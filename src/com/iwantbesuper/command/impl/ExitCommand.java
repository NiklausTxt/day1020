package com.iwantbesuper.command.impl;

import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class ExitCommand extends SystemCommand{

	@Override
	public void excute() {
		Console.println("欢迎下次使用，再见！！！");
		System.exit(0);	
	}

	

}
