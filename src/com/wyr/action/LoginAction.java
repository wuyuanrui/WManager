package com.wyr.action;

import java.util.Scanner;

import com.wyr.daoimpl.LoginDao;
import com.wyr.pojo.Admininfo;

public class LoginAction {
	static Scanner sca=new Scanner(System.in);
	static LoginDao ld=new LoginDao();
	public static void login(){
		System.out.println("��������������ӭ�����人��ѧ���¹���ϵͳ������������");
		int i=3;
		while(true){
			i--;
			System.out.println("�������û�����");
			String name=sca.next();
			System.out.println("���������룺");
			String pwd=sca.next();
			Admininfo an=ld.login(name,pwd);
			if(name.equals(an.getAname())&& pwd.equals(an.getApwd())){
				//��¼�ɹ�
				MenueAction.Menue();
				break;
			}else{
				System.out.println("��¼ʧ�ܣ��㻹��"+i+"�λ���");
				if(i==0){
					System.out.println("�������꣬�ټ���");
				}
			}
			
			if(i==0)
				break;
		}
		System.out.println("�������꣬лл");
	}
	
	
}
