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
	//ѡ��˵�
		public static void showAll(){
			System.out.println("��ǰ�����ڵ�λ�ã�������ҳ������λ����");
			System.out.println("1����λ��Ϣ�鿴");
			System.out.println("2����λ��Ϣ���");
			System.out.println("3����λ��Ϣ�޸�");
			System.out.println("4����λ��Ϣɾ��");
			System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
			int index=sca.nextInt();
			//ѡ��
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
					System.out.println("������������������");
					//���ظ�Ŀ¼
					MenueAction.Menue();
					break;
			}
		}
		
		
		//��ѯ
		public static void selectPo() {
			System.out.println("��λ��Ϣ���£�");
			List<Postinfo> list= pod.listPo();
			System.out.println("��λID\t��λ����\t��λ����\t��������");
			for(Postinfo p:list){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			back();
		}
		
		//ɾ����λ
		public static void deletePo() {
			System.out.println("�����ڽ��и�λ��ɾ������");
			System.out.println("�������¸�λ����ɾ����");
			//��ѯ��λ
			List<Postinfo> list= pod.listPo();
			System.out.println("��λID\t��λ����\t��λ����\t��������");
			for(Postinfo p:list){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			System.out.println("������Ҫ��ɾ���ĸ�λID��");
			int index=sca.nextInt();
			Postinfo po=pod.getOne(index);
			if(null==po){
				System.out.println("û�������λŶ��");
				showAll();
			}else{
				System.out.println("��Ҫɾ����λ��Ϣ���£�");
				System.out.println("��λID\t����\t����\t��������");
				System.out.println(po.getPid()+"\t"+po.getPname()+"\t"+po.getPremark()+"\t"+po.getDeptinfo().getDname());
				System.out.println("ȷ��Ҫɾ����(1/��   0/��)");
				int con=sca.nextInt();
				if(con==1){
					int i=pod.deletePo(index);
					if(i>0){
						System.out.println("ɾ���ɹ���");
						selectPo();
					}else{
						System.out.println("ɾ��ʧ��");
						selectPo();
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
		//�޸ĸ�λ
		public static void updatePo() {
			System.out.println("�����ڽ��и�λ���޸Ĳ�����");
			System.out.println("�������¸�λ�����޸ģ�");
			//��ѯ��λ
			List<Postinfo> list= pod.listPo();
			System.out.println("��λID\t��λ����\t��λ����\t��������");
			for(Postinfo p:list){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			System.out.println("��������Ҫ�޸ĵĸ�λID��");
			int index=sca.nextInt();//Ҫ�޸ĵĸ�λID
			Postinfo po=pod.getOne(index);
			if(null==po.getPname()){
				System.out.println("û�������λŶ��");
				showAll();
			}else{
				System.out.println("��Ҫ�޸ĵĸ�λ��Ϣ���£�");
				System.out.println("��λID\t����\t����\t��������");
				System.out.println(po.getPid()+"\t"+po.getPname()+"\t"+po.getPremark()+"\t"+po.getDeptinfo().getDname());
				System.out.println("������޸�����ѡ��:");
				//�����������޸ķ�ʽ��
				System.out.println("1����λ����");
				System.out.println("2����λ����");
				int index1=sca.nextInt();
				Postinfo po1=new Postinfo();
				po1.setPid(index);
				
				if(index1==1){
					System.out.println("�������λ���ƣ�");
					String name=sca.next();
					po1.setPname(name);
					int i=pod.updatePo(po1,index1);//�޸ĸ�λ����
					if(i>0){
						System.out.println("�޸ĳɹ���");
						selectPo();
					}else{
						System.out.println("�޸�ʧ��");
						selectPo();
					}
					
				}else if(index1==2){
					System.out.println("�������²��ſ���ѡ��");
					//��ѯ������ѡ��Ĳ���
					List<Deptinfo> lists=dfd.listDep();
					
					System.out.println("�������²��ſ���ѡ��");
					System.out.println("����ID\t��������\t��������");
					for(Deptinfo d:lists){
						System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
					};
					System.out.println("�������λ����ID");
					int index2=sca.nextInt();
					
					
					Deptinfo des=dfd.getOne(index2);//��ȡ����
					System.out.println("����Ҫ�޵Ĳ�����Ϣ���£�");
					System.out.println("����ID\t\t");
					System.out.println(des.getDid()+"\t"+des.getDname()+"\t"+des.getDremark());
					System.out.println("ȷ�������������(1/��   0/��)");
					int confim=sca.nextInt();
					if(confim==1){
						Deptinfo de=new Deptinfo();
						de.setDid(index2);
						po1.setDeptinfo(de);
						int i=pod.updatePo(po1, index1);//�޸Ĳ���
						if(i>0){
							System.out.println("�޸ĳɹ���");
							selectPo();
						}else{
							System.out.println("�޸�ʧ��");
							selectPo();
						}
					}else if(confim==0){
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
				}else{
					System.out.println("��������");
					showAll();
				}
			}
			
		}
		
		//��Ӹ�λ
		public static void addPo() {
			System.out.println("�����ڽ�����Ӹ�λ��Ϣ����..");
			//��ѯ������ѡ��Ĳ���
			List<Deptinfo> list=dfd.listDep();
			
			System.out.println("�������²��ſ���ѡ��");
			System.out.println("����ID\t��������\t��������");
			for(Deptinfo d:list){
				System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
			};
			System.out.println("��ѡ�����ϸ�λ�Ĳ��ţ�");
			int index=sca.nextInt();
			//��ȡ����������Ϣ���к˶�
			Deptinfo des=dfd.getOne(index);
			if(null==des.getDname()){
				System.out.println("û���������Ŷ�����������");
				showAll();
			}else{
				System.out.println("��������Ҫ����ĸ�λ��Ϣ��");
				System.out.println("����ID\t\t");
				System.out.println(des.getDid()+"\t"+des.getDname()+"\t"+des.getDremark());
				System.out.println("ȷ�������������(1/��   0/��)");
				int confim=sca.nextInt();
				if(confim==1){
					System.out.println("�������λ����");
					String name=sca.next();
					System.out.println("�������λ������");
					String  mark=sca.next();
					//��װ���ź͸�λ��Ϣ
					Deptinfo de=new Deptinfo();
						de.setDid(index);
					Postinfo po=new Postinfo();
						po.setPname(name);
						po.setPremark(mark);
						po.setDeptinfo(de);
						int i=pod.addPo(po);//��Ӹ�λ��Ϣ
						if(i>0){
							System.out.println("��ӳɹ���");
							selectPo();
						}else{
							System.out.println("���ʧ�ܣ�");
							selectPo();
						}
				}else if(confim==0){
					System.out.println("��ѡ���˳�����");
					showAll();//���ؼ������
				}else{
					System.out.println("��������");
					showAll();//������ҳ��
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