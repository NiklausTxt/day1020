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
		Console.print("��ѡ���������ݣ�\n 1��Login--�û���¼\n " + "2��Signup--�û�ע��\n 3��exit--�˳�\n 4��Help--��ʾ������Ϣ\n");
		while (true) {
			String name = Console.askUserInput("���������ѡ��");
			if ("".equals(name.trim())) {
				Console.println("Name is not allowed to be empty!");
				continue;
			}
			if ("1".equals(name)) {
				Console.println("=====================��ӭ�����û���¼����=====================");
				while (true) {
					String username = Console.askUserInput("�������û�����");
					String password = Console.askUserInput("���������룺");
					player = DatabaseConnection.login(username, password);
					if (player == null) {
						Console.println("�û��������������......");
						continue;
					}else{
						Console.println("��ӭ"+player.getName()+",��¼�ɹ���");
						break;
					}
				}
				Console.println("�����Խ���һ�²�����\n 1.�˳���exit��"
						+ "\n 2.������help��"
						+ "\n 3.�������⣨list��"
						+ "\n 4.���ʣ�ask��"
						+ "\n 5.�ش�answer��"
						+ "\n 6.��Ѵ𰸣�accept��"
						+ "\n 7.��ʾ�÷֣�score��");
				while(true){
					String command = Console.askUserInput("cmd> ");
					if(EXIT.equalsIgnoreCase(command)){
						Console.println("��ӭ�´�ʹ�ã��ټ�����");
						System.exit(0);
					}else if(HELP.equalsIgnoreCase(command)){
						Console.println("�����Խ���һ�²�����\n 1.�˳���exit��"
								+ "\n 2.������help��"
								+ "\n 3.�������⣨list��"
								+ "\n 4.���ʣ�ask��"
								+ "\n 5.�ش�answer��"
								+ "\n 6.��Ѵ𰸣�accept��"
								+ "\n 7.��ʾ�÷֣�score��");
						continue;
					}else if(LIST.equalsIgnoreCase(command)){
						Console.println("��ѡ�����������ģʽ��ALL,MINE,OPEN,ID");
						String type = Console.askUserInput("cmd>");
						if("ALL".equalsIgnoreCase(type)){
							DatabaseConnection.listQuestion(player, "ALL");
						}else if("MINE".equalsIgnoreCase(type)){
							DatabaseConnection.listQuestion(player, "MINE");
						}else if("OPEN".equalsIgnoreCase(type)){
							DatabaseConnection.listQuestion(player, "OPEN");
						}else if("ID".equalsIgnoreCase(type)){
							int qID = Integer.parseInt(Console.askUserInput("������ID��"));
							DatabaseConnection.listQuestion2(player, "ID",qID);
						}
						
						
					}else if(ASK.equalsIgnoreCase(command)){
						String question = Console.askUserInput("���������ʵ����⣺");
						int credit =Integer.parseInt(Console.askUserInput("����������ķ�ֵ��"));
						if(player.getScore()<credit){
							Console.println("��ĵ�ǰ��ֵ����"+credit+",Ĭ����������ķ�ֵΪ"+player.getScore());
							credit=player.getScore();
						}
						DatabaseConnection.askQuestion(player, question, credit);
						Console.println("�������Ѿ�����");
					}else if (ANSWER.equalsIgnoreCase(command)) {
						
						int answerID = Integer.parseInt(Console.askUserInput("������ش�����ı�ţ�"));
						int quesId = DatabaseConnection.isExists(answerID);
						while(quesId<0){
							Console.println("�������Ų�����...");
							answerID = Integer.parseInt(Console.askUserInput("������ش�����ı�ţ�"));
							quesId = DatabaseConnection.isExists(answerID);
						}
						String aAnswer = Console.askUserInput("�����������");
						DatabaseConnection.answerQuestion(aAnswer,quesId,player.getId());
						Console.println("���Ѿ�����");
						
					}else if (SCORE.equalsIgnoreCase(command)) {
						
					}else if (ACCEPT.equalsIgnoreCase(command)) {
						int quesID = Integer.parseInt(Console.askUserInput("����������ı�ţ�"));
//						int qid = DatabaseConnection.isBelong(quesID, player);
//						while(qid<=0){
//							Console.println("���������Ѵ𰸲����ɸ��û�ѡ��");
//							quesID = Integer.parseInt(Console.askUserInput("����������ı�ţ�"));
//							qid = DatabaseConnection.isBelong(quesID, player);
//						}
						DatabaseConnection.accept(quesID);
					}
				}
			}else if("2".equals(name)){
				Console.println("=====================��ӭ�����û�ע�����=====================");
				while(true){
					String nickname = Console.askUserInput("�������ǳƣ�");
					String username = Console.askUserInput("�������û�����");					
					String password = Console.askUserInput("���������룺");
					player = new Player(nickname, username, password);
					int i= DatabaseConnection.signup(player);
					if(i<0){
						Console.println("���û����Ѿ����ڣ�������ע��");
						continue;
					}else {
						Console.println("�ɹ��������û�");
						break;
					}
				}
			}else if ("3".equals(name)) {
				Console.println("��ӭ�´�ʹ�ã��ټ�����");
				break;
			}else if ("4".equals(name)) {
				Console.println("1��Login--�û���¼\n " + "2��Signup--�û�ע��\n 3��exit--�˳�\n 4��Help--��ʾ������Ϣ\n");
			}else {
				Console.println("���벻�Ϸ�!");
				continue;
			}
		}
}

}
