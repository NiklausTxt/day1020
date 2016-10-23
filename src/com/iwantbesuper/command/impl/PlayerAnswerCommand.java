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
		int qid = Integer.parseInt(Console.askUserInput("������ش�����ı�ţ�"));
		
		Question question = DatabaseDao.getQuestion(qid);
		if(question==null){
			Console.println("�������Ų�����...");
			return ;
		}
		if(player.getId() == question.getPlayer_id()){
			Console.println("�㲻�ܻش��Լ�������");
			return ;
		}
		String aAnswer = Console.askUserInput("�����������");
		Answer answer = new Answer(-1, aAnswer, qid, player.getId());
		DatabaseDao.upsertAnswer(answer);
		
		player.getAnswers().add(answer);
		question.getAnswers().add(answer);
		
		
		
//		int quesId = DatabaseDao.isExists(answerID);
//		while(quesId<0){
//			Console.println("�������Ų�����...");
//			answerID = Integer.parseInt(Console.askUserInput("������ش�����ı�ţ�"));
//			quesId = DatabaseDao.isExists(answerID);
//		}
//		String aAnswer = Console.askUserInput("�����������");
//		DatabaseDao.answerQuestion(aAnswer,quesId,player.getId());
//		Console.println("���Ѿ�����");
	}

}
