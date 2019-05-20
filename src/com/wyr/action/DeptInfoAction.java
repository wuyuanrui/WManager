package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.DeptinfoDao;
import com.wyr.pojo.Deptinfo;

public class DeptInfoAction{
	static Scanner sca=new Scanner(System.in);
	static DeptinfoDao dpd=new DeptinfoDao();
	//ѡ��˵�
	public static void showAll(){
		System.out.println("��ǰ�����ڵ�λ�ã�������ҳ�������Ź���");
		System.out.println("1��������Ϣ�鿴");
		System.out.println("2��������Ϣ���");
		System.out.println("3��������Ϣ�޸�");
		System.out.println("4��������Ϣɾ��");
		System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
		int index=sca.nextInt();
		//ѡ��
		switch(index){
			case 1:
				selectDe();
				break;
			case 2:
				addDe();
				break;
			case 3:
				updateDe();
				break;
			case 4:
				deleteDe();
				break;
			default:
				System.out.println("������������������");
				//���ظ�Ŀ¼
				MenueAction.Menue();
				break;
		}
		
	}
	//��ѯ
	public static void selectDe() {
		List<Deptinfo> list=dpd.listDep();
		System.out.println("��ѯ��Ϣ���£�");
		System.out.println("����ID\t��������\t��������");
		for(Deptinfo d:list){
			System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
		};
		back();//����
	}
	
	//ɾ��
	public static void deleteDe() {
		System.out.println("�����ڽ���ɾ������...");
		System.out.println("�����������ɾ���Ĳ�����Ϣ��");
		//���Ų鿴����
		List<Deptinfo> list=dpd.listDep();
		System.out.println("��ѯ��Ϣ���£�");
		System.out.println("����ID\t��������\t��������");
		for(Deptinfo d:list){
			System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
		};
		System.out.println("��������Ҫɾ����ID:");
		int index=sca.nextInt();
		Deptinfo one=dpd.getOne(index);//���ݿ��ѯ��ǰ����
		if(one.getDname()==null){
			System.out.println("û���������Ŷ������������");
			showAll();
		}else{
			System.out.println("��Ҫɾ���Ĳ�����Ϣ���£�");
			System.out.println("����ID\t��������\t����");
			System.out.println(one.getDid()+"\t"+one.getDname()+"\t"+one.getDremark());
			System.out.println("ɾ���밴1,�˳�ɾ���밴2");
			int index1=sca.nextInt();
			if(index1==1){
				System.out.println("����ɾ��...");
				int i=dpd.deleteDep(index);
				if(i>0){
					System.out.println("ɾ���ɹ�");
					selectDe();
				}else{
					System.out.println("ɾ��ʧ��");
					selectDe();
				}
			}else if(index1==2){
				showAll();
			}else{
				System.out.println("��������");
				showAll();
			}
		}
		
		
	}
	
	//�����޸�
	public static void updateDe() {
		System.out.println("�����ڽ����޸Ĳ��ŵĲ�������������²������");
		System.out.println("��ǰ������޸ĵĲ��������£�");
		//selectAd()����
		List<Deptinfo> ad=dpd.listDep();
		System.out.println("��ѯ��Ϣ���£�");
		System.out.println("����ID\t����\t����");
		for(Deptinfo a:ad){
			System.out.println(a.getDid()+"\t"+a.getDname()+"\t"+a.getDremark());
		}
		//�鿴����޸Ŀ�ʼ
		System.out.println("�����������Ϣ������Ҫ�޸ĵ�ID:");
		int index=sca.nextInt();
		Deptinfo one=dpd.getOne(index);
		if(one.getDname()==null){
			System.out.println("û���������Ŷ������������");
			showAll();
		}else{
			System.out.println("��Ҫ�޸ĵĲ�����Ϣ���£�");
			System.out.println("����ID\t��������\t����");
			System.out.println(one.getDid()+"\t"+one.getDname()+"\t"+one.getDremark());
			System.out.println("�޸��밴1,�˳��޸��밴2");
			int index1=sca.nextInt();
			if(index1==1){
				System.out.println("��ǰ�����޸�..");
				System.out.println("��������Ҫ�޸ĵĲ������ƣ�");
				String name=sca.next();
				System.out.println("��������Ҫ�޸ĵĲ�������");
				String mark=sca.next();
				//��װ��ȡ������Ϣ
				Deptinfo deptinfo=new Deptinfo();
				deptinfo.setDid(index);
				deptinfo.setDname(name);
				deptinfo.setDremark(mark);
				int i=dpd.updateDep(deptinfo);
				
				if(i>0){
					System.out.println("�޸ĳɹ�");
					selectDe();
				}else{
					System.out.println("�޸�ʧ��");
					selectDe();//���ؼ����޸�
				}
			}else if(index1==2){
				showAll();//�˳��޸�
			}else{
				System.out.println("��������!");
				showAll();//���ؼ����޸�
			}
		}
	}
	
	//�������
	public static void addDe() {
		System.out.println("�����ڽ�����Ӳ��ŵĲ�������������²������");
		System.out.println("�����벿������");
		String name=sca.next();
		System.out.println("�����벿������");
		String mark=sca.next();
		//��Ϣ��װ
		Deptinfo ad=new Deptinfo();
		ad.setDname(name);
		ad.setDremark(mark);
		//��Ӳ���
		int i=dpd.addDep(ad);
		if(i>0){
			System.out.println("��ӳɹ�");
			selectDe();
		}else{
			System.out.println("���ʧ��");
			selectDe();
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
