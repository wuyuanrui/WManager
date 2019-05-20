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
	
	//ѡ��˵�
			public static void showAll(){
				System.out.println("��ǰ�����ڵ�λ�ã�������ҳ�����꼶����");
				System.out.println("1���꼶��Ϣ�鿴");
				System.out.println("2���꼶��Ϣ���");
				System.out.println("3���꼶��Ϣ�޸�");
				System.out.println("4���꼶��Ϣɾ��");
				System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
				int index=sca.nextInt();
				//ѡ��
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
						System.out.println("������������������");
						//���ظ�Ŀ¼
						MenueAction.Menue();
						break;
				}
			}

			
			
			
			//��ѯ���꼶-Ժϵ
			private static void selectGr() {
				System.out.println("���������ڽ����꼶��ѯ...");
				System.out.println("���������꼶��");
				List<Grade> list=grd.listGr();
				System.out.println("�꼶ID\t����\t\t����\t����Ժϵ");
				for(Grade g:list){
					System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
				}
				back();
				
			}




			//����꼶-Ժϵ
			private static void addGr() {
				System.out.println("���������ڽ����꼶���...");
				System.out.println("��������Ժϵ:");
				List<Xb> list=xbd.listXb();
				System.out.println("ԺϵID\t����\t����");
				for(Xb x:list){
					System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
				}
				System.out.println("��ѡ������ԺϵID������ӣ������룺");
				int index=sca.nextInt();
				Xb xb=xbd.getOne(index);
				if(null==xb.getXname()){
					System.out.println("��������û�����Ժϵ������������");
					showAll();
				}else{
					System.out.println("�������꼶����Ϣ��");
					String name=sca.next();
					System.out.println("�������꼶��������");
					String mark=sca.next();
					//��Ϣ��װ
					Grade gr=new Grade();
					gr.setGname(name);
					gr.setGremark(mark);
					gr.setXb(xb);
					System.out.println("��������Ҫ��ӵ���Ϣ��");
					System.out.println("�꼶ID\t����\t\t����\t����Ժϵ");
					System.out.println(gr.getGname()+"\t\t"+gr.getGremark()+"\t"+gr.getXb().getXname());
					System.out.println("ȷ��Ҫ�����(1/��   0/��)");
					int con=sca.nextInt();
					if(con==1){
						System.out.println("�������");
						int ii=grd.add(gr);
						if(ii>0){
							System.out.println("��ӳɹ���");
							 selectGr();
						}else{
							System.out.println("���ʧ�ܣ�");
							 selectGr();
						}
					}else if(con==0){
						System.out.println("��ѡ���˳���");
						showAll();
					}else{
						System.out.println("��������,����������");
						addGr();
					}
				}
			}

			//�޸�
			private static void updateGr() {
				System.out.println("�����ڽ����꼶���޸Ĳ���...");
				//�꼶��ѯ
				System.out.println("���������꼶��");
				List<Grade> list=grd.listGr();
				System.out.println("�꼶ID\t����\t\t����\t����Ժϵ");
				for(Grade g:list){
					System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
				}
				
				System.out.println("��������Ҫ�޸ĵ��꼶ID��");
				int index=sca.nextInt();
				Grade gr=grd.getOne(index);
				if(null==gr.getGname()){
					System.out.println("��������û������꼶");
					showAll();
				}else{
					System.out.println("��������Ҫ�޸ĵ��꼶��Ϣ��");
					System.out.println("�꼶ID\t����\t\t����\t����Ժϵ");
					System.out.println(gr.getGid()+"\t"+gr.getGname()+"\t"+gr.getGremark()+"\t"+gr.getXb().getXname());
					System.out.println("ȷ��Ҫ�޸��𣿣�1/��   0/��");
					int i=sca.nextInt();
					if(i==1){
						updateSuccess(index);
					}else if(i==0){
						System.out.println("��ѡ���˳���");
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
					
				}
			}
			public static void updateSuccess(int index) {
				Grade gr=new Grade();
				//Ҫ�޸ĵ��꼶ID
				gr.setGid(index);
				
				System.out.println("�����������²���:");
				System.out.println("1���꼶��ͨ��Ϣ");
				System.out.println("2���꼶����Ժϵ");
				System.out.println("��������ѡ��ı�ţ�");
				int num=sca.nextInt();
				if(num==1){
					System.out.println("��������Ҫ�޸ĵ��꼶���ƣ�");
					String name=sca.next();
					System.out.println("��������Ҫ�޸ĵ��꼶������");
					String mark=sca.next();
					gr.setGname(name);
					gr.setGremark(mark);
					System.out.println("ȷ���޸��𣿣�1/��   0/��");
					int confirm=sca.nextInt();
					if(confirm==1){
						int i=grd.update(gr, 1);
						if(i>0){
							System.out.println("�޸ĳɹ���");
							selectGr();
						}else{
							System.out.println("�޸�ʧ�ܣ�");
							selectGr();
						}
					}else if(confirm==0){
						System.out.println("��ѡ���˳���");
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
				}else if(num==2){
					System.out.println("�����ڲ����꼶��Ժϵ�޸�...");
					System.out.println("������������Ժϵ����ѡ��");
					//����Ժϵ
					List<Xb> list=xbd.listXb();
					System.out.println("ԺϵID\t����\t����");
					for(Xb x:list){
						System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
					};
					System.out.println("��ѡ������ԺϵId�����޸�:");
					int indexx=sca.nextInt();
					Xb xb=xbd.getOne(indexx);
					//��װϵ�����Ϣ
					gr.setXb(xb);
					
					if(null==xb){
						System.out.println("û�����Ժϵ������������");
						showAll();
					}else{
						System.out.println("��Ҫ�޸ĵ�Ժϵ������ʾ��");
						System.out.println("ԺϵID\t����\t����");
						System.out.println(xb.getXid()+"\t"+xb.getXname()+"\t"+xb.getXremark());
						System.out.println("ȷ��Ҫ�޸���?(1/��   0/��)");
						int con=sca.nextInt();
						if(con==1){
							System.out.println("�����޸�...");
							int i=grd.update(gr, 2);
							if(i>0){
								System.out.println("�޸ĳɹ���");
								selectGr() ;
							}else{
								System.out.println("�޸�ʧ�ܣ�");
								selectGr() ;
							}
						}else if(con==0){
							System.out.println("��ѡ���˳���");
							showAll();
						}else{
							System.out.println("��������");
							showAll();
						}
					}
				}else{
					System.out.println("��������");
					showAll();
				}
				
			}




			//ɾ��
			private static void deleteGr() {
				System.out.println("�����ڽ��а༶��ɾ������...");
				System.out.println("���������꼶��");
				List<Grade> list=grd.listGr();
				System.out.println("�꼶ID\t����\t\t����\t����Ժϵ");
				for(Grade g:list){
					System.out.println(g.getGid()+"\t"+g.getGname()+"\t"+g.getGremark()+"\t"+g.getXb().getXname());
				};
				System.out.println("��������Ҫɾ�����꼶ID��");
				int index=sca.nextInt();
				Grade gr=grd.getOne(index);
				if(gr.getGname()==null){
					System.out.println("��������,û������꼶������������");
					showAll();
				}else{
					System.out.println("��Ҫɾ���İ༶��Ϣ������ʾ��");
					System.out.println("�༶ID\t����\t����\t����Ժϵ");
					System.out.println(gr.getGid()+"\t"+gr.getGname()+"\t"+gr.getGremark()+"\t"+gr.getXb().getXname());
					System.out.println("ȷ��ɾ���𣿣�1/s��   0/��");
					int confim=sca.nextInt();
					if(confim==1){
						System.out.println("����ɾ��");
						int ii=grd.delete(index);
						if(ii>0){
							System.out.println("ɾ���ɹ���");
							selectGr();
						}else{
							System.out.println("ɾ��ʧ��");
							selectGr();
						}
					}else if(confim==0){
						System.out.println("��ѡ���˳���");
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
