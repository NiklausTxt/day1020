package com.iwantbesuper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

public class DatabaseConnection {
	public DatabaseConnection() {

	}

	public static int signup(Player player) {
		String sql = "INSERT INTO `iwantbesuper`.`player` (`name`, `username`, `password`) VALUES (?,?,?)";
		String sql2 = "select id from player where username=?";
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		String name = player.getName();
		String username = player.getUsername();
		String password = player.getPassword();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id from player where username='" + username + "'");
			if (rs.next()) {
				return -1;
			} else {
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, username);
					ps.setString(3, password);

					ps.execute();
					// System.out.println("插入成功");
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					cleanup(conn, ps, null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(conn, stmt, rs);
		}
		return 1;

		// try {
		// conn=getConnection();
		// ps=conn.prepareStatement(sql);
		// ps.setString(1, name);
		// ps.setString(2, username);
		// ps.setString(3, password);
		//
		// ps.execute();
		// System.out.println("插入成功");
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }finally {
		// cleanup(conn, ps, null);
		// }
	}

	public static Player login(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// System.out.println("成功连接数据库");
			ps = conn.prepareStatement("select * from player where username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			Player player = null;
			while (rs.next()) {
				String name = rs.getString("name");
				// System.out.println(name);
				int id = rs.getInt("id");
				int score = rs.getInt("score");
				player = new Player(id, name, score, username, password);
			}
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			cleanup(conn, ps, rs);
		}

	}

