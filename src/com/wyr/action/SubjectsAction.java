package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.SubjectsDao;
import com.wyr.pojo.Subjects;
import com.wyr.pojo.Xb;

public class SubjectsAction {
	static Scanner sca=new Scanner(System.in);
	static SubjectsDao sbd=new SubjectsDao();
	

	//选项菜单
		public static void showAll(){
			System.out.println("当前你所在的位置：》》首页》》科目管理");
			System.out.println("1、科目信息查看");
			System.out.println("2、科目信息添加");
			System.out.println("3、科目信息修改");
			System.out.println("4、科目信息删除");
			System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
			int index=sca.nextInt();
			//选择
			switch(index){
				case 1:
					selectSu();
					break;
				case 2:
					addSu();
					break;
				case 3:
					updateSu();
					break;
				/*case 4:
					deleteSu();
					break;*/
				default:
					System.out.println("输入有误！请重新输入");
					//返回根目录
					MenueAction.Menue();
					break;
			}
		}
		
		//科目查询
		private static void selectSu() {
			System.out.println("你现在正在查看科目...");
			System.out.println("科目列表如下所示：");
			List<Subjects> list=sbd.listSu();
			System.out.println("科目ID\t名称\t描述");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			back();
		}


		private static void deleteSu() {
			System.out.println("你现在正在进行科目的删除...");
			System.out.println("所有科目如下：");
			//院系查询
			List<Subjects> list=sbd.listSu();
			System.out.println("科目ID\t名称\t描述");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			System.out.println("你可以选择以上科目ID进行删除，请输入：");
			int index=sca.nextInt();
			System.out.println("获取："+index);
			Subjects x=sbd.getOne(index);
			if(null==x.getSname()){
				System.out.println("输入有误，没有该科目，请重新输入");
				showAll();
			}else{
				System.out.println("你要删除的院系信息如下：");
				System.out.println("科目ID\t名称\t描述");
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
				
				System.out.println("确认删除吗？(1/是   0/否)");
				int confim=sca.nextInt();
				if(confim==1){
					System.out.println("正在删除...");
					int con=sbd.delete(index);
					if(con>0){
						System.out.println("删除成功！");
						selectSu();
					}else{
						System.out.println("删除失败");
						selectSu();
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


		private static void updateSu() {
			System.out.println("你正在进行科目修改操作：");
			System.out.println("现在你有如下科目可以修改：");
			List<Subjects> list=sbd.listSu();
			System.out.println("科目ID\t名称\t描述");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			System.out.println("请输入你要修改的科目ID");
			int index=sca.nextInt();
			
			Subjects x=sbd.getOne(index);
			if(null==x.getSname()){
				System.out.println("输出错误没有该院系，请重新输入");
				showAll();
			}else{
				System.out.println("你要修改的科目信息如下所示：");
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
				System.out.println("确认修改吗？(1/是  0/否)");
				int i=sca.nextInt();
				if(i==1){
					System.out.println("请输入你要修改的科目名称：");
					String name=sca.next();
					System.out.println("请输入你要修改的科目的描述：");
					String mark=sca.next();
					System.out.println("以下是你要修改后的字段");
					//封装
					Subjects su=new Subjects();
					su.setSid(index);
					su.setSname(name);
					su.setSremark(mark);
					
					
					System.out.println("科目名\t描述");
					System.out.println(su.getSname()+"\t"+su.getSremark());
					System.out.println("确认吗？（1/是  0/否）");
					int ii=sca.nextInt();
					if(ii==1){
						int a=sbd.update(su);
						if(a>0){
							System.out.println("修改成功！");
							selectSu();
						}else{
							System.out.println("修改失败！");
							selectSu();
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


		private static void addSu() {
			System.out.println("你正在添加科目");
			System.out.println("请输入要添加的科目名称：");
			String name=sca.next();
			System.out.println("请输入要添加的科目描述：");
			String mark=sca.next();
			System.out.println("如下是你要添加的信息：");
			System.out.println("科目\t描述");
			System.out.println(name+"\t"+mark);
			System.out.println("确认吗？（1/是   0/否）");
			int i=sca.nextInt();
			if(i==1){
				System.out.println("正在添加");
				//封装信息
				Subjects su=new Subjects();
				su.setSname(name);
				su.setSremark(mark);
				//
				int ii=sbd.add(su);
				if(ii>0){
					System.out.println("添加成功");
					selectSu();
				}else{
					System.out.println("添加失败");
					selectSu();
				}
			}else if(i==0){
				System.out.println("你撤销了操作");
				showAll();
			}else{
				System.out.println("输入有误请重新输入");
				showAll();
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
