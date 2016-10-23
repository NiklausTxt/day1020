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
		int quesID = Integer.parseInt(Console.askUserInput("����������ı�ţ�"));
//		int qid = DatabaseConnection.isBelong(quesID, player);
//		while (qid <= 0) {
//			Console.println("���������Ѵ𰸲����ɸ��û�ѡ��");
//			quesID = Integer.parseInt(Console.askUserInput("����������ı�ţ�"));
//			qid = DatabaseConnection.isBelong(quesID, player);
//		}
		List<Integer> list = DatabaseDao.accept(player,quesID);
		int bestAnswer = Integer.parseInt(Console.askUserInput("��������Ѵ𰸵Ĵ𰸱�ţ�"));
	}

}
