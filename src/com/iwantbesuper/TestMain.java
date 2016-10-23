package com.iwantbesuper;

import com.iwantbesuper.command.CommandFactory;
import com.iwantbesuper.command.CommandFactory.CommandCode;
import com.iwantbesuper.command.Command;
import com.iwantbesuper.command.impl.HelpCommand;
import com.iwantbesuper.util.Console;

public class TestMain {
	private static TestMain instance = null;

	private TestMain() {
	}

	public synchronized static TestMain getInstance() {
		if (instance == null) {
			instance = new TestMain();
		}
		return instance;
	}

	private Player player;

	public static void main(String[] args) {
		Command start = new HelpCommand();
		DatabaseDao.loadDriverClass();
		start.excute();
		while (true) {
			String cmd = Console.askUserInput("cmd> ");
			CommandCode cmdCode = null;
			try {
				cmdCode = CommandCode.valueOf(cmd.toUpperCase());
			} catch (Exception e) {
				Console.println("不合法输入...");
				continue;
			}
			
			CommandFactory fac = CommandFactory.getFactory(TestMain.getInstance().getPlayer());
			Command command = fac.buildCommand(cmdCode);
			command.excute();

		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
