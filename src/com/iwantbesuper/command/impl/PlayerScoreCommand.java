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
			level = "学霸";
		} else if (score > 60) {
			level = "学生";
		} else if (score > 30) {
			level = "学渣";
		} else{
			level = "弱鸡";
		}
		Console.println("==============" + player.getName() + "玩家的成绩====================");
		Console.println("提出问题的个数：" + qNum);
		Console.println("回答问题的个数：" + aNum);
		Console.println("你的最终得分是：" + score);
		Console.println("你的最终等级是：" + level);
	}

}
