package com.iwantbesuper.command.impl;

import java.util.List;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerAcceptCommand extends PlayerCommand {

	public PlayerAcceptCommand(Player player) {
		super(player);
	}

	@Override
	public void excute() {
		DatabaseDao.isAcceptable(player);
		int quesID = Integer.parseInt(Console.askUserInput("请输入问题的编号："));
//		int qid = DatabaseConnection.isBelong(quesID, player);
//		while (qid <= 0) {
//			Console.println("该问题的最佳答案不能由该用户选择");
//			quesID = Integer.parseInt(Console.askUserInput("请输入问题的编号："));
//			qid = DatabaseConnection.isBelong(quesID, player);
//		}
		List<Integer> list = DatabaseDao.accept(player,quesID);
		int bestAnswer = Integer.parseInt(Console.askUserInput("请输入最佳答案的答案编号："));
	}

}
