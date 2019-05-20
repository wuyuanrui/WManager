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
	
	//ѡ��˵�
	public static void showAll(){
		System.out.println("��ǰ�����ڵ�λ�ã�������ҳ����Ա������");
		System.out.println("1��Ա����Ϣ�鿴");
		System.out.println("2��Ա����Ϣ���");
		System.out.println("3��Ա����Ϣ�޸�");
		System.out.println("4��Ա����Ϣɾ��");
		System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
		
		int index=sca.nextInt();
		//ѡ��
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
				System.out.println("������������������");
				//���ظ�Ŀ¼
				MenueAction.Menue();
				break;
		}
	}
	
	//��ѯԱ��
	public static void selectEm() {
		System.out.println("����������ִ��Ա����ѯ����");
		System.out.println("����Ա��������ʾ��");
		List<Emplo> list=emd.lisEm();
		System.out.println("Ա��ID\t����\t�Ա�\t����\t����\tְλ\t\t������λ\t\t��������");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t\t"+l.getZwinfo().getPostinfo().getPname()+"\t\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		}
		back();
	}
	
	//ɾ��Ա��
	public static void deleteEm() {
		System.out.println("������ִ��ɾ������");
		System.out.println("��������Ա������ɾ����");
		//��ѯ���е�Ա��
		System.out.println("����Ա��������ʾ��");
		List<Emplo> list=emd.lisEm();
		System.out.println("Ա��ID\t����\t�Ա�\t����\t����\tְλ\t������λ\t��������");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t"+l.getZwinfo().getPostinfo().getPname()+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		}
		System.out.println("��������Ҫɾ����Ա��ID��");
		int index=sca.nextInt();
		Emplo em=emd.getOne(index);
		if(null==em.getEname()){
			System.out.println("û�����Ա����������ѡ��");
			showAll();
		}else{
			System.out.println("��ǰ��Ҫɾ����Ա���ǣ�");
			System.out.println("Ա��ID\t����\t�Ա�\t����\t����\tְλ\t������λ\t��������");
			System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t"+em.getZwinfo().getPostinfo().getPname()+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
			System.out.println("ȷ��Ҫɾ������1/��   0/��");
			int index2=sca.nextInt();
			if(index2==1){
				int i=emd.deleteEm(index);
				if(i>0){
					System.out.println("ɾ���ɹ�");
					selectEm();
				}else{
					System.out.println("ɾ��ʧ��");
					selectEm();
				}
			}else if(index2==0){
				System.out.println("�㳷���˲���");
				showAll();
			}else{
				System.out.println("�������");
				showAll();
			}
		}
	}
	
	
	
	//�޸�
	public static void updateEm() {
		System.out.println("�����ڽ����޸�Ա���Ĳ�������������²������");
		System.out.println("��ǰ������޸ĵ�Ա�������£�");
		List<Emplo> list=emd.lisEm();
		System.out.println("Ա��ID\t����\t�Ա�\t����\t����\tְλ\t������λ\t��������");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t"+l.getZwinfo().getPostinfo().getPname()+"\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		};
		System.out.println("��������Ҫ�޸ĵ�Ա��ID��");
		int eid=sca.nextInt();
		Emplo em=emd.getOne(eid);
		if(null==em.getEname()){
			System.out.println("û�����Ա��,����������");
			showAll();
		}else{
			System.out.println("��������Ҫ�޸ĵ�Ա����Ϣ��");
			System.out.println("Ա��ID\t����\t�Ա�\t����\t����\tְλ\t������λ\t��������");
			System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t"+em.getZwinfo().getPostinfo().getPname()+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
			System.out.println("ȷ���޸ģ�(1/��  0/��)");
			int i=sca.nextInt();
			if(i==1){
				updateSuccess(eid);
			}else if(i==0){
				System.out.println("�㳷���˲���");
				showAll();
			}else{
				System.out.println("��������");
				showAll();
			}
		}
	}

	public static void updateSuccess(int eid) {
		System.out.println("�����������²�����");
		System.out.println("1���޸�Ա����ͨ��Ϣ");
		System.out.println("2���޸�Ա��ְλ");
		
		System.out.println("���������ϱ�ţ�");
		int i=sca.nextInt();
		
		Emplo em=new Emplo();
		em.setEid(eid);
		if(i==1){
			System.out.println("�������޸�Ա����ͨ��Ϣ...");
			System.out.println("������Ա��������");
			String name=sca.next();
			System.out.println("������Ա���Ա�");
			String sex=sca.next();
			System.out.println("������Ա�����䣺");
			int age=sca.nextInt();
			System.out.println("������Ա��ѧ����");
			String edu=sca.next();
			em.setEname(name);
			em.setEsex(sex);
			em.setEage(age);
			em.setEedu(edu);
			int m=emd.updateEm(em, i);
			if(m>0){
				System.out.println("�޸ĳɹ���");
				selectEm();
			}else{
				System.out.println("�޸�ʧ�ܣ�");
				selectEm();
			}
		}else if(i==2){
			//��ְλ
			System.out.println("ְλ��Ϣ������ʾ��");
			List<Zwinfo> list=zwd.listZw();
			System.out.println("ID\t\tְλ����\t\t����\t\t������λ\t\t��������");
			for(Zwinfo z:list){
				System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"\t"+z.getPostinfo().getPname()+"\t\t"+z.getPostinfo().getDeptinfo().getDname());
			}
			System.out.println("����������ְλID:");
			int zid=sca.nextInt();
			System.out.println("��������ǣ�"+zid);
			Zwinfo zw=zwd.getOne(zid);
			
			if(null==zw.getZname()){
				System.out.println("û�����ְλ������������");
				showAll();
			}else{
				System.out.println("��Ҫ�޸ĳ�ְλ��Ϣ���£�");
				System.out.println("ID\t\tְλ����\t\t����\t\t������λ\t\t��������");
				System.out.println(zw.getZid()+"\t\t"+zw.getZname()+"\t\t"+zw.getZremark()+"\t\t"+zw.getPostinfo().getPname()+"\t\t"+zw.getPostinfo().getDeptinfo().getDname());
				System.out.println("ȷ�ϸ����𣿣�1/��   0/��");
				int confim=sca.nextInt();
				if(confim==1){
					System.out.println("�������޸�Ա��ְλ��Ϣ...");
					em.setZwinfo(zw);//
					int iii=emd.updateEm(em, i);
					if(iii>0){
						System.out.println("�޸ĳɹ�");
						selectEm();
					}else{
						System.out.println("�޸�ʧ��");
						selectEm();
					}
				}else if(confim==0){
					System.out.println("�㳷���˲���");
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
//���
	public static void addEm() {
		System.out.println("�����ڽ������Ա���Ĳ�������������²������");
		System.out.println("������Ա������");
		String name=sca.next();
		System.out.println("������Ա���Ա�");
		String sex=sca.next();
		System.out.println("������Ա������");
		int eage=sca.nextInt();
		System.out.println("������Ա��ѧ��");
		String eedu=sca.next();
		//��Ϣ��װ
		Emplo em=new Emplo();
		em.setEname(name);
		em.setEsex(sex);
		em.setEage(eage);
		em.setEedu(eedu);
		System.out.println("�������£�");
		//���Ų�ѯ
		List<Deptinfo> list=dpd.listDep();
		System.out.println("��ѯ��Ϣ���£�");
		System.out.println("����ID\t��������\t��������");
		for(Deptinfo d:list){
			System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
		};
		System.out.println("��ѡ���������в���ID:");
		int  did=sca.nextInt();
		Deptinfo de=dpd.getOne(did);
		if(null==de.getDname()){
			System.out.println("û��������ţ�����������");
			showAll();
		}else{
			//��λ�鿴
			System.out.println("��λ��Ϣ���£�");
			List<Postinfo> lists= pod.listPo();
			System.out.println("��λID\t��λ����\t��λ����\t��������");
			for(Postinfo p:lists){
				System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
			}
			System.out.println("��ѡ������������һ����λID��");
			int pid=sca.nextInt();
			Postinfo po=pod.getOne(pid);
			if(null==po.getPname()){
				System.out.println("û�������λ������������");
				showAll();
			}else{
				//ְλ�鿴
				System.out.println("ְλ��Ϣ������ʾ��");
				List<Zwinfo> listss=zwd.listZw();
				System.out.println("ID\t\tְλ����\t\t����\t\t������λ\t\t��������");
				for(Zwinfo z:listss){
					System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"\t"+z.getPostinfo().getPname()+"\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				System.out.println("��������������һ��ְλID��");
				int zid=sca.nextInt();
				Zwinfo zw=zwd.getOne(zid);
				if(null==zw.getZname()){
					System.out.println("û�����ְλ������������");
					showAll();
				}else{
					//��Ϣ��װ
					po.setDeptinfo(de);
					zw.setPostinfo(po);
					em.setZwinfo(zw);
					System.out.println("��������Ҫ��ӵ���Ϣ:");
					System.out.println("����\t�Ա�\t����\t����\tְλ\t������λ\t��������");
					System.out.println(em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t"+em.getZwinfo().getPostinfo().getPname()+"\t"+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
					System.out.println("��Ϣ����ȷ����ӣ�(1/��  0/��)");
					int i=sca.nextInt();
					if(i==1){
						int ii=emd.add(em);
						if(ii>0){
							System.out.println("��ӳɹ�");
							 selectEm();
						}else{
							System.out.println("���ʧ��");
							selectEm();
						}
					}else if(i==0){
						System.out.println("��������");
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
				}
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
