package com.wyr.action;

import java.util.List;
import java.util.Scanner;

import com.wyr.daoimpl.AdminInfoDao;
import com.wyr.pojo.Admininfo;

public class AdminInfoAction {
	static Scanner sca=new Scanner(System.in);
	static AdminInfoDao adm=new AdminInfoDao();
	//ѡ��˵�
	public static void showAll(){
		System.out.println("��ǰ�����ڵ�λ�ã�������ҳ��������Ա����");
		System.out.println("1������Ա�鿴");
		System.out.println("2������Ա���");
		System.out.println("3������Ա�޸�");
		System.out.println("4������Աɾ��");
		System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
		int index=sca.nextInt();
		//ѡ��
		switch(index){
			case 1:
				selectAd();
				break;
			case 2:
				addAd();
				break;
			case 3:
				updateAd();
				break;
			case 4:
				deleteAd();
				break;
			default:
				System.out.println("������������������");
				//���ظ�Ŀ¼
				MenueAction.Menue();
				break;
		}
		
	}
	//�鿴
	public static void selectAd(){
		List<Admininfo> ad=adm.listAd();
		System.out.println("��ѯ��Ϣ���£�");
		System.out.println("ID\t����Ա\t����");
		for(Admininfo a:ad){
			System.out.println(a.getAid()+"\t"+a.getAname()+"\t"+a.getAremark());
		}
		back();
	}
	//���
	public static void addAd(){
		System.out.println("�����ڽ�����ӹ���Ա�Ĳ�������������²������");
		System.out.println("�������������");
		String name=sca.next();
		System.out.println("�������������");
		String pwd=sca.next();
		System.out.println("��������ĸ���ǩ��");
		String gx=sca.next();
		
		Admininfo ad=new Admininfo();
		ad.setAname(name);
		ad.setApwd(pwd);
		ad.setAremark(gx);
		
		int i=adm.addAd(ad);
		if(i>0){
			System.out.println("��ӳɹ�");
			selectAd();
		}else{
			System.out.println("���ʧ��");
			selectAd();
		}
	}
	//�޸�
	public static void updateAd(){
		System.out.println("�����ڽ����޸Ĺ���Ա�Ĳ�������������²������");
		System.out.println("��ǰ������޸ĵĹ���Ա�����£�");
		//selectAd()����
		List<Admininfo> ad=adm.listAd();
		System.out.println("��ѯ��Ϣ���£�");
		System.out.println("ID\t����Ա\t����");
		for(Admininfo a:ad){
			System.out.println(a.getAid()+"\t"+a.getAname()+"\t"+a.getAremark());
		}
		//�鿴����޸Ŀ�ʼ
		System.out.println("�����������Ϣ������Ҫ�޸ĵ�ID:");
		int index=sca.nextInt();
		Admininfo one=adm.getOne(index);
		if(one.getAname()==null){
			System.out.println("û�������Ŷ������������");
			showAll();
		}else{
			System.out.println("��Ҫ�޸ĵĹ���Ա�˺���Ϣ���£�");
			System.out.println("ID\t����\t����\t����ǩ��");
			System.out.println(one.getAid()+"\t"+one.getAname()+"\t"+one.getApwd()+"\t"+one.getAremark());
			System.out.println("�޸��밴1,�˳��޸��밴2");
			int index1=sca.nextInt();
			if(index1==1){
				 updateSuccess(index);//���ӵ��޸ģ���������������
			}else if(index1==2){
				showAll();//�˳��޸�
			}else{
				System.out.println("��������!");
				showAll();//���ؼ����޸�
			}
		}
		
	}
	
