package com.iwantbesuper.command.impl;

import com.iwantbesuper.command.Command;
import com.iwantbesuper.util.Console;

public class InvalidCommand implements Command {

	@Override
	public void excute() {
		Console.println("输入不合法...");
	}

}