	public static void listQuestion2(Player player, String type, int qID) {
		String sqlID = "select count(answer.id) as 'num',question.id,question.player_id,question.value"
				+ ",question.isOpen from question , answer where answer.question_id=question.id and question.id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = getConnection();
		if ("ID".equalsIgnoreCase(type)) {
			try {
				ps = conn.prepareStatement(sqlID);
				ps.setInt(1, qID);
				rs = ps.executeQuery();
				if(rs==null){
					System.out.println("");
				}
				while (rs.next()) {
					int quesId = rs.getInt("question.id");
					int playerID = rs.getInt("question.player_id");
					String qvalue = rs.getString("question.value");
//					String avalue = rs.getString("answer.value");
					int num = rs.getInt("num");
					String isOpen = rs.getString("question.isOpen");
					if ("Y".equals(isOpen)) {
						System.out.println("[UNSOLVED] Question [id : " + quesId + ",ask by player" + playerID + ", qustion content : "
								+ qvalue + ", number of answer:" + num+"]");
					}else{
						System.out.println("[SOLVED]   Question [id : " + quesId + ",ask by player" + playerID + ", qustion content : "
								+ qvalue + ", number of answer:" + num+"]");
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cleanup(conn, ps, rs);
			}
		}
	}

	public static void listQuestion(Player player, String type) {
		int id = player.getId();
		String sqlAll = "select * from question";
		String sqlMINE = "select * from question where player_id = ?";
		String sqlOPEN = "select * from question where isOpen = 'Y'";
		String sqlID = "select q.id,q.player_id,q.value,a.value,a.player_id from question q, answer a where a.question_id=q.id and q.id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = getConnection();

		if ("All".equalsIgnoreCase(type)) {
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlAll);
				while (rs.next()) {
					int quesId = rs.getInt("id");
					String value = rs.getString("value");
					int credit = rs.getInt("credit");
					int player_id = rs.getInt("player_id");
					String isOpen = rs.getString("isOpen");
					if ("Y".equals(isOpen)) {
						System.out.println("[UNSLOVED]" + "Question [id=" + quesId + ",value=" + value + ", credit="
								+ credit + ", ask by player" + player_id + "]");
					} else {
						System.out.println("[SLOVED]  " + "Question [id=" + quesId + ",value=" + value + ", credit="
								+ credit + ", ask by player" + player_id + "]");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cleanup(conn, stmt, rs);
			}
		} else if ("Mine".equalsIgnoreCase(type)) {
			try {
				ps = conn.prepareStatement(sqlMINE);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					int quesId = rs.getInt("id");
					String value = rs.getString("value");
					int credit = rs.getInt("credit");
					int player_id = rs.getInt("player_id");
					String isOpen = rs.getString("isOpen");
					if ("Y".equals(isOpen)) {
						System.out.println("[UNSLOVED]" + "Question [id=" + quesId + ",value=" + value + ", credit="
								+ credit + ", ask by player" + player_id + "]");
					} else {
						System.out.println("[SLOVED]  " + "Question [id=" + quesId + ",value=" + value + ", credit="
								+ credit + ", ask by player" + player_id + "]");
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cleanup(conn, ps, rs);
			}
		} else if ("OPEN".equalsIgnoreCase(type)) {
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sqlOPEN);
				while (rs.next()) {
					int quesId = rs.getInt("id");
					String value = rs.getString("value");
					int credit = rs.getInt("credit");
					int player_id = rs.getInt("player_id");
					String isOpen = rs.getString("isOpen");
					if ("Y".equals(isOpen)) {
						System.out.println("[UNSLOVED]" + "Question [id=" + quesId + ",value=" + value + ", credit="
								+ credit + ", ask by player" + player_id + "]");
					} else {
						System.out.println("[SLOVED]  " + "Question [id=" + quesId + ",value=" + value + ", credit="
								+ credit + ", ask by player" + player_id + "]");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cleanup(conn, stmt, rs);
			}
		}
	}

	public static void askQuestion(Player player, String ques, int credit) {
		Connection conn = null;
		PreparedStatement ps = null;

		int player_id = player.getId();
		String sql = "INSERT INTO `iwantbesuper`.`question` (`value`, `credit`, `player_id`) VALUES (?,?,?);";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ques);
			ps.setInt(2, credit);
			ps.setInt(3, player_id);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(conn, ps, null);
		}

	}

	public static int isExists(int answerID) {
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			cs = conn.prepareCall("call iwantbesuper.ps_is_exist(?, ?)");
			cs.setInt(1, answerID);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			int res_count = cs.getInt(2);
			int quesId = -1;
			System.out.println(res_count);
			rs = cs.getResultSet();
			while (rs.next()) {
				quesId = rs.getInt("id");
			}
			if (res_count <= 0) {
				return -1;
			} else {
				return quesId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			cleanup(conn, cs, rs);
		}
	}

	public static void answerQuestion(String value, int quesId, int playerId) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `iwantbesuper`.`answer` (`value`, `player_id`, `question_id`) VALUES (?,?,?)";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			ps.setInt(2, playerId);
			ps.setInt(3, quesId);

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(conn, ps, null);
		}
	}

	public static int isBelong(int quesId, Player player) {
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			cs = conn.prepareCall("call iwantbesuper.ps_is_belong(?, ?,?)");
			cs.setInt(1, quesId);
			cs.setInt(2, player.getId());
			cs.registerOutParameter(3, Types.INTEGER);
			cs.execute();
			int res_count = cs.getInt(3);
			int qid = -1;
			System.out.println(res_count);
			rs = cs.getResultSet();
			while (rs.next()) {
				qid = rs.getInt("id");
			}
			if (res_count <= 0) {
				return -1;
			} else {
				return qid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			cleanup(conn, cs, rs);
		}
	}

	public static void accept(int quesId) {
		String sql = "select * from question, answer where question.id=answer.id and question.player_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, quesId);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (i == 0) {
					String quesContent = rs.getString("question.value");
					System.out.println("Question " + quesId + ":[content:" + quesContent + "]");
					i++;
				}
				int answerId = rs.getInt("answer.id");
				int playerId = rs.getInt("answer.player_id");
				String answerContent = rs.getString("answer.value");
				System.out
						.println("Answer " + answerId + ":[Content:" + answerContent + ",answer by " + playerId + "]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static Connection getConnection() {
		loadDriverClass();
		try {
			String url = "jdbc:mysql://localhost:3306/iwantbesuper?useSSL=false";
			String u = "root";
			String p = "niklaus1993";
			return DriverManager.getConnection(url, u, p);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	private static void loadDriverClass() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			// System.out.println(driver);
			Class.forName(driver);
			// System.out.println("成功加载MySQL驱动程序");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void cleanup(Connection conn, Statement stmt, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
