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
	
	//ѡ��˵�
			public static void showAll(){
				System.out.println("��ǰ�����ڵ�λ�ã�������ҳ�����༶����");
				System.out.println("1���༶��Ϣ�鿴");
				System.out.println("2���༶��Ϣ���");
				System.out.println("3���༶��Ϣ�޸�");
				System.out.println("4���༶��Ϣɾ��");
				System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
				int index=sca.nextInt();
				//ѡ��
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
						System.out.println("������������������");
						//���ظ�Ŀ¼
						MenueAction.Menue();
						break;
				}
			}
			

	public static void selectCla() {
		System.out.println("�༶��Ϣ���£�");
		List<Classes> list= cad.listCla();
		System.out.println("�༶ID\t����\t����\t�����꼶");
		for(Classes p:list){
			System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
		}
		back();	
	}
//���
	public static void addCla() {
		System.out.println("�����ڽ�������꼶��Ϣ����..");
		//��ѯ������ѡ����꼶
		System.out.println("���������꼶��");
		List<Grade> list=grd.listGr();
		System.out.println("�꼶ID\t����\t����\t����ϵԺ");
		for(Grade g:list){
			System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
		}
		System.out.println("��ѡ�����ϵ��꼶ID��");
		int index=sca.nextInt();
		//��ȡ�����꼶��Ϣ���к˶�
		
		Grade des=grd.getOne(index);
		if(des.getGname()==null){
			System.out.println("û������꼶Ŷ�����������");
			showAll();
		}else{
			System.out.println("����������ӵ���Ϣ��");
			System.out.println("�꼶ID\t����\t����\t����Ժϵ");
			System.out.println(des.getGid()+"\t"+des.getGname()+"\t"+des.getGremark()+"\t"+des.getXb().getXname());
			System.out.println("ȷ��������꼶��(1/��   0/��)");
			int con=sca.nextInt();
			if(con==1){
				//Ժϵ��ѯ
				System.out.println("Ժϵ�б�������ʾ��");
				List<Xb> lists=xbd.listXb();
				System.out.println("ԺϵID\t����\t����");
				for(Xb x:lists){
					System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
				}
				System.out.println("��ѡ������Ժϵid��");
				int ins=sca.nextInt();
				
				Xb xb=xbd.getOne(ins);
				if(xb.getXname()==null){
					System.out.println("û�����Ժϵ���������룡");
					showAll();
				}else{
					System.out.println("��������ѡ���Ժϵ��Ϣ��");
					System.out.println("ԺϵID\t����\t����");
					System.out.println(xb.getXid()+"\t"+xb.getXname()+"\t"+xb.getXremark());
					System.out.println("ȷ������𣿣�1/��   0/��");
					int cono=sca.nextInt();
					
					if(cono==1){
						System.out.println("������༶���ƣ�");
						String name=sca.next();
						System.out.println("������༶������");
						String  mark=sca.next();
						//��װ���ź͸�λ��Ϣ
						Classes cla=new Classes();
						cla.setCname(name);
						cla.setCremark(mark);
						//��װ��Ϣ
						des.setXb(xb);
						cla.setGrade(des);
						int i=cad.addCla(cla);
						if(i>0){
							System.out.println("��ӳɹ���");
							selectCla();
						}else{
							System.out.println("���ʧ��");
							selectCla();
						}
					}else if(con==0){
						System.out.println("�㳷���ˣ�");
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
				}
			}else if(con==0){
				System.out.println("��ѡ���˳���");
				showAll();
			}else{
				System.out.println("��������");
				showAll();
			}
			
		}
				
	}
//�޸�
	public static void updateCla() {
		System.out.println("�����ڽ��а༶���޸Ĳ�����");
		System.out.println("�������°༶�����޸ģ�");	
		List<Classes> list= cad.listCla();
		System.out.println("�༶ID\t����\t����\t�����꼶");
		for(Classes p:list){
			System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
		};
		System.out.println("���������ϵİ༶Id�����޸ģ�");
		int index=sca.nextInt();
		
		Classes cla=cad.getOne(index);
		if(cla.getCname()==null){
			System.out.println("û������༶������������");
			showAll();
		}else{
			System.out.println("��Ҫ�޸ĵİ༶��Ϣ���£�");
			System.out.println("�༶ID\t����\t����\t�����꼶");
			System.out.println(cla.getCid()+"\t"+cla.getCname()+"\t"+cla.getCremark()+"\t"+cla.getGrade().getGname());
			System.out.println("ȷ���޸���(1/��   0/��)");
			int confirm=sca.nextInt();
			if(confirm==1){
				System.out.println("��������Ҫ���ĵİ༶���ƣ�");
				String name=sca.next();
				System.out.println("��������Ҫ���ĵİ༶������");
				String mark=sca.next();
				
				Classes clas=new Classes();
				clas.setCid(index);
				clas.setCname(name);
				clas.setCremark(mark);
				int ii=cad.update(clas);
				if(ii>0){
					System.out.println("�޸ĳɹ���");
					selectCla();
				}else{
					System.out.println("�޸�ʧ�ܣ�");
					selectCla();
				}
			}else if(confirm==0){
				System.out.println("��ѡ���˳���");
				showAll();
			}else{
				System.out.println("��������");
				showAll();
			}
		}
				
	}

	public static void deleteCla() {
		System.out.println("�����ڽ��а༶��ɾ������");
		System.out.println("�������°༶����ɾ����");
		//��ѯ��λ
		List<Classes> list= cad.listCla();
		System.out.println("�༶ID\t����\t����\t�����꼶");
		for(Classes p:list){
			System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
		}
		
		System.out.println("������Ҫ��ɾ���İ༶ID��");
		int index=sca.nextInt();
		Classes po=cad.getOne(index);
		if(po.getClass()==null){
			System.out.println("û������༶Ŷ��");
			showAll();
		}else{
			System.out.println("��Ҫɾ���༶��Ϣ���£�");
			System.out.println("�༶ID\t����\t����\t�����꼶");
			System.out.println(po.getCid()+"\t"+po.getCname()+"\t"+po.getCremark()+"\t"+po.getGrade().getGname());
			System.out.println("ȷ��Ҫɾ����(1/��   0/��)");
			int con=sca.nextInt();
			if(con==1){
				int i=cad.delete(con);
				if(i>0){
					System.out.println("ɾ���ɹ���");
					selectCla();
				}else{
					System.out.println("ɾ��ʧ��");
					selectCla();
				}
			}else if(con==0){
				System.out.println("��ѡ���˳�����");
				showAll();
			}else{
				System.out.println("��������");
				showAll();
			}
		}
		
	}
	

	//�����ķ���
	public static void back(){
		System.out.println("���ظ�Ŀ¼10��������һ��11");
		int index=sca.nextInt();
		if(index==10){
			//���ظ�Ŀ¼
			MenueAction.Menue();
		}else if(index==11){
			showAll();
		}
	}
}
