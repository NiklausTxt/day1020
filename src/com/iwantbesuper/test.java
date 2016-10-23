package com.iwantbesuper;

import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		DatabaseDao dc=new DatabaseDao();
		
//		Scanner sc=new Scanner(System.in);
//		System.out.println("请输入用户名：");
//		String username = sc.nextLine();
//		System.out.println("请输入密码：");
//		String password=sc.nextLine();
//		Player player=DatabaseConnection.login(username, password);
//		System.out.println(player.toString());
		
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入用户名：");
		String name = sc.nextLine();
		System.out.println("请输入昵称：");
		String username=sc.nextLine();
		System.out.println("请输入密码：");
		String password=sc.nextLine();
		 
		Player player=new Player(name, username, password);
		DatabaseDao.signup(player);
//		System.out.println(player.toString());
		
	}
}
