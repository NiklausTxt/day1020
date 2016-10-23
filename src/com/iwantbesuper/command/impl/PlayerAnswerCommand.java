package com.iwantbesuper.command.impl;

import com.iwantbesuper.Answer;
import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.Question;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerAnswerCommand extends PlayerCommand{

	public PlayerAnswerCommand(Player player) {
		super(player);
	}

	@Override
	public void excute() {
		int qid = Integer.parseInt(Console.askUserInput("请输入回答问题的编号："));
		
		Question question = DatabaseDao.getQuestion(qid);
		if(question==null){
			Console.println("该问题编号不存在...");
			return ;
		}
		if(player.getId() == question.getPlayer_id()){
			Console.println("你不能回答自己的问题");
			return ;
		}
		String aAnswer = Console.askUserInput("请输入问题答案");
		Answer answer = new Answer(-1, aAnswer, qid, player.getId());
		DatabaseDao.upsertAnswer(answer);
		
		player.getAnswers().add(answer);
		question.getAnswers().add(answer);
		
		
		
//		int quesId = DatabaseDao.isExists(answerID);
//		while(quesId<0){
//			Console.println("该问题编号不存在...");
//			answerID = Integer.parseInt(Console.askUserInput("请输入回答问题的编号："));
//			quesId = DatabaseDao.isExists(answerID);
//		}
//		String aAnswer = Console.askUserInput("请输入问题答案");
//		DatabaseDao.answerQuestion(aAnswer,quesId,player.getId());
//		Console.println("答案已经插入");
	}

}
