package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.GradeDao;
import com.wyr.daoimpl.XbDao;
import com.wyr.pojo.Grade;
import com.wyr.pojo.Xb;

public class GradeAction {
	static Scanner sca=new Scanner(System.in);
	static GradeDao grd=new GradeDao();
	static XbDao xbd=new XbDao();
	
	//选项菜单
			public static void showAll(){
				System.out.println("当前你所在的位置：》》首页》》年级管理");
				System.out.println("1、年级信息查看");
				System.out.println("2、年级信息添加");
				System.out.println("3、年级信息修改");
				System.out.println("4、年级信息删除");
				System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
				int index=sca.nextInt();
				//选择
				switch(index){
					case 1:
						selectGr();
						break;
					case 2:
						addGr();
						break;
					case 3:
						updateGr();
						break;
					case 4:
						deleteGr();
						break;
					default:
						System.out.println("输入有误！请重新输入");
						//返回根目录
						MenueAction.Menue();
						break;
				}
			}

			
			
			
			//查询班年级-院系
			private static void selectGr() {
				System.out.println("你现在正在进行年级查询...");
				System.out.println("你有如下年级：");
				List<Grade> list=grd.listGr();
				System.out.println("年级ID\t名称\t\t描述\t所属院系");
				for(Grade g:list){
					System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
				}
				back();
				
			}




			//添加年级-院系
			private static void addGr() {
				System.out.println("你现在正在进行年级添加...");
				System.out.println("你有如下院系:");
				List<Xb> list=xbd.listXb();
				System.out.println("院系ID\t名称\t描述");
				for(Xb x:list){
					System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
				}
				System.out.println("请选择以上院系ID进行添加，请输入：");
				int index=sca.nextInt();
				Xb xb=xbd.getOne(index);
				if(null==xb.getXname()){
					System.out.println("输入有误，没有这个院系，请重新输入");
					showAll();
				}else{
					System.out.println("请输入年级的信息：");
					String name=sca.next();
					System.out.println("请输入年级的描述：");
					String mark=sca.next();
					//信息封装
					Grade gr=new Grade();
					gr.setGname(name);
					gr.setGremark(mark);
					gr.setXb(xb);
					System.out.println("以下是你要添加的信息：");
					System.out.println("年级ID\t名称\t\t描述\t所属院系");
					System.out.println(gr.getGname()+"\t\t"+gr.getGremark()+"\t"+gr.getXb().getXname());
					System.out.println("确定要添加吗？(1/是   0/否)");
					int con=sca.nextInt();
					if(con==1){
						System.out.println("正在添加");
						int ii=grd.add(gr);
						if(ii>0){
							System.out.println("添加成功！");
							 selectGr();
						}else{
							System.out.println("添加失败！");
							 selectGr();
						}
					}else if(con==0){
						System.out.println("你选择了撤销");
						showAll();
					}else{
						System.out.println("输入有误！,请重新输入");
						addGr();
					}
				}
			}

