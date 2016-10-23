package com.iwantbesuper.command.impl;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.TestMain;
import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class LoginCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("=====================欢迎来到用户登录界面=====================");
		
		String username = Console.askUserInput("请输入用户名：");
		while(!DatabaseDao.isUsernameExists(username)){
			Console.println("用户名不存在");
			username = Console.askUserInput("请输入用户名：");
		}
		String password = Console.askUserInput("请输入密码：");
		Player player = DatabaseDao.login(username, password);
		if(player==null){
			Console.println("登录失败，密码错误");
			return ;
		}
		Console.println("欢迎"+player.getName()+",登录成功！");
		TestMain.getInstance().setPlayer(player);
	}

	
}
