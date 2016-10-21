package com.iwantbesuper;

import java.util.List;

public class TestMain {
	private final static String EXIT = "exit";
	private final static String HELP = "help";
	private final static String ASK = "ask";
	private final static String ANSWER = "answer";
	private final static String LIST = "list";
	private final static String SCORE = "score";
	private final static String ACCEPT = "accept";

	public static void main(String[] args) {
		Player player=null;
		Console.print("请选择以下内容：\n 1：Login--用户登录\n " + "2：Signup--用户注册\n 3：exit--退出\n 4：Help--显示帮助信息\n");
		while (true) {
			String name = Console.askUserInput("请输入你的选择：");
			if ("".equals(name.trim())) {
				Console.println("Name is not allowed to be empty!");
				continue;
			}
			if ("1".equals(name)) {
				Console.println("=====================欢迎来到用户登录界面=====================");
				while (true) {
					String username = Console.askUserInput("请输入用户名：");
					String password = Console.askUserInput("请输入密码：");
					player = DatabaseConnection.login(username, password);
					if (player == null) {
						Console.println("用户名或者密码错误......");
						continue;
					}else{
						Console.println("欢迎"+player.getName()+",登录成功！");
						break;
					}
				}
				Console.println("您可以进行一下操作：\n 1.退出（exit）"
						+ "\n 2.帮助（help）"
						+ "\n 3.罗列问题（list）"
						+ "\n 4.提问（ask）"
						+ "\n 5.回答（answer）"
						+ "\n 6.最佳答案（accept）"
						+ "\n 7.显示得分（score）");
				while(true){
					String command = Console.askUserInput("cmd> ");
					if(EXIT.equalsIgnoreCase(command)){
						Console.println("欢迎下次使用！再见！！");
						System.exit(0);
					}else if(HELP.equalsIgnoreCase(command)){
						Console.println("您可以进行一下操作：\n 1.退出（exit）"
								+ "\n 2.帮助（help）"
								+ "\n 3.罗列问题（list）"
								+ "\n 4.提问（ask）"
								+ "\n 5.回答（answer）"
								+ "\n 6.最佳答案（accept）"
								+ "\n 7.显示得分（score）");
						continue;
					}else if(LIST.equalsIgnoreCase(command)){
						Console.println("请选择罗列问题的模式：ALL,MINE,OPEN,ID");
						String type = Console.askUserInput("cmd>");
						if("ALL".equalsIgnoreCase(type)){
							DatabaseConnection.listQuestion(player, "ALL");
						}else if("MINE".equalsIgnoreCase(type)){
							DatabaseConnection.listQuestion(player, "MINE");
						}else if("OPEN".equalsIgnoreCase(type)){
							DatabaseConnection.listQuestion(player, "OPEN");
						}else if("ID".equalsIgnoreCase(type)){
							int qID = Integer.parseInt(Console.askUserInput("请输入ID："));
							DatabaseConnection.listQuestion2(player, "ID",qID);
						}
						
						
					}else if(ASK.equalsIgnoreCase(command)){
						String question = Console.askUserInput("请输入提问的问题：");
						int credit =Integer.parseInt(Console.askUserInput("请输入问题的分值："));
						if(player.getScore()<credit){
							Console.println("你的当前分值不足"+credit+",默认设置问题的分值为"+player.getScore());
							credit=player.getScore();
						}
						DatabaseConnection.askQuestion(player, question, credit);
						Console.println("该问题已经创建");
					}else if (ANSWER.equalsIgnoreCase(command)) {
						
						int answerID = Integer.parseInt(Console.askUserInput("请输入回答问题的编号："));
						int quesId = DatabaseConnection.isExists(answerID);
						while(quesId<0){
							Console.println("该问题编号不存在...");
							answerID = Integer.parseInt(Console.askUserInput("请输入回答问题的编号："));
							quesId = DatabaseConnection.isExists(answerID);
						}
						String aAnswer = Console.askUserInput("请输入问题答案");
						DatabaseConnection.answerQuestion(aAnswer,quesId,player.getId());
						Console.println("答案已经插入");
						
					}else if (SCORE.equalsIgnoreCase(command)) {
						
					}else if (ACCEPT.equalsIgnoreCase(command)) {
						int quesID = Integer.parseInt(Console.askUserInput("请输入问题的编号："));
//						int qid = DatabaseConnection.isBelong(quesID, player);
//						while(qid<=0){
//							Console.println("该问题的最佳答案不能由该用户选择");
//							quesID = Integer.parseInt(Console.askUserInput("请输入问题的编号："));
//							qid = DatabaseConnection.isBelong(quesID, player);
//						}
						DatabaseConnection.accept(quesID);
					}
				}
			}else if("2".equals(name)){
				Console.println("=====================欢迎来到用户注册界面=====================");
				while(true){
					String nickname = Console.askUserInput("请输入昵称：");
					String username = Console.askUserInput("请输入用户名：");					
					String password = Console.askUserInput("请输入密码：");
					player = new Player(nickname, username, password);
					int i= DatabaseConnection.signup(player);
					if(i<0){
						Console.println("该用户名已经存在，请重新注册");
						continue;
					}else {
						Console.println("成功创建该用户");
						break;
					}
				}
			}else if ("3".equals(name)) {
				Console.println("欢迎下次使用！再见！！");
				break;
			}else if ("4".equals(name)) {
				Console.println("1：Login--用户登录\n " + "2：Signup--用户注册\n 3：exit--退出\n 4：Help--显示帮助信息\n");
			}else {
				Console.println("输入不合法!");
				continue;
			}
		}
}

}
