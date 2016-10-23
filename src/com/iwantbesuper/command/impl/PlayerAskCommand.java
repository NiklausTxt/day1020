package com.iwantbesuper.command.impl;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.Question;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerAskCommand extends PlayerCommand{

	public PlayerAskCommand(Player player) {
		super(player);		
	}

	@Override
	public void excute() {
		String content = Console.askUserInput("请输入提问的问题：");
		int credit =Integer.parseInt(Console.askUserInput("请输入问题的分值："));
		if(player.getScore()<credit){
			Console.println("你的当前分值不足"+credit+",默认设置问题的分值为"+player.getScore());
			credit=player.getScore();
		}
		Question question = new Question(-1, content, credit, player.getId());
//		Question question = new Question(id, value, credit, player_id)
		DatabaseDao.upsertQuestion(question);
		
		player.getQuestions().add(question);
		player.setScore(player.getScore()-credit);
		
		
//		String question = Console.askUserInput("请输入提问的问题：");
//		int credit =Integer.parseInt(Console.askUserInput("请输入问题的分值："));
//		if(player.getScore()<credit){
//			Console.println("你的当前分值不足"+credit+",默认设置问题的分值为"+player.getScore());
//			credit=player.getScore();
//		}
//		DatabaseDao.askQuestion(player, question, credit);
//		Console.println("该问题已经创建");
	}

}
