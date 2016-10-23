package com.iwantbesuper.command.impl;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.TestMain;
import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class LoginCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("=====================��ӭ�����û���¼����=====================");
		
		String username = Console.askUserInput("�������û�����");
		while(!DatabaseDao.isUsernameExists(username)){
			Console.println("�û���������");
			username = Console.askUserInput("�������û�����");
		}
		String password = Console.askUserInput("���������룺");
		Player player = DatabaseDao.login(username, password);
		if(player==null){
			Console.println("��¼ʧ�ܣ��������");
			return ;
		}
		Console.println("��ӭ"+player.getName()+",��¼�ɹ���");
		TestMain.getInstance().setPlayer(player);
	}

	
}
