package com.wyr.action;

import java.util.Scanner;

import com.wyr.daoimpl.LoginDao;
import com.wyr.pojo.Admininfo;

public class LoginAction {
	static Scanner sca=new Scanner(System.in);
	static LoginDao ld=new LoginDao();
	public static void login(){
		System.out.println("》》》》》》欢迎来到武汉大学人事管理系统》》》》》》");
		int i=3;
		while(true){
			i--;
			System.out.println("请输入用户名：");
			String name=sca.next();
			System.out.println("请输入密码：");
			String pwd=sca.next();
			Admininfo an=ld.login(name,pwd);
			if(name.equals(an.getAname())&& pwd.equals(an.getApwd())){
				//登录成功
				MenueAction.Menue();
				break;
			}else{
				System.out.println("登录失败，你还有"+i+"次机会");
				if(i==0){
					System.out.println("机会用完，再见！");
				}
			}
			
			if(i==0)
				break;
		}
		System.out.println("次数用完，谢谢");
	}
	
	
}
