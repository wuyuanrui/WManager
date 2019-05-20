package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.DeptinfoDao;
import com.wyr.daoimpl.PostInfoDao;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Postinfo;

public class PostInfoAction {
	static Scanner sca=new Scanner(System.in);
	static PostInfoDao pod=new PostInfoDao();
	static DeptinfoDao dfd=new DeptinfoDao();
	//选项菜单
		public static void showAll(){
			System.out.println("当前你所在的位置：》》首页》》岗位管理");
			System.out.println("1、岗位信息查看");
			System.out.println("2、岗位信息添加");
			System.out.println("3、岗位信息修改");
			System.out.println("4、岗位信息删除");
			System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
			int index=sca.nextInt();
			//选择
			switch(index){
				case 1:
					selectPo();
					break;
				case 2:
					addPo();
					break;
				case 3:
					updatePo();
					break;
				case 4:
					deletePo();
					break;
				default:
					System.out.println("输入有误！请重新输入");
					//返回根目录
					MenueAction.Menue();
					break;
			}
		}
		
		
		//查询
		public static void selectPo() {
			System.out.println("岗位信息如下：");
			List<Postinfo> list= pod.listPo();
			System.out.println("岗位ID\t岗位名称\t岗位描述\t所属部门");
			for(Postinfo p:list){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			back();
		}
		
		//删除岗位
		public static void deletePo() {
			System.out.println("你正在进行岗位的删除操作");
			System.out.println("你有如下岗位可以删除：");
			//查询岗位
			List<Postinfo> list= pod.listPo();
			System.out.println("岗位ID\t岗位名称\t岗位描述\t所属部门");
			for(Postinfo p:list){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			System.out.println("请输入要你删除的岗位ID：");
			int index=sca.nextInt();
			Postinfo po=pod.getOne(index);
			if(null==po){
				System.out.println("没有这个岗位哦！");
				showAll();
			}else{
				System.out.println("你要删除岗位信息如下：");
				System.out.println("岗位ID\t名称\t描述\t所属部门");
				System.out.println(po.getPid()+"\t"+po.getPname()+"\t"+po.getPremark()+"\t"+po.getDeptinfo().getDname());
				System.out.println("确定要删除吗？(1/是   0/否)");
				int con=sca.nextInt();
				if(con==1){
					int i=pod.deletePo(index);
					if(i>0){
						System.out.println("删除成功！");
						selectPo();
					}else{
						System.out.println("删除失败");
						selectPo();
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
		//修改岗位
		public static void updatePo() {
			System.out.println("你正在进行岗位的修改操作：");
			System.out.println("你有以下岗位可以修改：");
			//查询岗位
			List<Postinfo> list= pod.listPo();
			System.out.println("岗位ID\t岗位名称\t岗位描述\t所属部门");
			for(Postinfo p:list){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			System.out.println("请输入你要修改的岗位ID：");
			int index=sca.nextInt();//要修改的岗位ID
			Postinfo po=pod.getOne(index);
			if(null==po.getPname()){
				System.out.println("没有这个岗位哦！");
				showAll();
			}else{
				System.out.println("你要修改的岗位信息如下：");
				System.out.println("岗位ID\t名称\t描述\t所属部门");
				System.out.println(po.getPid()+"\t"+po.getPname()+"\t"+po.getPremark()+"\t"+po.getDeptinfo().getDname());
				System.out.println("你可以修改以下选项:");
				//以下有两种修改方式！
				System.out.println("1、岗位名称");
				System.out.println("2、岗位部门");
				int index1=sca.nextInt();
				Postinfo po1=new Postinfo();
				po1.setPid(index);
				
				if(index1==1){
					System.out.println("请输入岗位名称：");
					String name=sca.next();
					po1.setPname(name);
					int i=pod.updatePo(po1,index1);//修改岗位操作
					if(i>0){
						System.out.println("修改成功！");
						selectPo();
					}else{
						System.out.println("修改失败");
						selectPo();
					}
					
				}else if(index1==2){
					System.out.println("你有以下部门可以选择：");
					//查询出可以选择的部门
					List<Deptinfo> lists=dfd.listDep();
					
					System.out.println("你有如下部门可以选择：");
					System.out.println("部门ID\t部门名称\t部门描述");
					for(Deptinfo d:lists){
						System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
					};
					System.out.println("请输入岗位部门ID");
					int index2=sca.nextInt();
					
					
					Deptinfo des=dfd.getOne(index2);//获取部门
					System.out.println("请需要修的部门信息如下：");
					System.out.println("部门ID\t\t");
					System.out.println(des.getDid()+"\t"+des.getDname()+"\t"+des.getDremark());
					System.out.println("确定是这个部门吗？(1/是   0/否)");
					int confim=sca.nextInt();
					if(confim==1){
						Deptinfo de=new Deptinfo();
						de.setDid(index2);
						po1.setDeptinfo(de);
						int i=pod.updatePo(po1, index1);//修改操作
						if(i>0){
							System.out.println("修改成功！");
							selectPo();
						}else{
							System.out.println("修改失败");
							selectPo();
						}
					}else if(confim==0){
						showAll();
					}else{
						System.out.println("输入有误！");
						showAll();
					}
				}else{
					System.out.println("输入有误！");
					showAll();
				}
			}
			
		}
		
		//添加岗位
		public static void addPo() {
			System.out.println("你正在进行添加岗位信息操作..");
			//查询出可以选择的部门
			List<Deptinfo> list=dfd.listDep();
			
			System.out.println("你有如下部门可以选择：");
			System.out.println("部门ID\t部门名称\t部门描述");
			for(Deptinfo d:list){
				System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
			};
			System.out.println("请选择以上岗位的部门：");
			int index=sca.nextInt();
			//获取单个部门信息进行核对
			Deptinfo des=dfd.getOne(index);
			if(null==des.getDname()){
				System.out.println("没有这个部门哦！请重新添加");
				showAll();
			}else{
				System.out.println("以下是你要插入的岗位信息：");
				System.out.println("部门ID\t\t");
				System.out.println(des.getDid()+"\t"+des.getDname()+"\t"+des.getDremark());
				System.out.println("确定是这个部门吗？(1/是   0/否)");
				int confim=sca.nextInt();
				if(confim==1){
					System.out.println("请输入岗位名：");
					String name=sca.next();
					System.out.println("请输入岗位描述：");
					String  mark=sca.next();
					//封装部门和岗位信息
					Deptinfo de=new Deptinfo();
						de.setDid(index);
					Postinfo po=new Postinfo();
						po.setPname(name);
						po.setPremark(mark);
						po.setDeptinfo(de);
						int i=pod.addPo(po);//添加岗位信息
						if(i>0){
							System.out.println("添加成功！");
							selectPo();
						}else{
							System.out.println("添加失败！");
							selectPo();
						}
				}else if(confim==0){
					System.out.println("你选择了撤销！");
					showAll();//返回继续添加
				}else{
					System.out.println("输入有误！");
					showAll();//返回主页面
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