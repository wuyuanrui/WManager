package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.AdminInfoDao;
import com.wyr.pojo.Admininfo;

public class AdminInfoAction {
	static Scanner sca=new Scanner(System.in);
	static AdminInfoDao adm=new AdminInfoDao();
	//选项菜单
	public static void showAll(){
		System.out.println("当前你所在的位置：》》首页》》管理员管理");
		System.out.println("1、管理员查看");
		System.out.println("2、管理员添加");
		System.out.println("3、管理员修改");
		System.out.println("4、管理员删除");
		System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
		int index=sca.nextInt();
		//选择
		switch(index){
			case 1:
				selectAd();
				break;
			case 2:
				addAd();
				break;
			case 3:
				updateAd();
				break;
			case 4:
				deleteAd();
				break;
			default:
				System.out.println("输入有误！请重新输入");
				//返回根目录
				MenueAction.Menue();
				break;
		}
		
	}
	//查看
	public static void selectAd(){
		List<Admininfo> ad=adm.listAd();
		System.out.println("查询信息如下：");
		System.out.println("ID\t管理员\t描述");
		for(Admininfo a:ad){
			System.out.println(a.getAid()+"\t"+a.getAname()+"\t"+a.getAremark());
		}
		back();
	}
	//添加
	public static void addAd(){
		System.out.println("你正在进行添加管理员的操作，请根据以下步骤进行");
		System.out.println("请输入你的名称");
		String name=sca.next();
		System.out.println("请输入你的密码");
		String pwd=sca.next();
		System.out.println("请输入你的个性签名");
		String gx=sca.next();
		
		Admininfo ad=new Admininfo();
		ad.setAname(name);
		ad.setApwd(pwd);
		ad.setAremark(gx);
		
		int i=adm.addAd(ad);
		if(i>0){
			System.out.println("添加成功");
			selectAd();
		}else{
			System.out.println("添加失败");
			selectAd();
		}
	}
	//修改
	public static void updateAd(){
		System.out.println("你正在进行修改管理员的操作，请根据以下步骤进行");
		System.out.println("当前你可以修改的管理员有如下：");
		//selectAd()方法
		List<Admininfo> ad=adm.listAd();
		System.out.println("查询信息如下：");
		System.out.println("ID\t管理员\t描述");
		for(Admininfo a:ad){
			System.out.println(a.getAid()+"\t"+a.getAname()+"\t"+a.getAremark());
		}
		//查看完毕修改开始
		System.out.println("请根据以上信息，输入要修改的ID:");
		int index=sca.nextInt();
		Admininfo one=adm.getOne(index);
		if(one.getAname()==null){
			System.out.println("没有这个人哦！请重新输入");
			showAll();
		}else{
			System.out.println("你要修改的管理员账号信息如下：");
			System.out.println("ID\t名称\t密码\t个性签名");
			System.out.println(one.getAid()+"\t"+one.getAname()+"\t"+one.getApwd()+"\t"+one.getAremark());
			System.out.println("修改请按1,退出修改请按2");
			int index1=sca.nextInt();
			if(index1==1){
				 updateSuccess(index);//复杂的修改！！！！！！！！
			}else if(index1==2){
				showAll();//退出修改
			}else{
				System.out.println("输入有误!");
				showAll();//返回继续修改
			}
		}
		
	}
	
	//删除
	public static void deleteAd(){
		System.out.println("你正在进行删除操作...");
		System.out.println("如下是你可以删除的管理员信息：");
		//selectAd()方法
				List<Admininfo> ad=adm.listAd();
				System.out.println("查询信息如下：");
				System.out.println("ID\t管理员\t描述");
				for(Admininfo a:ad){
					System.out.println(a.getAid()+"\t"+a.getAname()+"\t"+a.getAremark());
				}
		System.out.println("请输入你要删除的ID:");
		int index=sca.nextInt();
		Admininfo one=adm.getOne(index);//数据库查询当前这个人
		if(one.getAname()==null){
			System.out.println("没有这个人哦！请重新输入");
			showAll();
		}else{
			System.out.println("你要删除的管理员账号信息如下：");
			System.out.println("ID\t名称\t密码\t个性签名");
			System.out.println(one.getAid()+"\t"+one.getAname()+"\t"+one.getApwd()+"\t"+one.getAremark());
			System.out.println("删除请按1,退出删除请按2");
			int index1=sca.nextInt();
			if(index1==1){
				System.out.println("正在删除...");
				int i=adm.deleteAd(index);
				if(i>0){
					System.out.println("删除成功");
					selectAd();
				}else{
					System.out.println("删除失败");
					selectAd();
				}
			}else if(index1==2){
				System.out.println("你选择了撤销");
				showAll();
			}else{
				System.out.println("输入有误！");
				showAll();
			}
		}
		
		
	}
	
	public static void updateSuccess(int index) {
		System.out.println("你正在进行修改操作...");
		System.out.println("你有如下修改项：");
		System.out.println("1、名称修改");
		System.out.println("2、密码修改");
		System.out.println("3、个性签名修改");
		System.out.println("4、修改全部");
		int index2=sca.nextInt();
		switch(index2){
			case 1:
				System.out.println("正在进行用户名修改：");
				System.out.println("请输入你要修改的新用户名：");
				String name=sca.next();
				Admininfo a=new Admininfo();
				a.setAid(index);
				a.setAname(name);
				
				int i=adm.updateAd(a,1);
				if(i>0){
					System.out.println("修改成功");
					selectAd();//返回查询页面
				}else{
					System.out.println("修改失败");
					selectAd();//返回修改页面
				}
				break;
			case 2:
				System.out.println("正在进行密码修改：");
				System.out.println("请输入你要修改的新密码：");
				String pwd=sca.next();
				Admininfo b=new Admininfo();
				b.setAid(index);
				b.setApwd(pwd);
				int i1=adm.updateAd(b,2);
				if(i1>0){
					System.out.println("修改成功");
					selectAd();//返回查询页面
				}else{
					System.out.println("修改失败");
					selectAd();//返回修改页面
				}
				break;
			case 3:
				System.out.println("正在进行个性签名修改：");
				System.out.println("请输入你要修改的新个性签名：");
				String marks=sca.next();
				Admininfo c=new Admininfo();
				c.setAid(index);
				c.setAremark(marks);
				int i2=adm.updateAd(c,3);
				if(i2>0){
					System.out.println("修改成功");
					selectAd();//返回查询页面
				}else{
					System.out.println("修改失败");
					selectAd();//返回修改页面
				}
				break;
			case 4:
				System.out.println("正在进行全部信息修改：");
				System.out.println("请输入你要修改的新用户名：");
				String name1=sca.next();
				System.out.println("请输入你要修改的新密码：");
				String pwd1=sca.next();
				System.out.println("请输入你要修改的新个性签名：");
				String marks1=sca.next();
				Admininfo d=new Admininfo();
				d.setAid(index);
				d.setAname(name1);
				d.setApwd(pwd1);
				d.setAremark(marks1);
				int i3=adm.updateAd(d,4);
				if(i3>0){
					System.out.println("修改成功");
					selectAd();//返回查询页面
				}else{
					System.out.println("修改失败");
					selectAd();//返回修改页面
				}
				break;
			default:
				System.out.println("输入有误");
				showAll();
				break;
		}
		
	}
	
	//公共的返回
	public static void back(){
		System.out.println("返回根目录10，返回上一层11");
		int index=sca.nextInt();
		if(index==10){
			//返回根目录
			MenueAction.Menue();
		}else if(index==11){
			showAll();
		}
	}

}
