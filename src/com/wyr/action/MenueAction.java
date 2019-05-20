package com.wyr.action;

import java.util.Scanner;

public class MenueAction {
	static Scanner sca=new Scanner(System.in);
	//菜单
	public static void Menue(){
		while(true){
			System.out.println("1、管理员管理");
			System.out.println("2、部门管理");
			System.out.println("3、岗位管理");
			System.out.println("4、职位管理");
			System.out.println("5、员工管理");
			System.out.println("6、系院管理");
			System.out.println("7、年级管理");
			System.out.println("8、班级管理");
			System.out.println("9、科目管理");
			System.out.println("10、课程管理");
			System.out.println("你有以上操作（ 1 2 3 4 5 6 7 8 9 10）");
			System.out.println("请输入你的选择：");
			int index=sca.nextInt();
			switch(index){
				case 1:
					AdminInfoAction.showAll();
					break;
				case 2:
					DeptInfoAction.showAll();
					break;
				case 3:
					PostInfoAction.showAll();
					break;
				case 4:
					ZwinfoAction.showAll();
					break;
				case 5:
					EmploAction.showAll();
					break;
				case 6:
					XbAction.showAll();
					break;
				case 7:
					GradeAction.showAll();
					break;
				case 8:
					ClassesAction.showAll();
					break;
				case 9:
					SubjectsAction.showAll();
					break;
				case 10:
					CurrAction.showAll();
					break;
				default:
					System.out.println("输入有误！请正确选择");
					Menue();
					break;
			}
		}
		
		
	}
}
