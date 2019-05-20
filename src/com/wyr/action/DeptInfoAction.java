package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.DeptinfoDao;
import com.wyr.pojo.Deptinfo;

public class DeptInfoAction{
	static Scanner sca=new Scanner(System.in);
	static DeptinfoDao dpd=new DeptinfoDao();
	//选项菜单
	public static void showAll(){
		System.out.println("当前你所在的位置：》》首页》》部门管理");
		System.out.println("1、部门信息查看");
		System.out.println("2、部门信息添加");
		System.out.println("3、部门信息修改");
		System.out.println("4、部门信息删除");
		System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
		int index=sca.nextInt();
		//选择
		switch(index){
			case 1:
				selectDe();
				break;
			case 2:
				addDe();
				break;
			case 3:
				updateDe();
				break;
			case 4:
				deleteDe();
				break;
			default:
				System.out.println("输入有误！请重新输入");
				//返回根目录
				MenueAction.Menue();
				break;
		}
		
	}
	//查询
	public static void selectDe() {
		List<Deptinfo> list=dpd.listDep();
		System.out.println("查询信息如下：");
		System.out.println("部门ID\t部门名称\t部门描述");
		for(Deptinfo d:list){
			System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
		};
		back();//返回
	}
	
	//删除
	public static void deleteDe() {
		System.out.println("你正在进行删除操作...");
		System.out.println("如下是你可以删除的部门信息：");
		//部门查看方法
		List<Deptinfo> list=dpd.listDep();
		System.out.println("查询信息如下：");
		System.out.println("部门ID\t部门名称\t部门描述");
		for(Deptinfo d:list){
			System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
		};
		System.out.println("请输入你要删除的ID:");
		int index=sca.nextInt();
		Deptinfo one=dpd.getOne(index);//数据库查询当前部门
		if(one.getDname()==null){
			System.out.println("没有这个部门哦！请重新输入");
			showAll();
		}else{
			System.out.println("你要删除的部门信息如下：");
			System.out.println("部门ID\t部门名称\t描述");
			System.out.println(one.getDid()+"\t"+one.getDname()+"\t"+one.getDremark());
			System.out.println("删除请按1,退出删除请按2");
			int index1=sca.nextInt();
			if(index1==1){
				System.out.println("正在删除...");
				int i=dpd.deleteDep(index);
				if(i>0){
					System.out.println("删除成功");
					selectDe();
				}else{
					System.out.println("删除失败");
					selectDe();
				}
			}else if(index1==2){
				showAll();
			}else{
				System.out.println("输入有误");
				showAll();
			}
		}
		
		
	}
	
	//部门修改
	public static void updateDe() {
		System.out.println("你正在进行修改部门的操作，请根据以下步骤进行");
		System.out.println("当前你可以修改的部门有如下：");
		//selectAd()方法
		List<Deptinfo> ad=dpd.listDep();
		System.out.println("查询信息如下：");
		System.out.println("部门ID\t部门\t描述");
		for(Deptinfo a:ad){
			System.out.println(a.getDid()+"\t"+a.getDname()+"\t"+a.getDremark());
		}
		//查看完毕修改开始
		System.out.println("请根据以上信息，输入要修改的ID:");
		int index=sca.nextInt();
		Deptinfo one=dpd.getOne(index);
		if(one.getDname()==null){
			System.out.println("没有这个部门哦！请重新输入");
			showAll();
		}else{
			System.out.println("你要修改的部门信息如下：");
			System.out.println("部门ID\t部门名称\t描述");
			System.out.println(one.getDid()+"\t"+one.getDname()+"\t"+one.getDremark());
			System.out.println("修改请按1,退出修改请按2");
			int index1=sca.nextInt();
			if(index1==1){
				System.out.println("当前正在修改..");
				System.out.println("请输入你要修改的部门名称：");
				String name=sca.next();
				System.out.println("请输入你要修改的部门描述");
				String mark=sca.next();
				//封装获取到的信息
				Deptinfo deptinfo=new Deptinfo();
				deptinfo.setDid(index);
				deptinfo.setDname(name);
				deptinfo.setDremark(mark);
				int i=dpd.updateDep(deptinfo);
				
				if(i>0){
					System.out.println("修改成功");
					selectDe();
				}else{
					System.out.println("修改失败");
					selectDe();//返回继续修改
				}
			}else if(index1==2){
				showAll();//退出修改
			}else{
				System.out.println("输入有误!");
				showAll();//返回继续修改
			}
		}
	}
	
	//部门添加
	public static void addDe() {
		System.out.println("你正在进行添加部门的操作，请根据以下步骤进行");
		System.out.println("请输入部门名称");
		String name=sca.next();
		System.out.println("请输入部门描述");
		String mark=sca.next();
		//信息封装
		Deptinfo ad=new Deptinfo();
		ad.setDname(name);
		ad.setDremark(mark);
		//添加操作
		int i=dpd.addDep(ad);
		if(i>0){
			System.out.println("添加成功");
			selectDe();
		}else{
			System.out.println("添加失败");
			selectDe();
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
