package com.wyr.action;

import java.util.Scanner;

public class MenueAction {
	static Scanner sca=new Scanner(System.in);
	//�˵�
	public static void Menue(){
		while(true){
			System.out.println("1������Ա����");
			System.out.println("2�����Ź���");
			System.out.println("3����λ����");
			System.out.println("4��ְλ����");
			System.out.println("5��Ա������");
			System.out.println("6��ϵԺ����");
			System.out.println("7���꼶����");
			System.out.println("8���༶����");
			System.out.println("9����Ŀ����");
			System.out.println("10���γ̹���");
			System.out.println("�������ϲ����� 1 2 3 4 5 6 7 8 9 10��");
			System.out.println("���������ѡ��");
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
					System.out.println("������������ȷѡ��");
					Menue();
					break;
			}
		}
		
		
	}
}
