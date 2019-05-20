package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.ClassesDao;
import com.wyr.daoimpl.GradeDao;
import com.wyr.daoimpl.XbDao;
import com.wyr.pojo.Classes;
import com.wyr.pojo.Grade;
import com.wyr.pojo.Xb;

public class ClassesAction {
	static Scanner sca=new Scanner(System.in);
	static ClassesDao cad=new ClassesDao();
	static GradeDao grd=new GradeDao();
	static XbDao xbd=new XbDao();
	
	//选项菜单
			public static void showAll(){
				System.out.println("当前你所在的位置：》》首页》》班级管理");
				System.out.println("1、班级信息查看");
				System.out.println("2、班级信息添加");
				System.out.println("3、班级信息修改");
				System.out.println("4、班级信息删除");
				System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
				int index=sca.nextInt();
				//选择
				switch(index){
					case 1:
						selectCla();
						break;
					case 2:
						addCla();
						break;
					case 3:
						updateCla();
						break;
					/*case 4:
						deleteCla();
						break;*/
					default:
						System.out.println("输入有误！请重新输入");
						//返回根目录
						MenueAction.Menue();
						break;
				}
			}
			

	public static void selectCla() {
		System.out.println("班级信息如下：");
		List<Classes> list= cad.listCla();
		System.out.println("班级ID\t名称\t描述\t所属年级");
		for(Classes p:list){
			System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
		}
		back();	
	}
//添加
	public static void addCla() {
		System.out.println("你正在进行添加年级信息操作..");
		//查询出可以选择的年级
		System.out.println("你有如下年级：");
		List<Grade> list=grd.listGr();
		System.out.println("年级ID\t名称\t描述\t所属系院");
		for(Grade g:list){
			System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
		}
		System.out.println("请选择以上的年级ID：");
		int index=sca.nextInt();
		//获取单个年级信息进行核对
		
		Grade des=grd.getOne(index);
		if(des.getGname()==null){
			System.out.println("没有这个年级哦！请重新添加");
			showAll();
		}else{
			System.out.println("以下是你添加的信息：");
			System.out.println("年级ID\t名称\t描述\t所属院系");
			System.out.println(des.getGid()+"\t"+des.getGname()+"\t"+des.getGremark()+"\t"+des.getXb().getXname());
			System.out.println("确定是这个年级吗？(1/是   0/否)");
			int con=sca.nextInt();
			if(con==1){
				//院系查询
				System.out.println("院系列表如下所示：");
				List<Xb> lists=xbd.listXb();
				System.out.println("院系ID\t名称\t描述");
				for(Xb x:lists){
					System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
				}
				System.out.println("请选择以上院系id：");
				int ins=sca.nextInt();
				
				Xb xb=xbd.getOne(ins);
				if(xb.getXname()==null){
					System.out.println("没有这个院系请重新输入！");
					showAll();
				}else{
					System.out.println("以下是你选择的院系信息：");
					System.out.println("院系ID\t名称\t描述");
					System.out.println(xb.getXid()+"\t"+xb.getXname()+"\t"+xb.getXremark());
					System.out.println("确定添加吗？（1/是   0/否）");
					int cono=sca.nextInt();
					
					if(cono==1){
						System.out.println("请输入班级名称：");
						String name=sca.next();
						System.out.println("请输入班级描述：");
						String  mark=sca.next();
						//封装部门和岗位信息
						Classes cla=new Classes();
						cla.setCname(name);
						cla.setCremark(mark);
						//封装信息
						des.setXb(xb);
						cla.setGrade(des);
						int i=cad.addCla(cla);
						if(i>0){
							System.out.println("添加成功！");
							selectCla();
						}else{
							System.out.println("添加失败");
							selectCla();
						}
					}else if(con==0){
						System.out.println("你撤销了！");
						showAll();
					}else{
						System.out.println("输入有误！");
						showAll();
					}
				}
			}else if(con==0){
				System.out.println("你选择了撤销");
				showAll();
			}else{
				System.out.println("输入有误");
				showAll();
			}
			
		}
				
	}
//修改
	public static void updateCla() {
		System.out.println("你正在进行班级的修改操作：");
		System.out.println("你有以下班级可以修改：");	
		List<Classes> list= cad.listCla();
		System.out.println("班级ID\t名称\t描述\t所属年级");
		for(Classes p:list){
			System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
		};
		System.out.println("请输入以上的班级Id进行修改：");
		int index=sca.nextInt();
		
		Classes cla=cad.getOne(index);
		if(cla.getCname()==null){
			System.out.println("没有这个班级，请重新输入");
			showAll();
		}else{
			System.out.println("你要修改的班级信息如下：");
			System.out.println("班级ID\t名称\t描述\t所属年级");
			System.out.println(cla.getCid()+"\t"+cla.getCname()+"\t"+cla.getCremark()+"\t"+cla.getGrade().getGname());
			System.out.println("确定修改吗？(1/是   0/否)");
			int confirm=sca.nextInt();
			if(confirm==1){
				System.out.println("请输入你要更改的班级名称：");
				String name=sca.next();
				System.out.println("请输入你要更改的班级描述：");
				String mark=sca.next();
				
				Classes clas=new Classes();
				clas.setCid(index);
				clas.setCname(name);
				clas.setCremark(mark);
				int ii=cad.update(clas);
				if(ii>0){
					System.out.println("修改成功！");
					selectCla();
				}else{
					System.out.println("修改失败！");
					selectCla();
				}
			}else if(confirm==0){
				System.out.println("你选择了撤销");
				showAll();
			}else{
				System.out.println("输入有误！");
				showAll();
			}
		}
				
	}

	public static void deleteCla() {
		System.out.println("你正在进行班级的删除操作");
		System.out.println("你有如下班级可以删除：");
		//查询岗位
		List<Classes> list= cad.listCla();
		System.out.println("班级ID\t名称\t描述\t所属年级");
		for(Classes p:list){
			System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
		}
		
		System.out.println("请输入要你删除的班级ID：");
		int index=sca.nextInt();
		Classes po=cad.getOne(index);
		if(po.getClass()==null){
			System.out.println("没有这个班级哦！");
			showAll();
		}else{
			System.out.println("你要删除班级信息如下：");
			System.out.println("班级ID\t名称\t描述\t所属年级");
			System.out.println(po.getCid()+"\t"+po.getCname()+"\t"+po.getCremark()+"\t"+po.getGrade().getGname());
			System.out.println("确定要删除吗？(1/是   0/否)");
			int con=sca.nextInt();
			if(con==1){
				int i=cad.delete(con);
				if(i>0){
					System.out.println("删除成功！");
					selectCla();
				}else{
					System.out.println("删除失败");
					selectCla();
				}
			}else if(con==0){
				System.out.println("你选择了撤销！");
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
