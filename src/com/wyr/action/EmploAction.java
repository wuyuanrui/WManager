package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.DeptinfoDao;
import com.wyr.daoimpl.PostInfoDao;
import com.wyr.daoimpl.ZwinfoDao;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Emplo;
import com.wyr.pojo.Postinfo;
import com.wyr.pojo.Zwinfo;
import com.wyr.daoimpl.EmploDao; 

public class EmploAction {
	static Scanner sca=new Scanner(System.in);
	static ZwinfoDao zwd=new ZwinfoDao();
	static PostInfoDao pod=new PostInfoDao();
	static DeptinfoDao dpd=new DeptinfoDao();
	static EmploDao emd=new EmploDao();
	
	//选项菜单
	public static void showAll(){
		System.out.println("当前你所在的位置：》》首页》》员工管理");
		System.out.println("1、员工信息查看");
		System.out.println("2、员工信息添加");
		System.out.println("3、员工信息修改");
		System.out.println("4、员工信息删除");
		System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
		
		int index=sca.nextInt();
		//选择
		switch(index){
			case 1:
				selectEm();
				break;
			case 2:
				addEm();
				break;
			case 3:
				updateEm();
				break;
			case 4:
				deleteEm();
				break;
			default:
				System.out.println("输入有误！请重新输入");
				//返回根目录
				MenueAction.Menue();
				break;
		}
	}
	
