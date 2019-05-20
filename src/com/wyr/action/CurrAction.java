package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.ClassesDao;
import com.wyr.daoimpl.CurrDao;
import com.wyr.daoimpl.EmploDao;
import com.wyr.daoimpl.SubjectsDao;
import com.wyr.daoimpl.XbDao;
import com.wyr.pojo.Classes;
import com.wyr.pojo.Curr;
import com.wyr.pojo.Emplo;
import com.wyr.pojo.Subjects;
import com.wyr.pojo.Xb;

public class CurrAction {
	static Scanner sca=new Scanner(System.in);
	static CurrDao cud=new CurrDao();
	static EmploDao emd=new EmploDao();
	static ClassesDao cld=new   ClassesDao();
	static SubjectsDao sbd=new SubjectsDao();
	static XbDao xbd=new XbDao();
	static ClassesDao cad=new ClassesDao();
	
	
	//选项菜单
	public static void showAll(){
		System.out.println("当前你所在的位置：》》首页》》课程管理");
		System.out.println("1、课程信息查看");
		System.out.println("2、课程信息添加");
		System.out.println("3、课程信息修改");
		System.out.println("4、课程信息删除");
		System.out.println("请输入以上选项（1 2 3 4）否则返回根目录");
		int index=sca.nextInt();
		//选择
		switch(index){
			case 1:
				selectCur();
				break;
			case 2:
				addCur();
				break;
			case 3:
				updateCur();
				break;
			case 4:
				deleteCur();
				break;
			default:
				System.out.println("输入有误！请重新输入");
				//返回根目录
				MenueAction.Menue();
				break;
		}
	}

	//课程-员工-班级-科目
	public static void selectCur() {
		System.out.println("你现在正在进行课程的查询操作：");
		System.out.println("现在你可以查询一下操作：");
		System.out.println("1、全查询");
		System.out.println("2、模糊查询");
		System.out.println("3、课时量查询");
		System.out.println("4、统计科目人数");
		System.out.println("请输入以上编号（1 2 3 4 ）否则返回根目录");
		//查询
		 selectSuccess();
	}
	
