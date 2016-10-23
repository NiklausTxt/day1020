package com.iwantbesuper.command.impl;

import java.util.regex.Pattern;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class SignupCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("=====================��ӭ�����û�ע�����=====================");

		String username = Console.askUserInput("�������û�����");
		while(DatabaseDao.isUsernameExists(username)){
			Console.println("�û����Ѵ���");
			username = Console.askUserInput("�������û�����");
		}
		String password=null;
		while(true){
			password = Console.askUserInput("���������룺");
			try {
				validPassword(password);
				break;
			} catch (Exception e) {
				Console.println("�������"+e.getMessage());
				continue;
			}
		}
		
		String nickname = Console.askUserInput("�������ǳƣ�");
		Player player = new Player(nickname, username, password);
		DatabaseDao.signup(player);
		Console.println("���û��ɹ�����");
		
//		Player player = null;
//		while(true){
//			String nickname = Console.askUserInput("�������ǳƣ�");
//			String username = Console.askUserInput("�������û�����");					
//			String password = Console.askUserInput("���������룺");
//			player = new Player(nickname, username, password);
//			int i= DatabaseDao.signup(player);
//			if(i<0){
//				Console.println("���û����Ѿ����ڣ�������ע��");
//				continue;
//			}else {
//				Console.println("�ɹ��������û�");
//				break;
//			}
//		}
		
	}
	
	private void validPassword(String password) throws Exception{
		//���ȴ���6
		Pattern lenPattern = Pattern.compile("[0-9a-zA-Z]{6,}");
		if(!lenPattern.matcher(password).find()){
			throw new Exception("���볤������Ϊ6λ");
		}
		
		//���ٰ���һλ����
		Pattern numPattern = Pattern.compile("[0-9]");
		if(!numPattern.matcher(password).find()){
			throw new Exception("����������Ҫһλ����");
		}
		
		//���ٰ���һλ��ĸ
		Pattern wordPattern = Pattern.compile("[a-zA-z]");
		if (!wordPattern.matcher(password).find()) {
			throw new Exception("����������Ҫһλ��ĸ");
		}
	}
}
