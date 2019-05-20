package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.DeptinfoDao;
import com.wyr.daoimpl.PostInfoDao;
import com.wyr.daoimpl.ZwinfoDao;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Postinfo;
import com.wyr.pojo.Zwinfo;

public class ZwinfoAction {
	static Scanner sca=new Scanner(System.in);
	static ZwinfoDao zwd=new ZwinfoDao();
	static DeptinfoDao dpd=new DeptinfoDao();
	static PostInfoDao pod=new PostInfoDao();
	
	//选项菜单
			public static void showAll(){
				System.out.println("当前你所在的位置：》》首页》》职位管理");
				System.out.println("1、职位信息查看");
				System.out.println("2、职位信息添加");
				System.out.println("3、职位信息修改");
				System.out.println("4、职位信息删除");
				System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
				
				int index=sca.nextInt();
				//选择
				switch(index){
					case 1:
						selectZw();
						break;
					case 2:
						addZw();
						break;
					case 3:
						updateZw();
						break;
					case 4:
						deleteZw();
						break;
					default:
						System.out.println("输入有误！请重新输入");
						//返回根目录
						MenueAction.Menue();
						break;
				}
			}
			
			//查询职位信息
			public static void selectZw() {
				System.out.println("职位信息如下所示：");
				List<Zwinfo> list=zwd.listZw();
				System.out.println("ID\t\t职位名称\t\t描述\t\t所属岗位\t\t所属部门");
				for(Zwinfo z:list){
					System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"\t\t"+z.getPostinfo().getPname()+"\t\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				back();
			}

			
			//删除职位信息
			public static void deleteZw() {
				System.out.println("你正在执行职位的删除操作");
				System.out.println("如下所示你可以删除如下职位信息：");
				List<Zwinfo> list=zwd.listZw();
				System.out.println("ID\t\t职位名称\t\t描述\t\t所属岗位\t\t所属部门");
				for(Zwinfo z:list){
					System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"t\t"+z.getPostinfo().getPname()+"t\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				System.out.println("请输入你要删除的职位ID:");
				int index=sca.nextInt();
				Zwinfo zw=zwd.getOne(index);
				if(null==zw.getZname()){
					System.out.println("没有这个职位请重新选择：");
					showAll();
				}else{
					System.out.println("你要删除的职位如下所示：");
					System.out.println("ID\t职位名称\t描述\t所属岗位\t所属部门");
					System.out.println(zw.getZid()+"\t"+zw.getZname()+"\t"+zw.getZremark()+"\t"+zw.getPostinfo().getPname()+"\t"+zw.getPostinfo().getDeptinfo().getDname());
					System.out.println("确定删除吗？（1/是  0/否）");
					int index1=sca.nextInt();
					if(index1==1){
						System.out.println("正在删除...");
						int i=zwd.deleteZw(index);
						if(i>0){
							System.out.println("删除成功！");
							selectZw();
						}else{
							System.out.println("删除失败！");
							selectZw();
						}
					}else if(index1==0){
						System.out.println("你撤销删除了！");
						showAll();
					}else{
						System.out.println("输入有误！");
						showAll();
					}
				}
			}

			
			//修改职位-岗位-部门
			public static void updateZw() {
				System.out.println("你正在执行职位的修改操作");
				System.out.println("如下所示，你有如下职位可以修改：");
				List<Zwinfo> list=zwd.listZw();
				System.out.println("ID\t职位名称\t描述\t所属岗位\t所属部门");
				for(Zwinfo z:list){
					System.out.println(z.getZid()+"\t"+z.getZname()+"\t"+z.getZremark()+"\t"+z.getPostinfo().getPname()+"\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				System.out.println("请输入你要修改的职位ID");
				int zid=sca.nextInt();
				Zwinfo zw=zwd.getOne(zid);
				if(null==zw.getZname()){
					System.out.println("没有这个职位！请重新修改..");
					showAll();			
				}else{
					System.out.println("以下是你要修改的 职位信息：");
					System.out.println("职位ID\t名称\t描述\t所属岗位\t所属部门");
					System.out.println(zw.getZid()+"\t"+zw.getZname()+"\t"+zw.getZremark()+"\t"+zw.getPostinfo().getPname()+"\t"+zw.getPostinfo().getDeptinfo().getDname());
					System.out.println("确认修改吗？（1/是    0/否）");
					int confim=sca.nextInt();
					if(confim==1){
						//修改操作
						updateSuccess(zid);
						
					}else if(confim==0){
						System.out.println("你选择了撤销");
						showAll();
					}else{
						System.out.println("输入有误！");
						showAll();
					}
				}
			}
			
			
			
			public static void updateSuccess(int zid) {
				System.out.println("你有如下修改项：");
				System.out.println("1、修改职位信息");
				System.out.println("2、修改所属岗位");
				System.out.println("请输入你的选择(1 2)");
				int i=sca.nextInt();
				Zwinfo zw=new Zwinfo();
				zw.setZid(zid);//传递修改的字段
				if(i==1){
					System.out.println("你正在修改职位的信息..");
					System.out.println("请输入你要更改的职位名称：");
					String name=sca.next();
					System.out.println("请输入你要修改的职位描述：");
					String mark=sca.next();
					//封装用户输入的信息
					zw.setZname(name);
					zw.setZremark(mark);
					//修改部分字段
					int n=zwd.updateZw(zw, i);
					if(n>0){
						System.out.println("修改成功！");
						selectZw();
					}else{
						System.out.println("修改失败！");
						showAll();;
					}
				}else if(i==2){
					System.out.println("你正在修改职位的所属岗位...");
					System.out.println("现在你有如下岗位供你选择：");
					//岗位查询
					List<Postinfo> list= pod.listPo();
					System.out.println("岗位ID\t岗位名称\t岗位描述\t所属部门");
					for(Postinfo p:list){
						System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
					}
					System.out.println("请输入你要更改的所属岗位ID：");
					int pid=sca.nextInt();
					System.out.println("以下是你选择的所属岗位信息");
					Postinfo po=pod.getOne(pid);
					if(null==po.getPname()){
						System.out.println("没有这个岗位！请重新选择");
						showAll();
					}else{
						System.out.println("岗位ID\t名称\t描述\t所属部门");
						System.out.println(po.getPid()+"\t"+po.getPname()+"\t"+po.getPremark()+"\t"+po.getDeptinfo().getDname());
						System.out.println("确认修改吗？(1/是  0/否)");
						int confim=sca.nextInt();
						//封装信息
						zw.setPostinfo(po);
						if(confim==1){
							
							int ii=zwd.updateZw(zw, i);
							if(ii>0){
								System.out.println("修改成功！");
								selectZw();
							}else{
								System.out.println("修改失败");
								selectZw();
							}
							
						}else if(confim==0){
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

			
			//添加职位，岗位，部门
			public static void addZw() {
				System.out.println("你现在正在进行职位信息的添加");
				System.out.println("请根据以下步骤依次添加信息：");
				System.out.println("现在你有以下的部门可以选择：");
				//部门查询
				List<Deptinfo> list=dpd.listDep();
				System.out.println("部门ID\t部门名称\t部门描述");
				for(Deptinfo d:list){
					System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
				};
				System.out.println("请输入职位的部门ID:");
				int dep=sca.nextInt();
				//查询是否有着个部门
				Deptinfo de=dpd.getOne(dep);
				if(null==de.getDname()){
					System.out.println("没有这个部门，请重新添加！");
					showAll();
				}else{
					//查询岗位
					System.out.println("现在你有以下岗位可以选择：");
					List<Postinfo> list1= pod.listPo();
					System.out.println("岗位ID\t岗位名称\t岗位描述\t所属部门");
					for(Postinfo p:list1){
						System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
					}
					System.out.println("请输入你要选择的岗位ID:");
					int po=sca.nextInt();
					Postinfo post=pod.getOne(po);
					if(null==post.getPname()){
						System.out.println("没有这个岗位哦！请重新输入");
						showAll();
					}else{
						System.out.println("以下是你选中的部门信息：");
						System.out.println("部门ID\t名称\t描述");
						System.out.println(de.getDid()+"\t"+de.getDname()+"\t"+de.getDremark());
						System.out.println("以下是你选中的岗位信息：");
						System.out.println("岗位ID\t名称\t描述");
						System.out.println(post.getPid()+"\t"+post.getPname()+"\t"+post.getPremark());
						System.out.println("继续吗？（1/是  0/否）");
						int confim=sca.nextInt();
						if(confim==1){
							//继续添加信息操作
							System.out.println("现在录入职位的信息...");
							System.out.println("请输职位名称:");
							String name =sca.next();
							System.out.println("请输入职位的描述：");
							String mark=sca.next();
							System.out.println("添加的信息如下所示：");
							System.out.println("职位\t岗位\t部门");
							System.out.println(name+"\t"+post.getPname()+"\t"+de.getDname());
							System.out.println("确认无误吗？（1/是  0/否 ）");
							int i=sca.nextInt();
							System.out.println("正在录入信息");
							//封装信息
							Zwinfo zw=new Zwinfo();
							
							zw.setZname(name);
							zw.setZremark(mark);
							//岗位封装部门信息
							post.setDeptinfo(de);
							//职位封装岗位信息
							zw.setPostinfo(post);
							//添加职位信息
							int n=zwd.addZw(zw);
							
							if(n>0){
								System.out.println("添加成功！");
								selectZw();
							}else{
								System.out.println("添加失败!");
								selectZw();
							}
						}else if(confim==0){
							System.out.println("你选择了撤销！");
							showAll();
						}else{
							System.out.println("输入有误！");
							showAll();
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