	//查询员工
	public static void selectEm() {
		System.out.println("你现在正在执行员工查询操作");
		System.out.println("所有员工如下所示：");
		List<Emplo> list=emd.lisEm();
		System.out.println("员工ID\t姓名\t性别\t年龄\t教育\t职位\t\t所属岗位\t\t所属部门");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t\t"+l.getZwinfo().getPostinfo().getPname()+"\t\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		}
		back();
	}
	
	//删除员工
	public static void deleteEm() {
		System.out.println("你正在执行删除操作");
		System.out.println("你有如下员工可以删除：");
		//查询所有的员工
		System.out.println("所有员工如下所示：");
		List<Emplo> list=emd.lisEm();
		System.out.println("员工ID\t姓名\t性别\t年龄\t教育\t职位\t所属岗位\t所属部门");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t"+l.getZwinfo().getPostinfo().getPname()+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		}
		System.out.println("请输入你要删除的员工ID：");
		int index=sca.nextInt();
		Emplo em=emd.getOne(index);
		if(null==em.getEname()){
			System.out.println("没有这个员工，请重新选择！");
			showAll();
		}else{
			System.out.println("当前你要删除的员工是：");
			System.out.println("员工ID\t姓名\t性别\t年龄\t教育\t职位\t所属岗位\t所属部门");
			System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t"+em.getZwinfo().getPostinfo().getPname()+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
			System.out.println("确认要删除？（1/是   0/否）");
			int index2=sca.nextInt();
			if(index2==1){
				int i=emd.deleteEm(index);
				if(i>0){
					System.out.println("删除成功");
					selectEm();
				}else{
					System.out.println("删除失败");
					selectEm();
				}
			}else if(index2==0){
				System.out.println("你撤销了操作");
				showAll();
			}else{
				System.out.println("输入错误");
				showAll();
			}
		}
	}
	
	
	
	//修改
	public static void updateEm() {
		System.out.println("你正在进行修改员工的操作，请根据以下步骤进行");
		System.out.println("当前你可以修改的员工有如下：");
		List<Emplo> list=emd.lisEm();
		System.out.println("员工ID\t姓名\t性别\t年龄\t教育\t职位\t所属岗位\t所属部门");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t"+l.getZwinfo().getPostinfo().getPname()+"\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		};
		System.out.println("请输入你要修改的员工ID：");
		int eid=sca.nextInt();
		Emplo em=emd.getOne(eid);
		if(null==em.getEname()){
			System.out.println("没有这个员工,请重新输入");
			showAll();
		}else{
			System.out.println("以下是你要修改的员工信息：");
			System.out.println("员工ID\t姓名\t性别\t年龄\t教育\t职位\t所属岗位\t所属部门");
			System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t"+em.getZwinfo().getPostinfo().getPname()+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
			System.out.println("确认修改？(1/是  0/否)");
			int i=sca.nextInt();
			if(i==1){
				updateSuccess(eid);
			}else if(i==0){
				System.out.println("你撤销了操作");
				showAll();
			}else{
				System.out.println("输入有误");
				showAll();
			}
		}
	}

	public static void updateSuccess(int eid) {
		System.out.println("现在你有如下操作：");
		System.out.println("1、修改员工普通信息");
		System.out.println("2、修改员工职位");
		
		System.out.println("请输入以上编号：");
		int i=sca.nextInt();
		
		Emplo em=new Emplo();
		em.setEid(eid);
		if(i==1){
			System.out.println("你正在修改员工普通信息...");
			System.out.println("请输入员工姓名：");
			String name=sca.next();
			System.out.println("请输入员工性别：");
			String sex=sca.next();
			System.out.println("请输入员工年龄：");
			int age=sca.nextInt();
			System.out.println("请输入员工学历：");
			String edu=sca.next();
			em.setEname(name);
			em.setEsex(sex);
			em.setEage(age);
			em.setEedu(edu);
			int m=emd.updateEm(em, i);
			if(m>0){
				System.out.println("修改成功！");
				selectEm();
			}else{
				System.out.println("修改失败！");
				selectEm();
			}
		}else if(i==2){
			//查职位
			System.out.println("职位信息如下所示：");
			List<Zwinfo> list=zwd.listZw();
			System.out.println("ID\t\t职位名称\t\t描述\t\t所属岗位\t\t所属部门");
			for(Zwinfo z:list){
				System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"\t"+z.getPostinfo().getPname()+"\t\t"+z.getPostinfo().getDeptinfo().getDname());
			}
			System.out.println("请输入以上职位ID:");
			int zid=sca.nextInt();
			System.out.println("你输入的是："+zid);
			Zwinfo zw=zwd.getOne(zid);
			
			if(null==zw.getZname()){
				System.out.println("没有这个职位，请重新输入");
				showAll();
			}else{
				System.out.println("你要修改成职位信息如下：");
				System.out.println("ID\t\t职位名称\t\t描述\t\t所属岗位\t\t所属部门");
				System.out.println(zw.getZid()+"\t\t"+zw.getZname()+"\t\t"+zw.getZremark()+"\t\t"+zw.getPostinfo().getPname()+"\t\t"+zw.getPostinfo().getDeptinfo().getDname());
				System.out.println("确认更改吗？（1/是   0/否）");
				int confim=sca.nextInt();
				if(confim==1){
					System.out.println("你正在修改员工职位信息...");
					em.setZwinfo(zw);//
					int iii=emd.updateEm(em, i);
					if(iii>0){
						System.out.println("修改成功");
						selectEm();
					}else{
						System.out.println("修改失败");
						selectEm();
					}
				}else if(confim==0){
					System.out.println("你撤销了操作");
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
//添加
	public static void addEm() {
		System.out.println("你正在进行添加员工的操作，请根据以下步骤进行");
		System.out.println("请输入员工姓名");
		String name=sca.next();
		System.out.println("请输入员工性别");
		String sex=sca.next();
		System.out.println("请输入员工年龄");
		int eage=sca.nextInt();
		System.out.println("请输入员工学历");
		String eedu=sca.next();
		//信息封装
		Emplo em=new Emplo();
		em.setEname(name);
		em.setEsex(sex);
		em.setEage(eage);
		em.setEedu(eedu);
		System.out.println("部门如下：");
		//部门查询
		List<Deptinfo> list=dpd.listDep();
		System.out.println("查询信息如下：");
		System.out.println("部门ID\t部门名称\t部门描述");
		for(Deptinfo d:list){
			System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
		};
		System.out.println("请选择以上其中部门ID:");
		int  did=sca.nextInt();
		Deptinfo de=dpd.getOne(did);
		if(null==de.getDname()){
			System.out.println("没有这个部门，请重新输入");
			showAll();
		}else{
			//岗位查看
			System.out.println("岗位信息如下：");
			List<Postinfo> lists= pod.listPo();
			System.out.println("岗位ID\t岗位名称\t岗位描述\t所属部门");
			for(Postinfo p:lists){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			System.out.println("请选择你以上其中一个岗位ID：");
			int pid=sca.nextInt();
			Postinfo po=pod.getOne(pid);
			if(null==po.getPname()){
				System.out.println("没有这个岗位，请重新输入");
				showAll();
			}else{
				//职位查看
				System.out.println("职位信息如下所示：");
				List<Zwinfo> listss=zwd.listZw();
				System.out.println("ID\t\t职位名称\t\t描述\t\t所属岗位\t\t所属部门");
				for(Zwinfo z:listss){
					System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"\t"+z.getPostinfo().getPname()+"\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				System.out.println("请输入以上其中一个职位ID：");
				int zid=sca.nextInt();
				Zwinfo zw=zwd.getOne(zid);
				if(null==zw.getZname()){
					System.out.println("没有这个职位，请重新输入");
					showAll();
				}else{
					//信息封装
					po.setDeptinfo(de);
					zw.setPostinfo(po);
					em.setZwinfo(zw);
					System.out.println("以下是你要添加的信息:");
					System.out.println("姓名\t性别\t年龄\t教育\t职位\t所属岗位\t所属部门");
					System.out.println(em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t"+em.getZwinfo().getPostinfo().getPname()+"\t"+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
					System.out.println("信息无误确认添加？(1/是  0/否)");
					int i=sca.nextInt();
					if(i==1){
						int ii=emd.add(em);
						if(ii>0){
							System.out.println("添加成功");
							 selectEm();
						}else{
							System.out.println("添加失败");
							selectEm();
						}
					}else if(i==0){
						System.out.println("撤销操作");
						showAll();
					}else{
						System.out.println("输入有误！");
						showAll();
					}
				}
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
