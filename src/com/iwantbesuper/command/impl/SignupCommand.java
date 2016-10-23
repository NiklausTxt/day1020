package com.iwantbesuper.command.impl;

import java.util.regex.Pattern;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class SignupCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("=====================欢迎来到用户注册界面=====================");

		String username = Console.askUserInput("请输入用户名：");
		while(DatabaseDao.isUsernameExists(username)){
			Console.println("用户名已存在");
			username = Console.askUserInput("请输入用户名：");
		}
		String password=null;
		while(true){
			password = Console.askUserInput("请输入密码：");
			try {
				validPassword(password);
				break;
			} catch (Exception e) {
				Console.println("密码错误："+e.getMessage());
				continue;
			}
		}
		
		String nickname = Console.askUserInput("请输入昵称：");
		Player player = new Player(nickname, username, password);
		DatabaseDao.signup(player);
		Console.println("该用户成功创建");
		
//		Player player = null;
//		while(true){
//			String nickname = Console.askUserInput("请输入昵称：");
//			String username = Console.askUserInput("请输入用户名：");					
//			String password = Console.askUserInput("请输入密码：");
//			player = new Player(nickname, username, password);
//			int i= DatabaseDao.signup(player);
//			if(i<0){
//				Console.println("该用户名已经存在，请重新注册");
//				continue;
//			}else {
//				Console.println("成功创建该用户");
//				break;
//			}
//		}
		
	}
	
	private void validPassword(String password) throws Exception{
		//长度大于6
		Pattern lenPattern = Pattern.compile("[0-9a-zA-Z]{6,}");
		if(!lenPattern.matcher(password).find()){
			throw new Exception("密码长度至少为6位");
		}
		
		//至少包含一位数字
		Pattern numPattern = Pattern.compile("[0-9]");
		if(!numPattern.matcher(password).find()){
			throw new Exception("密码至少需要一位数字");
		}
		
		//至少包含一位字母
		Pattern wordPattern = Pattern.compile("[a-zA-z]");
		if (!wordPattern.matcher(password).find()) {
			throw new Exception("密码至少需要一位字母");
		}
	}
}
