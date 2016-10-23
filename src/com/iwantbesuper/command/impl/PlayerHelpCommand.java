package com.iwantbesuper.command.impl;

import com.iwantbesuper.Player;
import com.iwantbesuper.command.PlayerCommand;
import com.iwantbesuper.util.Console;

public class PlayerHelpCommand extends PlayerCommand{

	public PlayerHelpCommand(Player player) {
		super(player);
	}

	@Override
	public void excute() {
		Console.println("�����Խ���һ�²���");
		Console.println("Exit--�˳�");
		Console.println("Help--����");
		Console.println("List--��������");
		Console.println("Ask--����");
		Console.println("Answer--�ش�����");
		Console.println("Accept--ѡ����Ѵ�");
		Console.println("Score--��ʾ�÷�");
	}
	
}
