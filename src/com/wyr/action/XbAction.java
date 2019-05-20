package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.XbDao;
import com.wyr.pojo.Xb;

public class XbAction  {
	static Scanner sca=new Scanner(System.in);
	static XbDao xbd=new XbDao();
	
	
	//ѡ��˵�
		public static void showAll(){
			System.out.println("��ǰ�����ڵ�λ�ã�������ҳ����ѧԺ����");
			System.out.println("1��ѧԺ��Ϣ�鿴");
			System.out.println("2��ѧԺ��Ϣ���");
			System.out.println("3��ѧԺ��Ϣ�޸�");
			System.out.println("4��ѧԺ��Ϣɾ��");
			System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
			int index=sca.nextInt();
			//ѡ��
			switch(index){
				case 1:
					selectXb();
					break;
				case 2:
					addXb();
					break;
				case 3:
					updateXb();
					break;
				case 4:
					deleteXb();
					break;
				default:
					System.out.println("������������������");
					//���ظ�Ŀ¼
					MenueAction.Menue();
					break;
			}
		}
		
		
	
	//ѧԺ��ѯ
	public static void selectXb() {
		System.out.println("���������ڲ鿴Ժϵ...");
		System.out.println("Ժϵ�б�������ʾ��");
		List<Xb> list=xbd.listXb();
		System.out.println("ԺϵID\t����\t����");
		for(Xb x:list){
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
		}
		back();
	}




	//���Ժϵ
	public static void addXb() {
		System.out.println("���������Ժϵ");
		System.out.println("������Ҫ��ӵ�Ժϵ���ƣ�");
		String name=sca.next();
		System.out.println("������Ҫ��ӵ�Ժϵ������");
		String mark=sca.next();
		System.out.println("��������Ҫ��ӵ���Ϣ��");
		System.out.println("Ժϵ\t����");
		System.out.println(name+"\t"+mark);
		System.out.println("ȷ���𣿣�1/��   0/��");
		int i=sca.nextInt();
		if(i==1){
			System.out.println("�������");
			//��װ��Ϣ
			Xb xb=new Xb();
			xb.setXname(name);
			xb.setXremark(mark);
			//
			int ii=xbd.add(xb);
			if(ii>0){
				System.out.println("��ӳɹ�");
				selectXb();
			}else{
				System.out.println("���ʧ��");
				selectXb();
			}
		}else if(i==0){
			System.out.println("�㳷���˲���");
			showAll();
		}else{
			System.out.println("������������������");
			addXb();
		}
	}




	//Ժϵ�޸�
	public static void updateXb() {
		System.out.println("�����ڽ���Ժϵ�޸Ĳ�����");
		System.out.println("������������Ժϵ�����޸ģ�");
		List<Xb> list=xbd.listXb();
		System.out.println("ԺϵID\t����\t����");
		for(Xb x:list){
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
		}
		System.out.println("��������Ҫ�޸ĵ�ԺϵID");
		int index=sca.nextInt();
		
		Xb x=xbd.getOne(index);
		if(null==x.getXname()){
			System.out.println("�������û�и�Ժϵ������������");
			showAll();
		}else{
			System.out.println("��Ҫ�޸ĵ�Ժϵ��Ϣ������ʾ��");
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
			System.out.println("ȷ���޸���(1/��  0/��)");
			int i=sca.nextInt();
			if(i==1){
				System.out.println("��������Ҫ�޸ĵ�Ժϵ���ƣ�");
				String name=sca.next();
				System.out.println("��������Ҫ�޸ĵ�Ժϵ��������");
				String mark=sca.next();
				System.out.println("��������Ҫ�޸ĺ���ֶ�");
				Xb xb=new Xb();
				xb.setXid(index);
				xb.setXname(name);
				xb.setXremark(mark);
				
				System.out.println("Ժϵ��\t����");
				System.out.println(xb.getXname()+"\t"+xb.getXremark());
				System.out.println("ȷ���𣿣�1/��  0/��");
				int ii=sca.nextInt();
				if(ii==1){
					int a=xbd.update(xb);
					if(a>0){
						System.out.println("�޸ĳɹ���");
						selectXb();
						System.out.println("��Ҫ�����޸�������(1/��   0/��)");
						int con=sca.nextInt();
						if(con==1){
							updateXb();
						}else if(con==0){
							showAll();
						}else{
							System.out.println("��������");
							showAll();
						}
					}else{
						System.out.println("�޸�ʧ�ܣ�");
						selectXb();
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





	public static void deleteXb() {
		System.out.println("���������ڽ���Ժϵ��ɾ��...");
		System.out.println("����Ժϵ���£�");
		//Ժϵ��ѯ
		List<Xb> list=xbd.listXb();
		System.out.println("ԺϵID\t����\t����");
		for(Xb x:list){
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
		}
		System.out.println("�����ѡ������ԺϵID����ɾ���������룺");
		int index=sca.nextInt();
		System.out.println("��ȡ��"+index);
		Xb x=xbd.getOne(index);
		if(null==x.getXname()){
			System.out.println("��������û�и�Ժϵ������������");
			showAll();
		}else{
			System.out.println("��Ҫɾ����Ժϵ��Ϣ���£�");
			System.out.println("ԺϵID\t����\t����");
			System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
			
			System.out.println("ȷ��ɾ����(1/��   0/��)");
			int confim=sca.nextInt();
			if(confim==1){
				System.out.println("����ɾ��...");
				int con=xbd.delete(index);
				if(con>0){
					System.out.println("ɾ���ɹ���");
					selectXb();
				}else{
					System.out.println("ɾ��ʧ��");
					selectXb();
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
