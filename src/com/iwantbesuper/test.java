package com.iwantbesuper;

import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		DatabaseConnection dc=new DatabaseConnection();
		
//		Scanner sc=new Scanner(System.in);
//		System.out.println("�������û�����");
//		String username = sc.nextLine();
//		System.out.println("���������룺");
//		String password=sc.nextLine();
//		Player player=DatabaseConnection.login(username, password);
//		System.out.println(player.toString());
		
		Scanner sc=new Scanner(System.in);
		System.out.println("�������û�����");
		String name = sc.nextLine();
		System.out.println("�������ǳƣ�");
		String username=sc.nextLine();
		System.out.println("���������룺");
		String password=sc.nextLine();
		 
		Player player=new Player(name, username, password);
		DatabaseConnection.signup(player);
//		System.out.println(player.toString());
		
	}
}
