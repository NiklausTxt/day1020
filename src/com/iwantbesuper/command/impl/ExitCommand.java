package com.iwantbesuper.command.impl;

import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class ExitCommand extends SystemCommand{

	@Override
	public void excute() {
		Console.println("��ӭ�´�ʹ�ã��ټ�������");
		System.exit(0);	
	}

	

}