	//ɾ��
	public static void deleteAd(){
		System.out.println("�����ڽ���ɾ������...");
		System.out.println("�����������ɾ���Ĺ���Ա��Ϣ��");
		//selectAd()����
				List<Admininfo> ad=adm.listAd();
				System.out.println("��ѯ��Ϣ���£�");
				System.out.println("ID\t����Ա\t����");
				for(Admininfo a:ad){
					System.out.println(a.getAid()+"\t"+a.getAname()+"\t"+a.getAremark());
				}
		System.out.println("��������Ҫɾ����ID:");
		int index=sca.nextInt();
		Admininfo one=adm.getOne(index);//���ݿ��ѯ��ǰ�����
		if(one.getAname()==null){
			System.out.println("û�������Ŷ������������");
			showAll();
		}else{
			System.out.println("��Ҫɾ���Ĺ���Ա�˺���Ϣ���£�");
			System.out.println("ID\t����\t����\t����ǩ��");
			System.out.println(one.getAid()+"\t"+one.getAname()+"\t"+one.getApwd()+"\t"+one.getAremark());
			System.out.println("ɾ���밴1,�˳�ɾ���밴2");
			int index1=sca.nextInt();
			if(index1==1){
				System.out.println("����ɾ��...");
				int i=adm.deleteAd(index);
				if(i>0){
					System.out.println("ɾ���ɹ�");
					selectAd();
				}else{
					System.out.println("ɾ��ʧ��");
					selectAd();
				}
			}else if(index1==2){
				System.out.println("��ѡ���˳���");
				showAll();
			}else{
				System.out.println("��������");
				showAll();
			}
		}
		
		
	}
	
	public static void updateSuccess(int index) {
		System.out.println("�����ڽ����޸Ĳ���...");
		System.out.println("���������޸��");
		System.out.println("1�������޸�");
		System.out.println("2�������޸�");
		System.out.println("3������ǩ���޸�");
		System.out.println("4���޸�ȫ��");
		int index2=sca.nextInt();
		switch(index2){
			case 1:
				System.out.println("���ڽ����û����޸ģ�");
				System.out.println("��������Ҫ�޸ĵ����û�����");
				String name=sca.next();
				Admininfo a=new Admininfo();
				a.setAid(index);
				a.setAname(name);
				
				int i=adm.updateAd(a,1);
				if(i>0){
					System.out.println("�޸ĳɹ�");
					selectAd();//���ز�ѯҳ��
				}else{
					System.out.println("�޸�ʧ��");
					selectAd();//�����޸�ҳ��
				}
				break;
			case 2:
				System.out.println("���ڽ��������޸ģ�");
				System.out.println("��������Ҫ�޸ĵ������룺");
				String pwd=sca.next();
				Admininfo b=new Admininfo();
				b.setAid(index);
				b.setApwd(pwd);
				int i1=adm.updateAd(b,2);
				if(i1>0){
					System.out.println("�޸ĳɹ�");
					selectAd();//���ز�ѯҳ��
				}else{
					System.out.println("�޸�ʧ��");
					selectAd();//�����޸�ҳ��
				}
				break;
			case 3:
				System.out.println("���ڽ��и���ǩ���޸ģ�");
				System.out.println("��������Ҫ�޸ĵ��¸���ǩ����");
				String marks=sca.next();
				Admininfo c=new Admininfo();
				c.setAid(index);
				c.setAremark(marks);
				int i2=adm.updateAd(c,3);
				if(i2>0){
					System.out.println("�޸ĳɹ�");
					selectAd();//���ز�ѯҳ��
				}else{
					System.out.println("�޸�ʧ��");
					selectAd();//�����޸�ҳ��
				}
				break;
			case 4:
				System.out.println("���ڽ���ȫ����Ϣ�޸ģ�");
				System.out.println("��������Ҫ�޸ĵ����û�����");
				String name1=sca.next();
				System.out.println("��������Ҫ�޸ĵ������룺");
				String pwd1=sca.next();
				System.out.println("��������Ҫ�޸ĵ��¸���ǩ����");
				String marks1=sca.next();
				Admininfo d=new Admininfo();
				d.setAid(index);
				d.setAname(name1);
				d.setApwd(pwd1);
				d.setAremark(marks1);
				int i3=adm.updateAd(d,4);
				if(i3>0){
					System.out.println("�޸ĳɹ�");
					selectAd();//���ز�ѯҳ��
				}else{
					System.out.println("�޸�ʧ��");
					selectAd();//�����޸�ҳ��
				}
				break;
			default:
				System.out.println("��������");
				showAll();
				break;
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
