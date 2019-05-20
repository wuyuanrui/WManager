package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.SubjectsDao;
import com.wyr.pojo.Subjects;
import com.wyr.pojo.Xb;

public class SubjectsAction {
	static Scanner sca=new Scanner(System.in);
	static SubjectsDao sbd=new SubjectsDao();
	

	//ѡ��˵�
		public static void showAll(){
			System.out.println("��ǰ�����ڵ�λ�ã�������ҳ������Ŀ����");
			System.out.println("1����Ŀ��Ϣ�鿴");
			System.out.println("2����Ŀ��Ϣ���");
			System.out.println("3����Ŀ��Ϣ�޸�");
			System.out.println("4����Ŀ��Ϣɾ��");
			System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
			int index=sca.nextInt();
			//ѡ��
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
					System.out.println("������������������");
					//���ظ�Ŀ¼
					MenueAction.Menue();
					break;
			}
		}
		
		//��Ŀ��ѯ
		private static void selectSu() {
			System.out.println("���������ڲ鿴��Ŀ...");
			System.out.println("��Ŀ�б�������ʾ��");
			List<Subjects> list=sbd.listSu();
			System.out.println("��ĿID\t����\t����");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			back();
		}


		private static void deleteSu() {
			System.out.println("���������ڽ��п�Ŀ��ɾ��...");
			System.out.println("���п�Ŀ���£�");
			//Ժϵ��ѯ
			List<Subjects> list=sbd.listSu();
			System.out.println("��ĿID\t����\t����");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			System.out.println("�����ѡ�����Ͽ�ĿID����ɾ���������룺");
			int index=sca.nextInt();
			System.out.println("��ȡ��"+index);
			Subjects x=sbd.getOne(index);
			if(null==x.getSname()){
				System.out.println("��������û�иÿ�Ŀ������������");
				showAll();
			}else{
				System.out.println("��Ҫɾ����Ժϵ��Ϣ���£�");
				System.out.println("��ĿID\t����\t����");
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
				
				System.out.println("ȷ��ɾ����(1/��   0/��)");
				int confim=sca.nextInt();
				if(confim==1){
					System.out.println("����ɾ��...");
					int con=sbd.delete(index);
					if(con>0){
						System.out.println("ɾ���ɹ���");
						selectSu();
					}else{
						System.out.println("ɾ��ʧ��");
						selectSu();
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


		private static void updateSu() {
			System.out.println("�����ڽ��п�Ŀ�޸Ĳ�����");
			System.out.println("�����������¿�Ŀ�����޸ģ�");
			List<Subjects> list=sbd.listSu();
			System.out.println("��ĿID\t����\t����");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			System.out.println("��������Ҫ�޸ĵĿ�ĿID");
			int index=sca.nextInt();
			
			Subjects x=sbd.getOne(index);
			if(null==x.getSname()){
				System.out.println("�������û�и�Ժϵ������������");
				showAll();
			}else{
				System.out.println("��Ҫ�޸ĵĿ�Ŀ��Ϣ������ʾ��");
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
				System.out.println("ȷ���޸���(1/��  0/��)");
				int i=sca.nextInt();
				if(i==1){
					System.out.println("��������Ҫ�޸ĵĿ�Ŀ���ƣ�");
					String name=sca.next();
					System.out.println("��������Ҫ�޸ĵĿ�Ŀ��������");
					String mark=sca.next();
					System.out.println("��������Ҫ�޸ĺ���ֶ�");
					//��װ
					Subjects su=new Subjects();
					su.setSid(index);
					su.setSname(name);
					su.setSremark(mark);
					
					
					System.out.println("��Ŀ��\t����");
					System.out.println(su.getSname()+"\t"+su.getSremark());
					System.out.println("ȷ���𣿣�1/��  0/��");
					int ii=sca.nextInt();
					if(ii==1){
						int a=sbd.update(su);
						if(a>0){
							System.out.println("�޸ĳɹ���");
							selectSu();
						}else{
							System.out.println("�޸�ʧ�ܣ�");
							selectSu();
						}
					}else if(ii==0){
						System.out.println("��ѡ���˳���");
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
				}else if(i==0){
					System.out.println("��ѡ���˳���");
					showAll();
				}else{
					System.out.println("��������");
					showAll();
				}
			}
			
		}


		private static void addSu() {
			System.out.println("��������ӿ�Ŀ");
			System.out.println("������Ҫ��ӵĿ�Ŀ���ƣ�");
			String name=sca.next();
			System.out.println("������Ҫ��ӵĿ�Ŀ������");
			String mark=sca.next();
			System.out.println("��������Ҫ��ӵ���Ϣ��");
			System.out.println("��Ŀ\t����");
			System.out.println(name+"\t"+mark);
			System.out.println("ȷ���𣿣�1/��   0/��");
			int i=sca.nextInt();
			if(i==1){
				System.out.println("�������");
				//��װ��Ϣ
				Subjects su=new Subjects();
				su.setSname(name);
				su.setSremark(mark);
				//
				int ii=sbd.add(su);
				if(ii>0){
					System.out.println("��ӳɹ�");
					selectSu();
				}else{
					System.out.println("���ʧ��");
					selectSu();
				}
			}else if(i==0){
				System.out.println("�㳷���˲���");
				showAll();
			}else{
				System.out.println("������������������");
				showAll();
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
