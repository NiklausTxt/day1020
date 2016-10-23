package com.iwantbesuper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.codec.digest.DigestUtils;

import com.sun.xml.internal.bind.v2.model.core.ID;

import sun.net.www.content.text.plain;

public class DatabaseDao {
	
	public DatabaseDao() {

	}

	public static boolean isUsernameExists(String username){
		String sql = "select * from player where username = ?";
		Connection conn =null;
		PreparedStatement ps= null;
		ResultSet rs= null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeQuery();
			rs=ps.getResultSet();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return false;
	}
	
	public static void signup(Player player) {
		String sql = "INSERT INTO `iwantbesuper`.`player` (`name`, `username`, `password`) VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
				
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, player.getName());
			ps.setString(2, player.getUsername());
			ps.setString(3, DigestUtils.md5Hex(player.getPassword()));

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanup(conn, ps, null);
		}
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
			ps.setString(2, DigestUtils.md5Hex(password));
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

	public static List<Question> getQuestions(Player player){
		List<Question> questions = new ArrayList<>();
		String sql = "select * from question where player_id = ? order by isOpen desc";
		Connection conn= null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, player.getId());
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String value = rs.getString("value");
				int credit = rs.getInt("credit");
				int player_id = rs.getInt("player_id");
				boolean isOpen = "Y".equals(rs.getString("isOpen"));
				Question question = new Question(id,value,credit,player_id);
				question.setOpen(isOpen);
				
				List<Answer> answers = answerFrom(question);
				question.setAnswers(answers);
				
				questions.add(question);
			}
			
			return questions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return questions;
		
	}
	
	public static List<Question> getQuestions(){
		List<Question> questions = new ArrayList<>();
		String sql = "select * from question order by isOpen desc";
		Connection conn= null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String value = rs.getString("value");
				int credit = rs.getInt("credit");
				int player_id = rs.getInt("player_id");
				boolean isOpen = "Y".equals(rs.getString("isOpen"));
				Question question = new Question(id,value,credit,player_id);
				question.setOpen(isOpen);
				
				List<Answer> answers = answerFrom(question);
				question.setAnswers(answers);
				
				questions.add(question);
			}
			
			return questions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return questions;
	} 
	
	public static Question getQuestion(int qid){
		String sql = "select * from question where id=?";
		Connection conn= null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Question question=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, qid);
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String value = rs.getString("value");
				int credit = rs.getInt("credit");
				int player_id = rs.getInt("player_id");
				boolean isOpen = "Y".equals(rs.getString("isOpen"));
				question = new Question(id,value,credit,player_id);
				question.setOpen(isOpen);
				
				
			}
			
			return question;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return question;
	}
	
	public static List<Answer> answerFrom(Question ques){
		List<Answer> answers = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from answer where question_id = ?";
		
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ques.getId());
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String value = rs.getString("value");
				boolean best = "Y".equals(rs.getString("best"));
				int player_id = rs.getInt("player_id");
				int question_id = rs.getInt("question_id");
				Answer answer = new Answer(id, value, question_id, player_id);
				answer.setBest(best);
				
				answers.add(answer);
				
			}
			return answers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return answers;
	}
	
	public static Answer answerFrom(int aid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from answer where id = ?";
		Answer answer=null; 
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, aid);
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String value = rs.getString("value");
				boolean best = "Y".equals(rs.getString("best"));
				int player_id = rs.getInt("player_id");
				int question_id = rs.getInt("question_id");
				answer = new Answer(id, value, question_id, player_id);
				answer.setBest(best);
				
				
			}
			return answer;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return answer;
	}

	public static List<Answer> answerFrom(Player player){
		List<Answer> answers = new ArrayList<>();
		String sql = "select * from answer where player_id = ? order by id";
		Connection conn= null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, player.getId());
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String value = rs.getString("value");
				boolean best = "Y".equals(rs.getString("best"));
				int player_id = rs.getInt("player_id");
				int question_id = rs.getInt("question_id");
				Answer answer = new Answer(id, value, question_id, player_id);
				answer.setBest(best);
				
				answers.add(answer);
			}
			
			return answers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return answers;
	}
	
	public static Player getPlayer(int pid){
		String sql = "select * from player where id=?";
		Connection conn= null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Player player=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int score = rs.getInt("score");
				
				player = new Player(id, name, score);
				
				
			}
			
			return player;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return player;
	}
	
	public static void updatePlayer(Player player){
		Connection conn=null;
		PreparedStatement ps = null;
		String updatePlayer = "UPDATE `iwantbesuper`.`player` SET `score`=? WHERE `id`=?";
		
		try {
			conn=getConnection();
			ps=conn.prepareStatement(updatePlayer);
			ps.setInt(1, player.getScore());
			ps.setInt(2, player.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();		
		}finally {
			cleanup(conn, ps, null);
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

	public static void upsertQuestion(Question question){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlUpdate = "UPDATE `iwantbesuper`.`question` SET `value`=?, `credit`=?, `player_id`=?, `isOpen`=? WHERE `id`=?;";
		String sqlInsert = "INSERT INTO `iwantbesuper`.`question` (`value`, `credit`, `player_id`, `isOpen`) VALUES (?,?,?,?);";
		boolean isUpdate = question.getId() != -1;
	
		try {
			conn=getConnection();
			String sql=sqlInsert;
			if(isUpdate){
				sql=sqlUpdate;
			}
			ps=conn.prepareStatement(sql);
			ps.setString(1, question.getValue());
			ps.setInt(2, question.getCredit());
			ps.setInt(3, question.getPlayer_id());
			ps.setString(4, question.isOpen()?"Y":"N");
			if(isUpdate){
				ps.setInt(5, question.getId());
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		
	}
		
	public static void upsertAnswer(Answer answer){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlUpdate = "UPDATE `iwantbesuper`.`answer` SET `value`=?, `best`=?, `player_id`=?, `question_id`=? WHERE `id`=?";
		String sqlInsert = "INSERT INTO `iwantbesuper`.`answer` (`value`, `best`, `player_id`, `question_id`) VALUES (?,?,?,?)";
		boolean isUpdate = answer.getId() != -1;
		
		try {
			conn=getConnection();
			String sql=sqlInsert;
			if(isUpdate){
				sql=sqlUpdate;
			}
			ps=conn.prepareStatement(sql);
			ps.setString(1, answer.getValue());
			ps.setString(2, answer.isBest()?"Y":"N");
			ps.setInt(3, answer.getPlayer_id());
			ps.setInt(4, answer.getQuestion_id());
			if(isUpdate){
				ps.setInt(5, answer.getId());
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		
	}
	
	public static void acceptAnswer(Question question,Answer answer,Player player){
		Connection conn=null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		String updateQuestion = "UPDATE `iwantbesuper`.`question` SET `isOpen`='N' WHERE `id`=?";
		String updateAnswer = "UPDATE `iwantbesuper`.`answer` SET `best`='y' WHERE `id`=?";
		String updatePlayer = "UPDATE `iwantbesuper`.`player` SET `score`=? WHERE `id`=?";
		
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			
			ps1=conn.prepareStatement(updateQuestion);
			ps1.setInt(1, question.getId());
			ps1.execute();
			
			ps2=conn.prepareStatement(updateAnswer);
			ps2.setInt(1, answer.getId());
			ps2.execute();
			
			ps3=conn.prepareStatement(updatePlayer);
			ps3.setInt(1, player.getScore());
			ps3.setInt(2, player.getId());
			ps3.execute();
			
			conn.commit();			
		} catch (SQLException e) {
			
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}finally {
			cleanup(conn, ps1, null);
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
	
	public static void isAcceptable(Player player){
		String sql = "select * from question where player_id=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, player.getId());
			ps.executeQuery();
			rs=ps.getResultSet();
			while(rs.next()){
				String quesContent = rs.getString("question.value");
				int quesId = rs.getInt("id");
				System.out.println("Question " + quesId + ":[content:" + quesContent + "]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		
	}

	public static List<Integer> accept(Player player, int quesId) {
		String sql = "select * from question, answer where question.id=answer.question_id and question.player_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> list=new ArrayList<>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, quesId);
			rs = ps.executeQuery();

			while (rs.next()) {
				int answerId = rs.getInt("answer.id");
				int playerId = rs.getInt("answer.player_id");
				String answerContent = rs.getString("answer.value");
				list.add(answerId);
				System.out.println("Answer " + answerId + ":[Content:" + answerContent + ",answer by " + playerId + "]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cleanup(conn, ps, rs);
		}
		return list;

	}

	
	
	private static Connection getConnection() {
//		loadDriverClass();
		try {
			String url = "jdbc:mysql://localhost:3306/iwantbesuper?useSSL=false";
			String u = "root";
//			String p = "123456";
			String p = "niklaus1993";
			return DriverManager.getConnection(url, u, p);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public static void loadDriverClass() {
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
