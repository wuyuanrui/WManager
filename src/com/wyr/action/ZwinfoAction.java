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
	
	//ѡ��˵�
			public static void showAll(){
				System.out.println("��ǰ�����ڵ�λ�ã�������ҳ����ְλ����");
				System.out.println("1��ְλ��Ϣ�鿴");
				System.out.println("2��ְλ��Ϣ���");
				System.out.println("3��ְλ��Ϣ�޸�");
				System.out.println("4��ְλ��Ϣɾ��");
				System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
				
				int index=sca.nextInt();
				//ѡ��
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
						System.out.println("������������������");
						//���ظ�Ŀ¼
						MenueAction.Menue();
						break;
				}
			}
			
			//��ѯְλ��Ϣ
			public static void selectZw() {
				System.out.println("ְλ��Ϣ������ʾ��");
				List<Zwinfo> list=zwd.listZw();
				System.out.println("ID\t\tְλ����\t\t����\t\t������λ\t\t��������");
				for(Zwinfo z:list){
					System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"\t\t"+z.getPostinfo().getPname()+"\t\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				back();
			}

			
			//ɾ��ְλ��Ϣ
			public static void deleteZw() {
				System.out.println("������ִ��ְλ��ɾ������");
				System.out.println("������ʾ�����ɾ������ְλ��Ϣ��");
				List<Zwinfo> list=zwd.listZw();
				System.out.println("ID\t\tְλ����\t\t����\t\t������λ\t\t��������");
				for(Zwinfo z:list){
					System.out.println(z.getZid()+"\t\t"+z.getZname()+"\t\t"+z.getZremark()+"t\t"+z.getPostinfo().getPname()+"t\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				System.out.println("��������Ҫɾ����ְλID:");
				int index=sca.nextInt();
				Zwinfo zw=zwd.getOne(index);
				if(null==zw.getZname()){
					System.out.println("û�����ְλ������ѡ��");
					showAll();
				}else{
					System.out.println("��Ҫɾ����ְλ������ʾ��");
					System.out.println("ID\tְλ����\t����\t������λ\t��������");
					System.out.println(zw.getZid()+"\t"+zw.getZname()+"\t"+zw.getZremark()+"\t"+zw.getPostinfo().getPname()+"\t"+zw.getPostinfo().getDeptinfo().getDname());
					System.out.println("ȷ��ɾ���𣿣�1/��  0/��");
					int index1=sca.nextInt();
					if(index1==1){
						System.out.println("����ɾ��...");
						int i=zwd.deleteZw(index);
						if(i>0){
							System.out.println("ɾ���ɹ���");
							selectZw();
						}else{
							System.out.println("ɾ��ʧ�ܣ�");
							selectZw();
						}
					}else if(index1==0){
						System.out.println("�㳷��ɾ���ˣ�");
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
				}
			}

			
			//�޸�ְλ-��λ-����
			public static void updateZw() {
				System.out.println("������ִ��ְλ���޸Ĳ���");
				System.out.println("������ʾ����������ְλ�����޸ģ�");
				List<Zwinfo> list=zwd.listZw();
				System.out.println("ID\tְλ����\t����\t������λ\t��������");
				for(Zwinfo z:list){
					System.out.println(z.getZid()+"\t"+z.getZname()+"\t"+z.getZremark()+"\t"+z.getPostinfo().getPname()+"\t"+z.getPostinfo().getDeptinfo().getDname());
				}
				System.out.println("��������Ҫ�޸ĵ�ְλID");
				int zid=sca.nextInt();
				Zwinfo zw=zwd.getOne(zid);
				if(null==zw.getZname()){
					System.out.println("û�����ְλ���������޸�..");
					showAll();			
				}else{
					System.out.println("��������Ҫ�޸ĵ� ְλ��Ϣ��");
					System.out.println("ְλID\t����\t����\t������λ\t��������");
					System.out.println(zw.getZid()+"\t"+zw.getZname()+"\t"+zw.getZremark()+"\t"+zw.getPostinfo().getPname()+"\t"+zw.getPostinfo().getDeptinfo().getDname());
					System.out.println("ȷ���޸��𣿣�1/��    0/��");
					int confim=sca.nextInt();
					if(confim==1){
						//�޸Ĳ���
						updateSuccess(zid);
						
					}else if(confim==0){
						System.out.println("��ѡ���˳���");
						showAll();
					}else{
						System.out.println("��������");
						showAll();
					}
				}
			}
			
			
			
			public static void updateSuccess(int zid) {
				System.out.println("���������޸��");
				System.out.println("1���޸�ְλ��Ϣ");
				System.out.println("2���޸�������λ");
				System.out.println("���������ѡ��(1 2)");
				int i=sca.nextInt();
				Zwinfo zw=new Zwinfo();
				zw.setZid(zid);//�����޸ĵ��ֶ�
				if(i==1){
					System.out.println("�������޸�ְλ����Ϣ..");
					System.out.println("��������Ҫ���ĵ�ְλ���ƣ�");
					String name=sca.next();
					System.out.println("��������Ҫ�޸ĵ�ְλ������");
					String mark=sca.next();
					//��װ�û��������Ϣ
					zw.setZname(name);
					zw.setZremark(mark);
					//�޸Ĳ����ֶ�
					int n=zwd.updateZw(zw, i);
					if(n>0){
						System.out.println("�޸ĳɹ���");
						selectZw();
					}else{
						System.out.println("�޸�ʧ�ܣ�");
						showAll();;
					}
				}else if(i==2){
					System.out.println("�������޸�ְλ��������λ...");
					System.out.println("�����������¸�λ����ѡ��");
					//��λ��ѯ
					List<Postinfo> list= pod.listPo();
					System.out.println("��λID\t��λ����\t��λ����\t��������");
					for(Postinfo p:list){
						System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
					}
					System.out.println("��������Ҫ���ĵ�������λID��");
					int pid=sca.nextInt();
					System.out.println("��������ѡ���������λ��Ϣ");
					Postinfo po=pod.getOne(pid);
					if(null==po.getPname()){
						System.out.println("û�������λ��������ѡ��");
						showAll();
					}else{
						System.out.println("��λID\t����\t����\t��������");
						System.out.println(po.getPid()+"\t"+po.getPname()+"\t"+po.getPremark()+"\t"+po.getDeptinfo().getDname());
						System.out.println("ȷ���޸���(1/��  0/��)");
						int confim=sca.nextInt();
						//��װ��Ϣ
						zw.setPostinfo(po);
						if(confim==1){
							
							int ii=zwd.updateZw(zw, i);
							if(ii>0){
								System.out.println("�޸ĳɹ���");
								selectZw();
							}else{
								System.out.println("�޸�ʧ��");
								selectZw();
							}
							
						}else if(confim==0){
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

			
			//���ְλ����λ������
			public static void addZw() {
				System.out.println("���������ڽ���ְλ��Ϣ�����");
				System.out.println("��������²������������Ϣ��");
				System.out.println("�����������µĲ��ſ���ѡ��");
				//���Ų�ѯ
				List<Deptinfo> list=dpd.listDep();
				System.out.println("����ID\t��������\t��������");
				for(Deptinfo d:list){
					System.out.println(d.getDid()+"\t"+d.getDname()+"\t"+d.getDremark());
				};
				System.out.println("������ְλ�Ĳ���ID:");
				int dep=sca.nextInt();
				//��ѯ�Ƿ����Ÿ�����
				Deptinfo de=dpd.getOne(dep);
				if(null==de.getDname()){
					System.out.println("û��������ţ���������ӣ�");
					showAll();
				}else{
					//��ѯ��λ
					System.out.println("�����������¸�λ����ѡ��");
					List<Postinfo> list1= pod.listPo();
					System.out.println("��λID\t��λ����\t��λ����\t��������");
					for(Postinfo p:list1){
						System.out.println(p.getPid()+"\t"+p.getPname()+"\t"+p.getPremark()+"\t"+p.getDeptinfo().getDname());
					}
					System.out.println("��������Ҫѡ��ĸ�λID:");
					int po=sca.nextInt();
					Postinfo post=pod.getOne(po);
					if(null==post.getPname()){
						System.out.println("û�������λŶ������������");
						showAll();
					}else{
						System.out.println("��������ѡ�еĲ�����Ϣ��");
						System.out.println("����ID\t����\t����");
						System.out.println(de.getDid()+"\t"+de.getDname()+"\t"+de.getDremark());
						System.out.println("��������ѡ�еĸ�λ��Ϣ��");
						System.out.println("��λID\t����\t����");
						System.out.println(post.getPid()+"\t"+post.getPname()+"\t"+post.getPremark());
						System.out.println("�����𣿣�1/��  0/��");
						int confim=sca.nextInt();
						if(confim==1){
							//���������Ϣ����
							System.out.println("����¼��ְλ����Ϣ...");
							System.out.println("����ְλ����:");
							String name =sca.next();
							System.out.println("������ְλ��������");
							String mark=sca.next();
							System.out.println("��ӵ���Ϣ������ʾ��");
							System.out.println("ְλ\t��λ\t����");
							System.out.println(name+"\t"+post.getPname()+"\t"+de.getDname());
							System.out.println("ȷ�������𣿣�1/��  0/�� ��");
							int i=sca.nextInt();
							System.out.println("����¼����Ϣ");
							//��װ��Ϣ
							Zwinfo zw=new Zwinfo();
							
							zw.setZname(name);
							zw.setZremark(mark);
							//��λ��װ������Ϣ
							post.setDeptinfo(de);
							//ְλ��װ��λ��Ϣ
							zw.setPostinfo(post);
							//���ְλ��Ϣ
							int n=zwd.addZw(zw);
							
							if(n>0){
								System.out.println("��ӳɹ���");
								selectZw();
							}else{
								System.out.println("���ʧ��!");
								selectZw();
							}
						}else if(confim==0){
							System.out.println("��ѡ���˳�����");
							showAll();
						}else{
							System.out.println("��������");
							showAll();
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
