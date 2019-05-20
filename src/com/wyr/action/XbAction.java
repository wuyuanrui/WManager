package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.XbDao;
import com.wyr.pojo.Xb;

public class XbAction  {
	static Scanner sca=new Scanner(System.in);
	static XbDao xbd=new XbDao();
	
	
	//选项菜单
		public static void showAll(){
			System.out.println("当前你所在的位置：》》首页》》学院管理");
			System.out.println("1、学院信息查看");
			System.out.println("2、学院信息添加");
			System.out.println("3、学院信息修改");
			System.out.println("4、学院信息删除");
			System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
			int index=sca.nextInt();
			//选择
			switch(index){
				case 1:
					selectXb();
					break;
				case 2:
					addXb();
					break;
				case 3:
					updateXb();
					break;
				case 4:
					deleteXb();
					break;
				default:
					System.out.println("输入有误！请重新输入");
					//返回根目录
					MenueAction.Menue();
					break;
			}
		}
		
		
	
	//学院查询
	public static void selectXb() {
		System.out.println("你现在正在查看院系...");
		System.out.println("院系列表如下所示：");
		List<Xb> list=xbd.listXb();
		System.out.println("院系ID\t名称\t描述");
		for(Xb x:list){
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
		}
		back();
	}




	//添加院系
	public static void addXb() {
		System.out.println("你正在添加院系");
		System.out.println("请输入要添加的院系名称：");
		String name=sca.next();
		System.out.println("请输入要添加的院系描述：");
		String mark=sca.next();
		System.out.println("如下是你要添加的信息：");
		System.out.println("院系\t描述");
		System.out.println(name+"\t"+mark);
		System.out.println("确认吗？（1/是   0/否）");
		int i=sca.nextInt();
		if(i==1){
			System.out.println("正在添加");
			//封装信息
			Xb xb=new Xb();
			xb.setXname(name);
			xb.setXremark(mark);
			//
			int ii=xbd.add(xb);
			if(ii>0){
				System.out.println("添加成功");
				selectXb();
			}else{
				System.out.println("添加失败");
				selectXb();
			}
		}else if(i==0){
			System.out.println("你撤销了操作");
			showAll();
		}else{
			System.out.println("输入有误请重新输入");
			addXb();
		}
	}




	//院系修改
	public static void updateXb() {
		System.out.println("你正在进行院系修改操作：");
		System.out.println("现在你有如下院系可以修改：");
		List<Xb> list=xbd.listXb();
		System.out.println("院系ID\t名称\t描述");
		for(Xb x:list){
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
		}
		System.out.println("请输入你要修改的院系ID");
		int index=sca.nextInt();
		
		Xb x=xbd.getOne(index);
		if(null==x.getXname()){
			System.out.println("输出错误没有该院系，请重新输入");
			showAll();
		}else{
			System.out.println("你要修改的院系信息如下所示：");
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
			System.out.println("确认修改吗？(1/是  0/否)");
			int i=sca.nextInt();
			if(i==1){
				System.out.println("请输入你要修改的院系名称：");
				String name=sca.next();
				System.out.println("请输入你要修改的院系的描述：");
				String mark=sca.next();
				System.out.println("以下是你要修改后的字段");
				Xb xb=new Xb();
				xb.setXid(index);
				xb.setXname(name);
				xb.setXremark(mark);
				
				System.out.println("院系名\t描述");
				System.out.println(xb.getXname()+"\t"+xb.getXremark());
				System.out.println("确认吗？（1/是  0/否）");
				int ii=sca.nextInt();
				if(ii==1){
					int a=xbd.update(xb);
					if(a>0){
						System.out.println("修改成功！");
						selectXb();
						System.out.println("还要继续修改其他吗？(1/是   0/否)");
						int con=sca.nextInt();
						if(con==1){
							updateXb();
						}else if(con==0){
							showAll();
						}else{
							System.out.println("输入有误！");
							showAll();
						}
					}else{
						System.out.println("修改失败！");
						selectXb();
					}
				}else if(ii==0){
					System.out.println("你选择了撤销");
					showAll();
				}else{
					System.out.println("输入有误！");
					showAll();
				}
			}else if(i==0){
				System.out.println("你选择了撤销");
				showAll();
			}else{
				System.out.println("输入有误");
				showAll();
			}
		}
	}





	public static void deleteXb() {
		System.out.println("你现在正在进行院系的删除...");
		System.out.println("所有院系如下：");
		//院系查询
		List<Xb> list=xbd.listXb();
		System.out.println("院系ID\t名称\t描述");
		for(Xb x:list){
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
		}
		System.out.println("你可以选择以上院系ID进行删除，请输入：");
		int index=sca.nextInt();
		System.out.println("获取："+index);
		Xb x=xbd.getOne(index);
		if(null==x.getXname()){
			System.out.println("输入有误，没有该院系，请重新输入");
			showAll();
		}else{
			System.out.println("你要删除的院系信息如下：");
			System.out.println("院系ID\t名称\t描述");
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
			
			System.out.println("确认删除吗？(1/是   0/否)");
			int confim=sca.nextInt();
			if(confim==1){
				System.out.println("正在删除...");
				int con=xbd.delete(index);
				if(con>0){
					System.out.println("删除成功！");
					selectXb();
				}else{
					System.out.println("删除失败");
					selectXb();
				}
			}else if(confim==0){
				System.out.println("你选择了撤销");
				showAll();
			}else{
				System.out.println("输入有误！");
				showAll();
			}
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
