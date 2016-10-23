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
		int quesID = Integer.parseInt(Console.askUserInput("请输入问题的编号："));
		Question question = DatabaseDao.getQuestion(quesID);
		List<Question> questions = DatabaseDao.getQuestions(player);
		if(question==null){
			Console.println("该问题不存在");
			return ;
		}
		
		if(!question.isOpen()){
			Console.println("该问题已设置最佳答案");
			return ;
		}
		
		boolean flag=false;
		for(Question q:questions){
			if(q.getId()==quesID){
				flag=true;
			}
		}
		if(!flag){
			Console.println("该问题不是由你创建，你没有权限设置最佳答案");
			return ;
		}
		
		List<Answer> answers = DatabaseDao.answerFrom(question);
		for(Answer a:answers){
			Console.println(a.toString());
		}
		
		int bestAnswer = Integer.parseInt(Console.askUserInput("请输入最佳答案的答案编号："));
		
		Answer answer = DatabaseDao.answerFrom(bestAnswer);
		if(answer==null){
			Console.println("输入的答案ID不存在");
			return ;
		}
		flag=false;
		for(Answer a:answers){
			if(a.getId()==answer.getId()){
				flag=true;
			}
		}
		if(!flag){
			Console.println("输入的答案ID不在可选列表中");
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
//		int quesID = Integer.parseInt(Console.askUserInput("请输入问题的编号："));
//		int qid = DatabaseConnection.isBelong(quesID, player);
//		while (qid <= 0) {
//			Console.println("该问题的最佳答案不能由该用户选择");
//			quesID = Integer.parseInt(Console.askUserInput("请输入问题的编号："));
//			qid = DatabaseConnection.isBelong(quesID, player);
//		}
//		List<Integer> list = DatabaseDao.accept(player,quesID);
//		int bestAnswer = Integer.parseInt(Console.askUserInput("请输入最佳答案的答案编号："));
	}

}
