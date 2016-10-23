package com.iwantbesuper.command.impl;

import java.sql.Connection;
import java.util.List;

import com.iwantbesuper.Answer;
import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.Question;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerScoreCommand extends PlayerCommand {

	public PlayerScoreCommand(Player player) {
		super(player);
	}

	@Override
	public void excute() {
		List<Question> questions = DatabaseDao.getQuestions(player);
		List<Answer> answers = DatabaseDao.answerFrom(player);
		int score = player.getScore();
		int qNum = questions.size();
		int aNum = answers.size();
		String level="";
		if (score > 100) {
			level = "ѧ��";
		} else if (score > 60) {
			level = "ѧ��";
		} else if (score > 30) {
			level = "ѧ��";
		} else{
			level = "����";
		}
		Console.println("==============" + player.getName() + "��ҵĳɼ�====================");
		Console.println("�������ĸ�����" + qNum);
		Console.println("�ش�����ĸ�����" + aNum);
		Console.println("������յ÷��ǣ�" + score);
		Console.println("������յȼ��ǣ�" + level);
	}

}
