package com.iwantbesuper.command.impl;

import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class HelpCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("欢迎来到此系统，请选择以下内容");
		Console.println("Login--用户登录");
		Console.println("Signup--用户注册");
		Console.println("exit--退出");
		Console.println("Help--显示帮助信息");
	}

}
