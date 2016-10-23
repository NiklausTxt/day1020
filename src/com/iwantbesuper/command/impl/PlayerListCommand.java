package com.iwantbesuper.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.iwantbesuper.DatabaseDao;
import com.iwantbesuper.Player;
import com.iwantbesuper.Question;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerListCommand extends PlayerCommand{

	public PlayerListCommand(Player player) {
		super(player);
	}

	@Override
	public void excute() {
		List<Question> questions =new ArrayList<>();
		Console.println("��ѡ�����������ģʽ��ALL,MINE,OPEN,ID");
		String type = Console.askUserInput("cmd>");
		if("ALL".equalsIgnoreCase(type)){
//			DatabaseDao.listQuestion(player, "ALL");
			questions = DatabaseDao.getQuestions();
		}else if("MINE".equalsIgnoreCase(type)){
//			DatabaseDao.listQuestion(player, "MINE");
			questions = DatabaseDao.getQuestions(player);
		}else if("OPEN".equalsIgnoreCase(type)){
			List<Question> tempList = DatabaseDao.getQuestions();
			for(Question q:tempList){
				if(q.isOpen()){
					questions.add(q);
				}
			}
		}else if("ID".equalsIgnoreCase(type)){
			int qID = Integer.parseInt(Console.askUserInput("������ID��"));
//			DatabaseDao.listQuestion2(player, "ID",qID);
			Question question = DatabaseDao.getQuestion(qID);
			if(question==null){
				Console.println("�������Ų����ڡ�");
				return ;
			}
			questions.add(question);
		}
		for(Question q:questions){
			Console.println(q.toString());
		}
	}

	
}
