package com.iwantbesuper.command.impl;

import java.util.List;

import com.iwantbesuper.Answer;
import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.Question;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerAcceptCommand extends PlayerCommand {

	public PlayerAcceptCommand(Player player) {
		super(player);
	}

	@Override
	public void excute() {
		int quesID = Integer.parseInt(Console.askUserInput("����������ı�ţ�"));
		Question question = DatabaseDao.getQuestion(quesID);
		List<Question> questions = DatabaseDao.getQuestions(player);
		if(question==null){
			Console.println("�����ⲻ����");
			return ;
		}
		
		if(!question.isOpen()){
			Console.println("��������������Ѵ�");
			return ;
		}
		
		boolean flag=false;
		for(Question q:questions){
			if(q.getId()==quesID){
				flag=true;
			}
		}
		if(!flag){
			Console.println("�����ⲻ�����㴴������û��Ȩ��������Ѵ�");
			return ;
		}
		
		List<Answer> answers = DatabaseDao.answerFrom(question);
		for(Answer a:answers){
			Console.println(a.toString());
		}
		
		int bestAnswer = Integer.parseInt(Console.askUserInput("��������Ѵ𰸵Ĵ𰸱�ţ�"));
		
		Answer answer = DatabaseDao.answerFrom(bestAnswer);
		if(answer==null){
			Console.println("����Ĵ�ID������");
			return ;
		}
		flag=false;
		for(Answer a:answers){
			if(a.getId()==answer.getId()){
				flag=true;
			}
		}
		if(!flag){
			Console.println("����Ĵ�ID���ڿ�ѡ�б���");
			return ;
		}
		
		answer.setBest(true);
		question.setOpen(false);
		int answerFrom = answer.getPlayer_id();
		Player aFrom = DatabaseDao.getPlayer(answerFrom);
		int score=question.getCredit()+aFrom.getScore();
		aFrom.setScore(score);
		
		DatabaseDao.acceptAnswer(question, answer, aFrom);
		
		
		
//		DatabaseDao.isAcceptable(player);
//		int quesID = Integer.parseInt(Console.askUserInput("����������ı�ţ�"));
//		int qid = DatabaseConnection.isBelong(quesID, player);
//		while (qid <= 0) {
//			Console.println("���������Ѵ𰸲����ɸ��û�ѡ��");
//			quesID = Integer.parseInt(Console.askUserInput("����������ı�ţ�"));
//			qid = DatabaseConnection.isBelong(quesID, player);
//		}
//		List<Integer> list = DatabaseDao.accept(player,quesID);
//		int bestAnswer = Integer.parseInt(Console.askUserInput("��������Ѵ𰸵Ĵ𰸱�ţ�"));
	}

}
