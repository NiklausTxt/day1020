package com.iwantbesuper.command.impl;

import com.iwantbesuper.command.SystemCommand;
import com.iwantbesuper.util.Console;

public class HelpCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("��ӭ������ϵͳ����ѡ����������");
		Console.println("Login--�û���¼");
		Console.println("Signup--�û�ע��");
		Console.println("exit--�˳�");
		Console.println("Help--��ʾ������Ϣ");
	}

}