			//修改
			private static void updateGr() {
				System.out.println("你正在进行年级的修改操作...");
				//年级查询
				System.out.println("你有如下年级：");
				List<Grade> list=grd.listGr();
				System.out.println("年级ID\t名称\t\t描述\t所属院系");
				for(Grade g:list){
					System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
				}
				
				System.out.println("请输入你要修改的年级ID：");
				int index=sca.nextInt();
				Grade gr=grd.getOne(index);
				if(null==gr.getGname()){
					System.out.println("输入有误，没有这个年级");
					showAll();
				}else{
					System.out.println("以下是你要修改的年级信息：");
					System.out.println("年级ID\t名称\t\t描述\t所属院系");
					System.out.println(gr.getGid()+"\t"+gr.getGname()+"\t"+gr.getGremark()+"\t"+gr.getXb().getXname());
					System.out.println("确认要修改吗？（1/是   0/否）");
					int i=sca.nextInt();
					if(i==1){
						updateSuccess(index);
					}else if(i==0){
						System.out.println("你选择了撤销");
						showAll();
					}else{
						System.out.println("输入有误！");
						showAll();
					}
					
				}
			}
			public static void updateSuccess(int index) {
				Grade gr=new Grade();
				//要修改的年级ID
				gr.setGid(index);
				
				System.out.println("现在你有如下操作:");
				System.out.println("1、年级普通信息");
				System.out.println("2、年级所属院系");
				System.out.println("请输入你选择的编号：");
				int num=sca.nextInt();
				if(num==1){
					System.out.println("请输入你要修改的年级名称：");
					String name=sca.next();
					System.out.println("请输入你要修改的年级描述：");
					String mark=sca.next();
					gr.setGname(name);
					gr.setGremark(mark);
					System.out.println("确认修改吗？（1/是   0/否）");
					int confirm=sca.nextInt();
					if(confirm==1){
						int i=grd.update(gr, 1);
						if(i>0){
							System.out.println("修改成功！");
							selectGr();
						}else{
							System.out.println("修改失败！");
							selectGr();
						}
					}else if(confirm==0){
						System.out.println("你选择了撤销");
						showAll();
					}else{
						System.out.println("输入有误！");
						showAll();
					}
				}else if(num==2){
					System.out.println("你正在操作年级的院系修改...");
					System.out.println("现在你有如下院系可以选择：");
					//查找院系
					List<Xb> list=xbd.listXb();
					System.out.println("院系ID\t名称\t描述");
					for(Xb x:list){
						System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
					};
					System.out.println("请选择以上院系Id进行修改:");
					int indexx=sca.nextInt();
					Xb xb=xbd.getOne(indexx);
					//封装系别的信息
					gr.setXb(xb);
					
					if(null==xb){
						System.out.println("没有这个院系，请重新输入");
						showAll();
					}else{
						System.out.println("你要修改的院系如下所示：");
						System.out.println("院系ID\t名称\t描述");
						System.out.println(xb.getXid()+"\t"+xb.getXname()+"\t"+xb.getXremark());
						System.out.println("确认要修改吗?(1/是   0/否)");
						int con=sca.nextInt();
						if(con==1){
							System.out.println("正在修改...");
							int i=grd.update(gr, 2);
							if(i>0){
								System.out.println("修改成功！");
								selectGr() ;
							}else{
								System.out.println("修改失败！");
								selectGr() ;
							}
						}else if(con==0){
							System.out.println("你选择了撤销");
							showAll();
						}else{
							System.out.println("输入有误！");
							showAll();
						}
					}
				}else{
					System.out.println("输入有误！");
					showAll();
				}
				
			}




			//删除
			private static void deleteGr() {
				System.out.println("你正在进行班级的删除操作...");
				System.out.println("你有如下年级：");
				List<Grade> list=grd.listGr();
				System.out.println("年级ID\t名称\t\t描述\t所属院系");
				for(Grade g:list){
					System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
				};
				System.out.println("请输入你要删除的年级ID：");
				int index=sca.nextInt();
				Grade gr=grd.getOne(index);
				if(gr.getGname()==null){
					System.out.println("输入有误,没有这个年级，请重新输入");
					showAll();
				}else{
					System.out.println("你要删除的班级信息如下所示：");
					System.out.println("班级ID\t名称\t描述\t所属院系");
					System.out.println(gr.getGid()+"\t"+gr.getGname()+"\t"+gr.getGremark()+"\t"+gr.getXb().getXname());
					System.out.println("确认删除吗？（1/s是   0/否）");
					int confim=sca.nextInt();
					if(confim==1){
						System.out.println("正在删除");
						int ii=grd.delete(index);
						if(ii>0){
							System.out.println("删除成功！");
							selectGr();
						}else{
							System.out.println("删除失败");
							selectGr();
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