	public static void selectSuccess(){
		int i=sca.nextInt();
		if(i==1){
			System.out.println("课程信息如下：");
			List<Curr> list=cud.listCu();
			System.out.println("课程ID\t课时量\t教师\t所属职位\t所属岗位\t所属部门\t所属系\t所属年级\t\t指导班级\t课程名");
			for(Curr c:list){
				System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
			}
			back();
		}else if(i==2){
			//模糊查询
			System.out.println("你正在进行模糊查询：");
			System.out.println("你有如下操作：");
			System.out.println("1、教师");
			System.out.println("2、系别");
			System.out.println("3、年级");
			System.out.println("4、科目");
			System.out.println("请输入以上编号（1 2 3 4 ）否则返回根目录");
			int ii=sca.nextInt();
			if(ii==1){
				//模糊教师名
				System.out.println("模糊查询教师名字：");
				System.out.println("请输入教师名字");
				String name=sca.next();
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("没有内容！");
					selectCur();//返回
				}else{
					System.out.println("模糊查询教师信息如下：");
					System.out.println("教师\t\t课程ID\t课时量\t所属职位\t所属岗位\t所属部门\t所属系\t所属年级\t\t指导班级\t课程名");
					for(Curr c:list){
						System.out.println(c.getEmplo().getEname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
					}
					back();
				}
			}else if(ii==2){
				//模糊查询系别
				System.out.println("模糊查询系别：");
				//系别查询
				System.out.println("你有如下系别可以查询：");
				List<Xb> listx=xbd.listXb();
				System.out.println("院系ID\t名称\t描述");
				for(Xb x:listx){
					System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
				}
				System.out.println("请输入以上系别名称：");
				String name=sca.next();
				
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("没有内容！");
					 selectCur();//返回
				}else{
					System.out.println("模糊查询系别信息如下：");
					System.out.println("所属系\t\t课程ID\t课时量\t教师\t所属职位\t所属岗位\t所属部门\t所属年级\t\t指导班级\t课程名");
					for(Curr c:list){
						System.out.println(c.getClasses().getGrade().getXb().getXname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
					}
					back();
				}
			}else if(ii==3){
				//模糊查询年级
				System.out.println("模糊查询年级：");
				//班级查询
				System.out.println("你有以下年级可以查询：");
				List<Classes> lists= cad.listCla();
				System.out.println("班级ID\t名称\t描述\t所属年级");
				for(Classes p:lists){
					System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
				}
				System.out.println("请输入以上的年级名称：");
				String name=sca.next();
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("没有内容！");
					 selectCur();//返回
				}else{
					System.out.println("模糊查询系别信息如下：");
					System.out.println("所属年级\t\t课程ID\t课时量\t教师\t所属职位\t所属岗位\t所属部门\t所属系\t\t指导班级\t课程名");
					for(Curr c:list){
						System.out.println(c.getClasses().getGrade().getGname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
					}
					back();
				}
			}else if(ii==4){
				//模糊查询科目
				System.out.println("模糊查询科目：");
				System.out.println("你有以下科目可以查询：");
				List<Subjects> lists=sbd.listSu();
				System.out.println("科目ID\t名称\t描述");
				for(Subjects x:lists){
					System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
				}
				System.out.println("请输入以上科目名称：");
				String name=sca.next();
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("没有内容！");
					 selectCur();//返回
				}else{
					System.out.println("模糊查询科目信息如下：");
					System.out.println("课程名\t\t课程ID\t课时量\t教师\t所属职位\t所属岗位\t所属部门\t所属系\t所属年级\t\t指导班级");
					for(Curr c:list){
						System.out.println(c.getSubjects().getSname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname());
					}
					back();
				}
			}
		}else if(i==3){
			//课时量查询
			System.out.println("请输入课时量的范围：");
			System.out.println("请输入课时量的第一个值：");
			int num=sca.nextInt();
			System.out.println("请输入课时量的第二个值：");
			int num1=sca.nextInt();
			System.out.println("正在查询课时量在  "+num+"到"+num1+"的范围");
			List<Curr> list=cud.listCu(num,num1);	
			if(list.isEmpty()){
				System.out.println("没有内容！");
				selectCur();
			}else{
				System.out.println("课程信息如下：");
				System.out.println("课程ID\t课时量\t教师\t所属职位\t所属岗位\t所属部门\t所属系\t所属年级\t\t指导班级\t课程名");
				for(Curr c:list){
					System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
				}
				back();
			}
		}else if(i==4){
			System.out.println("你正在查询每个科目的上课人数：");
			System.out.println("你有如下科目可以查询：");
			//查询可查询的科目
			List<Subjects> listsu=sbd.listSu();
			System.out.println("科目ID\t名称\t描述");
			for(Subjects x:listsu){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			System.out.println("请输入以上科目的名称进行查询统计：");
			String name=sca.next();
			
			//查询科目的匹配
			String res=null;
			for(Subjects s:listsu){
				if(name.equals(s.getSname())){
					System.out.println("正在查询："+s.getSname()+"科目统计");
					List<Curr> lists=cud.listCu(name);
					if(lists.isEmpty()){
						System.out.println(s.getSname()+"  该科目暂时没人上课");
						selectCur();
					}else{
						System.out.println("当前上"+s.getSname()+"科目的人数:"+lists.size()+"人");
						System.out.println("人数\t教师\t所属职位\t所属岗位\t所属部门\t所属系\t所属年级\t\t指导班级\t课程名");
						for(Curr c:lists){
							System.out.println(c.getCount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
						}
						back();
						break;
					}
				}else{
					res=name;
				}
			}
			System.out.println("对不起，没有 "+name+" 科目的内容！");
			selectCur();
		}else{
			MenueAction.Menue();
		}
	}
	
	//添加课程-员工-班级-科目
	private static void addCur() {
		System.out.println("你正在操作课程的添加操作：");
		System.out.println("你有如下教师可以添加：");
		List<Emplo> list=emd.lisEm();
		System.out.println("教师ID\t姓名\t性别\t年龄\t教育\t职位\t\t所属岗位\t\t所属部门");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t\t"+l.getZwinfo().getPostinfo().getPname()+"\t\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		}
		System.out.println("请输入以上教师ID添加到课程：");
		int index=sca.nextInt();
		//查询出来的员工信息
		Emplo em=emd.getOne(index);
		if(em.getEname()==null){
			System.out.println("没有该教师，请重新输入");
			showAll();
		}else{
			System.out.println("以下是你选中的教师信息：");
			System.out.println("教师ID\t姓名\t性别\t年龄\t教育\t职位\t\t所属岗位\t\t所属部门");
			System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t\t"+em.getZwinfo().getPostinfo().getPname()+"\t\t"+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
			System.out.println("继续吗？(1/是   0/否)");
			int con=sca.nextInt();
			if(con==1){
				System.out.println("你有以下班级可以添加：");
				List<Classes> lists= cld.listCla();
				System.out.println("班级ID\t名称\t描述\t所属年级");
				for(Classes p:lists){
					System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
				}
				System.out.println("请输入以上班级ID进行添加：");
				int indexx=sca.nextInt();
				//班级信息查询
				Classes cla=cld.getOne(indexx);
				if(cla.getCname()==null){
					System.out.println("没有这个班级，请重新输入");
					addCur();
				}else{
					System.out.println("你选择的班级信息如下所示：");
					System.out.println("班级ID\t名称\t描述\t所属年级");
					System.out.println(cla.getCid()+"\t"+cla.getCname()+"\t"+cla.getCremark()+"\t"+cla.getGrade().getGname());
					System.out.println("继续吗？（1/是   0/否）");
					int cofin=sca.nextInt();
					if(cofin==1){
						System.out.println("你要选择科目信息如下：");
						List<Subjects> listss=sbd.listSu();
						System.out.println("科目ID\t名称\t描述");
						for(Subjects x:listss){
							System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
						}
						System.out.println("请输入以上的科目名称ID：");
						int confim=sca.nextInt();
						//班级信息查询
						Subjects sub=sbd.getOne(confim);
						if(sub.getSname()==null){
							System.out.println("输入有误，没有这个科目，请重新输入");
							showAll();
						}else{
							System.out.println("你要添加的科目信息如下：");
							System.out.println("科目ID\t名称\t描述");
							System.out.println(sub.getSid()+"\t"+sub.getSname()+"\t"+sub.getSremark());
							System.out.println("继续吗？(1/是  0/否)");
							int nn=sca.nextInt();
							if(nn==1){
								System.out.println("现在请输入课程的课时：");
								//所有信息封装
								int count=sca.nextInt();
								Curr cu=new Curr();
								cu.setRcount(count);
								cu.setEmplo(em);
								cu.setSubjects(sub);
								cu.setClasses(cla);
								System.out.println("以下是你要添加的课程的所有信息：");
								System.out.println("课程名\t\t教师\t\t班级\t\t科目");
								System.out.println(cu.getRcount()+"\t\t"+cu.getEmplo().getEname()+"\t\t"+cu.getClasses().getCname()+"\t\t"+cu.getSubjects().getSname());
								System.out.println("确认要添加吗？(1/是  0/否)");
								int mm=sca.nextInt();
								if(mm==1){
									int i=cud.addCu(cu);
									if(i>0){
										System.out.println("添加成功！");
										selectCur();
									}else{
										System.out.println("添加失败！");
										selectCur();
									}
								}else if(mm==0){
									System.out.println("你选择了撤销");
									showAll();
								}else{
									System.out.println("输入有误！");
									showAll();
								}
							}else if(nn==0){
								System.out.println("你选择了撤销！");
								showAll();
							}else{
								System.out.println("输入有误！");
								addCur();
							}
						}
					}else if(cofin==0){
						System.out.println("你选择了撤销！");
						showAll();
					}else{
						System.out.println("输入有误，请重新输入");
						showAll();
					}
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

	//修改课程时
	//教师
	//班级
	//科目
	private static void updateCur() {
		System.out.println("你正在进行课程的修改操作...");
		System.out.println("你有如下课程可以修改：");
		//课程查询
		List<Curr> list=cud.listCu();
		System.out.println("课程ID\t课时量\t教师\t所属职位\t所属岗位\t所属部门\t所属系\t所属年级\t\t指导班级\t课程名");
		for(Curr c:list){
			System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
		}
		//
		System.out.println("请输入以上课程ID进行修改信息：");
		int index=sca.nextInt();
		//获取课程信息
		Curr cu=cud.getOne(index);
		if(cu.getEmplo()==null){
			System.out.println("没有该课程请重新输入");
			showAll();
		}else{
			System.out.println("你要修改的课程信息如下：");
			System.out.println("课程ID\t课时量\t教师\t课程名");
			System.out.println(cu.getRid()+"\t"+cu.getRcount()+"\t"+cu.getEmplo().getEname()+"\t"+cu.getSubjects().getSname());
			System.out.println("继续吗？(1/是  0/否)");
			int confim=sca.nextInt();
			if(confim==1){
				successUpte(index);
			}else if(confim==0){
				System.out.println("你选择了撤销！");
				showAll();
			}else{
				System.out.println("输入有误！");
				showAll();
			}
		}
		
		
		
	}

	public static void successUpte(int index) {
		System.out.println("现在你有如下操作");
		System.out.println("1、课程课时");
		System.out.println("2、课程班级");
		System.out.println("3、课程科目");
		System.out.println("4、课程教师");
		System.out.println("请输入以上以上编号（1 2 3 4 ）否则返回根目录");
		int i=sca.nextInt();
		//封装信息
		Curr cu=new Curr();
		cu.setRid(index);
		
		if(i==1){
			System.out.println("你正在修改课程的课时：");
			System.out.println("请输入你要修改的课时：");
			int count=sca.nextInt();
			cu.setRcount(count);
			int is=cud.updateCu(cu, i);
			if(is>0){
				System.out.println("修改成功！");
				selectCur();
			}else{
				System.out.println("修改失败！");
				selectCur();
			}
		}else if(i==2){
			System.out.println("你正在输入课程的班级：");
			System.out.println("你有如下课程班级可以更改：");
			//班级查询
			List<Classes> list= cld.listCla();
			System.out.println("班级ID\t名称\t描述\t所属年级");
			for(Classes p:list){
				System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
			};
			System.out.println("请输入以上你要修改的课程Id：");
			int nm=sca.nextInt();
			Classes cla=cld.getOne(nm);
			if(cla.getCname()==null){
				System.out.println("输入有误，没有你输入的课程");
				showAll();
			}else{
				System.out.println("你输入的课程信息如下：");
				System.out.println("班级ID\t名称\t描述\t所属年级");
				System.out.println(cla.getCid()+"\t"+cla.getCname()+"\t"+cla.getCremark()+"\t"+cla.getGrade().getGname());
				System.out.println("确认吗？（1/是   0/否）");
				int nn=sca.nextInt();
				if(nn==1){
					System.out.println("正在修改课程的班级信息");
					cu.setClasses(cla);
					
					int ii=cud.updateCu(cu, i);
					if(ii>0){
						System.out.println("修改成功！");
						 selectCur();
					}else{
						System.out.println("修改失败！");
						 selectCur();
					}
				}else if(nn==0){
					System.out.println("你撤销了操作");
					showAll();
				}else{
					System.out.println("输入有误！");
					showAll();
				}
			}
		}else if(i==3){
			System.out.println("你正在进行课程的科目修改：");
			System.out.println("你有如下科目可以修改：");
			//科目查询
			List<Subjects> list=sbd.listSu();
			System.out.println("科目ID\t名称\t描述");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			};
			//
			System.out.println("请输入要更改的科目ID：");
			int nn=sca.nextInt();
			Subjects sub=sbd.getOne(nn);
			if(sub.getSname()==null){
				System.out.println("输入有误，没有该科目");
				showAll();
			}else{
				System.out.println("以下是你要修改的课程科目信息：");
				System.out.println("科目ID\t名称\t描述");
				System.out.println(sub.getSid()+"\t"+sub.getSname()+"\t"+sub.getSremark());
				System.out.println("确认修改？（1/是  0/否）");
				int coni=sca.nextInt();
				if(coni==1){
					System.out.println("正在修改课程科目信息");
					cu.setSubjects(sub);
					int confirm=cud.updateCu(cu, i);
					if(confirm>0){
						System.out.println("修改成功！");
						selectCur();
					}else{
						System.out.println("修改失败！");
						selectCur();
					}
					
				}else if(coni==0){
					System.out.println("你选择了撤销！");
					showAll();
				}else{
					System.out.println("输入有误！");
					showAll();
				}
			}
		}else if(i==4){
			System.out.println("你正在进行课程的教师修改：");
			System.out.println("你有如下科目可以修改：");
			//教师查询
			System.out.println("所有教师如下所示：");
			List<Emplo> list=emd.lisEm();
			System.out.println("员工ID\t姓名\t性别\t年龄\t教育\t职位\t\t所属岗位\t\t所属部门");
			for(Emplo l:list){
				System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t\t"+l.getZwinfo().getPostinfo().getPname()+"\t\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
			}
			//
			System.out.println("请输入要更改的教师ID：");
			int nn=sca.nextInt();
			Emplo em=emd.getOne(nn);
			if(em.getEname()==null){
				System.out.println("输入有误，没有该教师");
				showAll();
			}else{
				System.out.println("以下是你要修改的课程教师信息：");
				System.out.println("员工ID\t姓名\t性别\t年龄\t教育\t职位\t\t所属岗位\t\t所属部门");
				System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t\t"+em.getZwinfo().getPostinfo().getPname()+"\t\t"+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
				System.out.println("确认修改？（1/是  0/否）");
				int coni=sca.nextInt();
				if(coni==1){
					System.out.println("正在修改课程教师信息");
					cu.setEmplo(em);
					int confirm=cud.updateCu(cu, i);
					if(confirm>0){
						System.out.println("修改成功！");
						selectCur();
					}else{
						System.out.println("修改失败！");
						selectCur();
					}
				}else if(coni==0){
					System.out.println("你选择了撤销！");
					showAll();
				}else{
					System.out.println("输入有误！");
					showAll();
				}
			}
		}
	}

	//删除课程
	private static void deleteCur() {
		System.out.println("你正在进行课程的删除操作：");
		System.out.println("你现在有如下课程可以删除：");
		List<Curr> list=cud.listCu();
		System.out.println("课程ID\t课时量\t教师\t课程名");
		for(Curr c:list){
			System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getSubjects().getSname());
		};
		System.out.println("请输入你要删除的课程ID:");
		int index=sca.nextInt();
		Curr cu=cud.getOne(index);
		if(cu.getEmplo()==null){
			System.out.println("输入有误，没有这个课程，请重新输入");
			showAll();
		}else{
			System.out.println("你要上课的课程信息如下：");
			System.out.println("课程ID\t课时量\t教师\t课程名");
			System.out.println(cu.getRid()+"\t"+cu.getRcount()+"\t"+cu.getEmplo().getEname()+"\t"+cu.getSubjects().getSname());
			System.out.println("确定删除吗？（1/是   0/否）");
			int in=sca.nextInt();
			if(in==1){
				System.out.println("正在删除...");
				
				int i=cud.deleteCu(index);
				if(i>0){
					System.out.println("删除成功！");
					selectCur();
				}else{
					System.out.println("删除失败！");
					selectCur();
				}
			}else if(in==0){
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
