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
	
	
	//ѡ��˵�
	public static void showAll(){
		System.out.println("��ǰ�����ڵ�λ�ã�������ҳ�����γ̹���");
		System.out.println("1���γ���Ϣ�鿴");
		System.out.println("2���γ���Ϣ���");
		System.out.println("3���γ���Ϣ�޸�");
		System.out.println("4���γ���Ϣɾ��");
		System.out.println("����������ѡ�1 2 3 4�����򷵻ظ�Ŀ¼");
		int index=sca.nextInt();
		//ѡ��
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
				System.out.println("������������������");
				//���ظ�Ŀ¼
				MenueAction.Menue();
				break;
		}
	}

	//�γ�-Ա��-�༶-��Ŀ
	public static void selectCur() {
		System.out.println("���������ڽ��пγ̵Ĳ�ѯ������");
		System.out.println("��������Բ�ѯһ�²�����");
		System.out.println("1��ȫ��ѯ");
		System.out.println("2��ģ����ѯ");
		System.out.println("3����ʱ����ѯ");
		System.out.println("4��ͳ�ƿ�Ŀ����");
		System.out.println("���������ϱ�ţ�1 2 3 4 �����򷵻ظ�Ŀ¼");
		//��ѯ
		 selectSuccess();
	}
	
	public static void selectSuccess(){
		int i=sca.nextInt();
		if(i==1){
			System.out.println("�γ���Ϣ���£�");
			List<Curr> list=cud.listCu();
			System.out.println("�γ�ID\t��ʱ��\t��ʦ\t����ְλ\t������λ\t��������\t����ϵ\t�����꼶\t\tָ���༶\t�γ���");
			for(Curr c:list){
				System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
			}
			back();
		}else if(i==2){
			//ģ����ѯ
			System.out.println("�����ڽ���ģ����ѯ��");
			System.out.println("�������²�����");
			System.out.println("1����ʦ");
			System.out.println("2��ϵ��");
			System.out.println("3���꼶");
			System.out.println("4����Ŀ");
			System.out.println("���������ϱ�ţ�1 2 3 4 �����򷵻ظ�Ŀ¼");
			int ii=sca.nextInt();
			if(ii==1){
				//ģ����ʦ��
				System.out.println("ģ����ѯ��ʦ���֣�");
				System.out.println("�������ʦ����");
				String name=sca.next();
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("û�����ݣ�");
					selectCur();//����
				}else{
					System.out.println("ģ����ѯ��ʦ��Ϣ���£�");
					System.out.println("��ʦ\t\t�γ�ID\t��ʱ��\t����ְλ\t������λ\t��������\t����ϵ\t�����꼶\t\tָ���༶\t�γ���");
					for(Curr c:list){
						System.out.println(c.getEmplo().getEname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
					}
					back();
				}
			}else if(ii==2){
				//ģ����ѯϵ��
				System.out.println("ģ����ѯϵ��");
				//ϵ���ѯ
				System.out.println("��������ϵ����Բ�ѯ��");
				List<Xb> listx=xbd.listXb();
				System.out.println("ԺϵID\t����\t����");
				for(Xb x:listx){
					System.out.println(x.getXid()+"\t"+x.getXname()+"\t"+x.getXremark());
				}
				System.out.println("����������ϵ�����ƣ�");
				String name=sca.next();
				
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("û�����ݣ�");
					 selectCur();//����
				}else{
					System.out.println("ģ����ѯϵ����Ϣ���£�");
					System.out.println("����ϵ\t\t�γ�ID\t��ʱ��\t��ʦ\t����ְλ\t������λ\t��������\t�����꼶\t\tָ���༶\t�γ���");
					for(Curr c:list){
						System.out.println(c.getClasses().getGrade().getXb().getXname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
					}
					back();
				}
			}else if(ii==3){
				//ģ����ѯ�꼶
				System.out.println("ģ����ѯ�꼶��");
				//�༶��ѯ
				System.out.println("���������꼶���Բ�ѯ��");
				List<Classes> lists= cad.listCla();
				System.out.println("�༶ID\t����\t����\t�����꼶");
				for(Classes p:lists){
					System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
				}
				System.out.println("���������ϵ��꼶���ƣ�");
				String name=sca.next();
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("û�����ݣ�");
					 selectCur();//����
				}else{
					System.out.println("ģ����ѯϵ����Ϣ���£�");
					System.out.println("�����꼶\t\t�γ�ID\t��ʱ��\t��ʦ\t����ְλ\t������λ\t��������\t����ϵ\t\tָ���༶\t�γ���");
					for(Curr c:list){
						System.out.println(c.getClasses().getGrade().getGname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
					}
					back();
				}
			}else if(ii==4){
				//ģ����ѯ��Ŀ
				System.out.println("ģ����ѯ��Ŀ��");
				System.out.println("�������¿�Ŀ���Բ�ѯ��");
				List<Subjects> lists=sbd.listSu();
				System.out.println("��ĿID\t����\t����");
				for(Subjects x:lists){
					System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
				}
				System.out.println("���������Ͽ�Ŀ���ƣ�");
				String name=sca.next();
				List<Curr> list=cud.listCu(ii, name);
				if(list.isEmpty()){
					System.out.println("û�����ݣ�");
					 selectCur();//����
				}else{
					System.out.println("ģ����ѯ��Ŀ��Ϣ���£�");
					System.out.println("�γ���\t\t�γ�ID\t��ʱ��\t��ʦ\t����ְλ\t������λ\t��������\t����ϵ\t�����꼶\t\tָ���༶");
					for(Curr c:list){
						System.out.println(c.getSubjects().getSname()+"\t\t"+c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname());
					}
					back();
				}
			}
		}else if(i==3){
			//��ʱ����ѯ
			System.out.println("�������ʱ���ķ�Χ��");
			System.out.println("�������ʱ���ĵ�һ��ֵ��");
			int num=sca.nextInt();
			System.out.println("�������ʱ���ĵڶ���ֵ��");
			int num1=sca.nextInt();
			System.out.println("���ڲ�ѯ��ʱ����  "+num+"��"+num1+"�ķ�Χ");
			List<Curr> list=cud.listCu(num,num1);	
			if(list.isEmpty()){
				System.out.println("û�����ݣ�");
				selectCur();
			}else{
				System.out.println("�γ���Ϣ���£�");
				System.out.println("�γ�ID\t��ʱ��\t��ʦ\t����ְλ\t������λ\t��������\t����ϵ\t�����꼶\t\tָ���༶\t�γ���");
				for(Curr c:list){
					System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
				}
				back();
			}
		}else if(i==4){
			System.out.println("�����ڲ�ѯÿ����Ŀ���Ͽ�������");
			System.out.println("�������¿�Ŀ���Բ�ѯ��");
			//��ѯ�ɲ�ѯ�Ŀ�Ŀ
			List<Subjects> listsu=sbd.listSu();
			System.out.println("��ĿID\t����\t����");
			for(Subjects x:listsu){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			}
			System.out.println("���������Ͽ�Ŀ�����ƽ��в�ѯͳ�ƣ�");
			String name=sca.next();
			
			//��ѯ��Ŀ��ƥ��
			String res=null;
			for(Subjects s:listsu){
				if(name.equals(s.getSname())){
					System.out.println("���ڲ�ѯ��"+s.getSname()+"��Ŀͳ��");
					List<Curr> lists=cud.listCu(name);
					if(lists.isEmpty()){
						System.out.println(s.getSname()+"  �ÿ�Ŀ��ʱû���Ͽ�");
						selectCur();
					}else{
						System.out.println("��ǰ��"+s.getSname()+"��Ŀ������:"+lists.size()+"��");
						System.out.println("����\t��ʦ\t����ְλ\t������λ\t��������\t����ϵ\t�����꼶\t\tָ���༶\t�γ���");
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
			System.out.println("�Բ���û�� "+name+" ��Ŀ�����ݣ�");
			selectCur();
		}else{
			MenueAction.Menue();
		}
	}
	
	//��ӿγ�-Ա��-�༶-��Ŀ
	private static void addCur() {
		System.out.println("�����ڲ����γ̵���Ӳ�����");
		System.out.println("�������½�ʦ������ӣ�");
		List<Emplo> list=emd.lisEm();
		System.out.println("��ʦID\t����\t�Ա�\t����\t����\tְλ\t\t������λ\t\t��������");
		for(Emplo l:list){
			System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t\t"+l.getZwinfo().getPostinfo().getPname()+"\t\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
		}
		System.out.println("���������Ͻ�ʦID��ӵ��γ̣�");
		int index=sca.nextInt();
		//��ѯ������Ա����Ϣ
		Emplo em=emd.getOne(index);
		if(em.getEname()==null){
			System.out.println("û�иý�ʦ������������");
			showAll();
		}else{
			System.out.println("��������ѡ�еĽ�ʦ��Ϣ��");
			System.out.println("��ʦID\t����\t�Ա�\t����\t����\tְλ\t\t������λ\t\t��������");
			System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t\t"+em.getZwinfo().getPostinfo().getPname()+"\t\t"+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
			System.out.println("������(1/��   0/��)");
			int con=sca.nextInt();
			if(con==1){
				System.out.println("�������°༶������ӣ�");
				List<Classes> lists= cld.listCla();
				System.out.println("�༶ID\t����\t����\t�����꼶");
				for(Classes p:lists){
					System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
				}
				System.out.println("���������ϰ༶ID������ӣ�");
				int indexx=sca.nextInt();
				//�༶��Ϣ��ѯ
				Classes cla=cld.getOne(indexx);
				if(cla.getCname()==null){
					System.out.println("û������༶������������");
					addCur();
				}else{
					System.out.println("��ѡ��İ༶��Ϣ������ʾ��");
					System.out.println("�༶ID\t����\t����\t�����꼶");
					System.out.println(cla.getCid()+"\t"+cla.getCname()+"\t"+cla.getCremark()+"\t"+cla.getGrade().getGname());
					System.out.println("�����𣿣�1/��   0/��");
					int cofin=sca.nextInt();
					if(cofin==1){
						System.out.println("��Ҫѡ���Ŀ��Ϣ���£�");
						List<Subjects> listss=sbd.listSu();
						System.out.println("��ĿID\t����\t����");
						for(Subjects x:listss){
							System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
						}
						System.out.println("���������ϵĿ�Ŀ����ID��");
						int confim=sca.nextInt();
						//�༶��Ϣ��ѯ
						Subjects sub=sbd.getOne(confim);
						if(sub.getSname()==null){
							System.out.println("��������û�������Ŀ������������");
							showAll();
						}else{
							System.out.println("��Ҫ��ӵĿ�Ŀ��Ϣ���£�");
							System.out.println("��ĿID\t����\t����");
							System.out.println(sub.getSid()+"\t"+sub.getSname()+"\t"+sub.getSremark());
							System.out.println("������(1/��  0/��)");
							int nn=sca.nextInt();
							if(nn==1){
								System.out.println("����������γ̵Ŀ�ʱ��");
								//������Ϣ��װ
								int count=sca.nextInt();
								Curr cu=new Curr();
								cu.setRcount(count);
								cu.setEmplo(em);
								cu.setSubjects(sub);
								cu.setClasses(cla);
								System.out.println("��������Ҫ��ӵĿγ̵�������Ϣ��");
								System.out.println("�γ���\t\t��ʦ\t\t�༶\t\t��Ŀ");
								System.out.println(cu.getRcount()+"\t\t"+cu.getEmplo().getEname()+"\t\t"+cu.getClasses().getCname()+"\t\t"+cu.getSubjects().getSname());
								System.out.println("ȷ��Ҫ�����(1/��  0/��)");
								int mm=sca.nextInt();
								if(mm==1){
									int i=cud.addCu(cu);
									if(i>0){
										System.out.println("��ӳɹ���");
										selectCur();
									}else{
										System.out.println("���ʧ�ܣ�");
										selectCur();
									}
								}else if(mm==0){
									System.out.println("��ѡ���˳���");
									showAll();
								}else{
									System.out.println("��������");
									showAll();
								}
							}else if(nn==0){
								System.out.println("��ѡ���˳�����");
								showAll();
							}else{
								System.out.println("��������");
								addCur();
							}
						}
					}else if(cofin==0){
						System.out.println("��ѡ���˳�����");
						showAll();
					}else{
						System.out.println("������������������");
						showAll();
					}
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

	//�޸Ŀγ�ʱ
	//��ʦ
	//�༶
	//��Ŀ
	private static void updateCur() {
		System.out.println("�����ڽ��пγ̵��޸Ĳ���...");
		System.out.println("�������¿γ̿����޸ģ�");
		//�γ̲�ѯ
		List<Curr> list=cud.listCu();
		System.out.println("�γ�ID\t��ʱ��\t��ʦ\t����ְλ\t������λ\t��������\t����ϵ\t�����꼶\t\tָ���༶\t�γ���");
		for(Curr c:list){
			System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getEmplo().getZwinfo().getZname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getPname()+"\t"+c.getEmplo().getZwinfo().getPostinfo().getDeptinfo().getDname()+"\t"+c.getClasses().getGrade().getXb().getXname()+"\t"+c.getClasses().getGrade().getGname()+"\t"+c.getClasses().getCname()+"\t"+c.getSubjects().getSname());
		}
		//
		System.out.println("���������Ͽγ�ID�����޸���Ϣ��");
		int index=sca.nextInt();
		//��ȡ�γ���Ϣ
		Curr cu=cud.getOne(index);
		if(cu.getEmplo()==null){
			System.out.println("û�иÿγ�����������");
			showAll();
		}else{
			System.out.println("��Ҫ�޸ĵĿγ���Ϣ���£�");
			System.out.println("�γ�ID\t��ʱ��\t��ʦ\t�γ���");
			System.out.println(cu.getRid()+"\t"+cu.getRcount()+"\t"+cu.getEmplo().getEname()+"\t"+cu.getSubjects().getSname());
			System.out.println("������(1/��  0/��)");
			int confim=sca.nextInt();
			if(confim==1){
				successUpte(index);
			}else if(confim==0){
				System.out.println("��ѡ���˳�����");
				showAll();
			}else{
				System.out.println("��������");
				showAll();
			}
		}
		
		
		
	}

	public static void successUpte(int index) {
		System.out.println("�����������²���");
		System.out.println("1���γ̿�ʱ");
		System.out.println("2���γ̰༶");
		System.out.println("3���γ̿�Ŀ");
		System.out.println("4���γ̽�ʦ");
		System.out.println("�������������ϱ�ţ�1 2 3 4 �����򷵻ظ�Ŀ¼");
		int i=sca.nextInt();
		//��װ��Ϣ
		Curr cu=new Curr();
		cu.setRid(index);
		
		if(i==1){
			System.out.println("�������޸Ŀγ̵Ŀ�ʱ��");
			System.out.println("��������Ҫ�޸ĵĿ�ʱ��");
			int count=sca.nextInt();
			cu.setRcount(count);
			int is=cud.updateCu(cu, i);
			if(is>0){
				System.out.println("�޸ĳɹ���");
				selectCur();
			}else{
				System.out.println("�޸�ʧ�ܣ�");
				selectCur();
			}
		}else if(i==2){
			System.out.println("����������γ̵İ༶��");
			System.out.println("�������¿γ̰༶���Ը��ģ�");
			//�༶��ѯ
			List<Classes> list= cld.listCla();
			System.out.println("�༶ID\t����\t����\t�����꼶");
			for(Classes p:list){
				System.out.println(p.getCid()+"\t"+p.getCname()+"\t"+p.getCremark()+"\t"+p.getGrade().getGname());
			};
			System.out.println("������������Ҫ�޸ĵĿγ�Id��");
			int nm=sca.nextInt();
			Classes cla=cld.getOne(nm);
			if(cla.getCname()==null){
				System.out.println("��������û��������Ŀγ�");
				showAll();
			}else{
				System.out.println("������Ŀγ���Ϣ���£�");
				System.out.println("�༶ID\t����\t����\t�����꼶");
				System.out.println(cla.getCid()+"\t"+cla.getCname()+"\t"+cla.getCremark()+"\t"+cla.getGrade().getGname());
				System.out.println("ȷ���𣿣�1/��   0/��");
				int nn=sca.nextInt();
				if(nn==1){
					System.out.println("�����޸Ŀγ̵İ༶��Ϣ");
					cu.setClasses(cla);
					
					int ii=cud.updateCu(cu, i);
					if(ii>0){
						System.out.println("�޸ĳɹ���");
						 selectCur();
					}else{
						System.out.println("�޸�ʧ�ܣ�");
						 selectCur();
					}
				}else if(nn==0){
					System.out.println("�㳷���˲���");
					showAll();
				}else{
					System.out.println("��������");
					showAll();
				}
			}
		}else if(i==3){
			System.out.println("�����ڽ��пγ̵Ŀ�Ŀ�޸ģ�");
			System.out.println("�������¿�Ŀ�����޸ģ�");
			//��Ŀ��ѯ
			List<Subjects> list=sbd.listSu();
			System.out.println("��ĿID\t����\t����");
			for(Subjects x:list){
				System.out.println(x.getSid()+"\t"+x.getSname()+"\t"+x.getSremark());
			};
			//
			System.out.println("������Ҫ���ĵĿ�ĿID��");
			int nn=sca.nextInt();
			Subjects sub=sbd.getOne(nn);
			if(sub.getSname()==null){
				System.out.println("��������û�иÿ�Ŀ");
				showAll();
			}else{
				System.out.println("��������Ҫ�޸ĵĿγ̿�Ŀ��Ϣ��");
				System.out.println("��ĿID\t����\t����");
				System.out.println(sub.getSid()+"\t"+sub.getSname()+"\t"+sub.getSremark());
				System.out.println("ȷ���޸ģ���1/��  0/��");
				int coni=sca.nextInt();
				if(coni==1){
					System.out.println("�����޸Ŀγ̿�Ŀ��Ϣ");
					cu.setSubjects(sub);
					int confirm=cud.updateCu(cu, i);
					if(confirm>0){
						System.out.println("�޸ĳɹ���");
						selectCur();
					}else{
						System.out.println("�޸�ʧ�ܣ�");
						selectCur();
					}
					
				}else if(coni==0){
					System.out.println("��ѡ���˳�����");
					showAll();
				}else{
					System.out.println("��������");
					showAll();
				}
			}
		}else if(i==4){
			System.out.println("�����ڽ��пγ̵Ľ�ʦ�޸ģ�");
			System.out.println("�������¿�Ŀ�����޸ģ�");
			//��ʦ��ѯ
			System.out.println("���н�ʦ������ʾ��");
			List<Emplo> list=emd.lisEm();
			System.out.println("Ա��ID\t����\t�Ա�\t����\t����\tְλ\t\t������λ\t\t��������");
			for(Emplo l:list){
				System.out.println(l.getEid()+"\t"+l.getEname()+"\t"+l.getEsex()+"\t"+l.getEage()+"\t"+l.getEedu()+"\t"+l.getZwinfo().getZname()+"\t\t"+l.getZwinfo().getPostinfo().getPname()+"\t\t"+l.getZwinfo().getPostinfo().getDeptinfo().getDname());
			}
			//
			System.out.println("������Ҫ���ĵĽ�ʦID��");
			int nn=sca.nextInt();
			Emplo em=emd.getOne(nn);
			if(em.getEname()==null){
				System.out.println("��������û�иý�ʦ");
				showAll();
			}else{
				System.out.println("��������Ҫ�޸ĵĿγ̽�ʦ��Ϣ��");
				System.out.println("Ա��ID\t����\t�Ա�\t����\t����\tְλ\t\t������λ\t\t��������");
				System.out.println(em.getEid()+"\t"+em.getEname()+"\t"+em.getEsex()+"\t"+em.getEage()+"\t"+em.getEedu()+"\t"+em.getZwinfo().getZname()+"\t\t"+em.getZwinfo().getPostinfo().getPname()+"\t\t"+em.getZwinfo().getPostinfo().getDeptinfo().getDname());
				System.out.println("ȷ���޸ģ���1/��  0/��");
				int coni=sca.nextInt();
				if(coni==1){
					System.out.println("�����޸Ŀγ̽�ʦ��Ϣ");
					cu.setEmplo(em);
					int confirm=cud.updateCu(cu, i);
					if(confirm>0){
						System.out.println("�޸ĳɹ���");
						selectCur();
					}else{
						System.out.println("�޸�ʧ�ܣ�");
						selectCur();
					}
				}else if(coni==0){
					System.out.println("��ѡ���˳�����");
					showAll();
				}else{
					System.out.println("��������");
					showAll();
				}
			}
		}
	}

	//ɾ���γ�
	private static void deleteCur() {
		System.out.println("�����ڽ��пγ̵�ɾ��������");
		System.out.println("�����������¿γ̿���ɾ����");
		List<Curr> list=cud.listCu();
		System.out.println("�γ�ID\t��ʱ��\t��ʦ\t�γ���");
		for(Curr c:list){
			System.out.println(c.getRid()+"\t"+c.getRcount()+"\t"+c.getEmplo().getEname()+"\t"+c.getSubjects().getSname());
		};
		System.out.println("��������Ҫɾ���Ŀγ�ID:");
		int index=sca.nextInt();
		Curr cu=cud.getOne(index);
		if(cu.getEmplo()==null){
			System.out.println("��������û������γ̣�����������");
			showAll();
		}else{
			System.out.println("��Ҫ�ϿεĿγ���Ϣ���£�");
			System.out.println("�γ�ID\t��ʱ��\t��ʦ\t�γ���");
			System.out.println(cu.getRid()+"\t"+cu.getRcount()+"\t"+cu.getEmplo().getEname()+"\t"+cu.getSubjects().getSname());
			System.out.println("ȷ��ɾ���𣿣�1/��   0/��");
			int in=sca.nextInt();
			if(in==1){
				System.out.println("����ɾ��...");
				
				int i=cud.deleteCu(index);
				if(i>0){
					System.out.println("ɾ���ɹ���");
					selectCur();
				}else{
					System.out.println("ɾ��ʧ�ܣ�");
					selectCur();
				}
			}else if(in==0){
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
