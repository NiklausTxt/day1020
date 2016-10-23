package com.iwantbesuper.command.impl;

import com.iwantbesuper.Player;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerHelpCommand extends PlayerCommand{

	public PlayerHelpCommand(Player player) {
		super(player);
	}

	@Override
	public void excute() {
		Console.println("您可以进行一下操作");
		Console.println("Exit--退出");
		Console.println("Help--帮助");
		Console.println("List--罗列问题");
		Console.println("Ask--提问");
		Console.println("Answer--回答问题");
		Console.println("Accept--选出最佳答案");
		Console.println("Score--显示得分");
	}
	
}
