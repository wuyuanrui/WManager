package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.ICurrDao;
import com.wyr.pojo.Classes;
import com.wyr.pojo.Curr;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Emplo;
import com.wyr.pojo.Grade;
import com.wyr.pojo.Postinfo;
import com.wyr.pojo.Subjects;
import com.wyr.pojo.Xb;
import com.wyr.pojo.Zwinfo;
import com.wyr.until.JDBC;

public class CurrDao implements ICurrDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	public CurrDao(){
		jdbc=new JDBC();
	}
	
	//��ѯ�γ̰���-Ա��-�༶-��Ŀ
	/*--System.out.println("�γ�ID\t��ʱ��\t��ʦ\t����ְλ\t������λ\t��������\t�����꼶\tָ���༶\t�γ���");*/
	@Override
	public List<Curr> listCu() {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
			
			
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid";
			
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//�γ�
				Curr cu=new Curr();
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				//��ʦ
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				//ְλ
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(8));
				zw.setZname(res.getString(9));
				//��λ
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(10));
				po.setPname(res.getString(11));
				//����
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(12));
				de.setDname(res.getString(13));
				Xb x=new Xb();
				x.setXid(res.getInt(14));
				x.setXname(res.getString(15));
				//�꼶
				Grade gr=new Grade();
				gr.setGid(res.getInt(16));
				gr.setGname(res.getString(17));
				//�༶
				Classes cla=new Classes();
				cla.setCid(res.getInt(18));
				cla.setCname(res.getString(19));
				
				//��Ŀ
				Subjects s=new Subjects();
				s.setSid(res.getInt(20));
				s.setSname(res.getString(21));
				
				//��װ��Ϣ
				//����
				po.setDeptinfo(de);
				//��λ
				zw.setPostinfo(po);
				//ְλ
				em.setZwinfo(zw);
	
				//ϵ��
				gr.setXb(x);
				//�꼶
				cla.setGrade(gr);
				
				//�༶
				cu.setClasses(cla);
				//��ʦ
				cu.setEmplo(em);
				//��Ŀ
				cu.setSubjects(s);
				
				
				list.add(cu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

	//���--Ա-�༶-��Ŀ
	@Override
	public int addCu(Curr cu) {
		int i=0;
		try {

			conn=jdbc.getConnection();
			conn.setAutoCommit(false);
			String sql="insert into curr values('"+cu.getRcount()+"','"+cu.getEmplo().getEid()+"','"+cu.getClasses().getCid()+"','"+cu.getSubjects().getSid()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	@Override
	public int updateCu(Curr cu,int index) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql=null;
			if(index==1){
				//�޸Ŀ�ʱ
				 sql="update curr set rcount='"+cu.getRcount()+"' where rid='"+cu.getRid()+"'";
			}else if(index==2){
				//�޸İ༶
				sql="update curr set cid='"+cu.getClasses().getCid()+"' where rid='"+cu.getRid()+"'";
			}else if(index==3){
				//�޸Ŀ�Ŀ
				 sql="update curr set sid='"+cu.getSubjects().getSid()+"' where rid='"+cu.getRid()+"'";
			}else if(index==4){
				//������ʦ
				sql="update curr set eid='"+cu.getEmplo().getEid()+"' where rid='"+cu.getRid()+"'";
			}
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	
	//ɾ��
	@Override
	public int deleteCu(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from curr where rid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	
	
	//��ѯһ��
	@Override
	public Curr getOne(int id) {
		Curr cu=new Curr();
		try {
			conn=jdbc.getConnection();
			String sql="select c.rid,c.rcount,e.eid,e.ename,e.esex,e.eage,e.eedu,s.sid,s.sname,s.sremark from curr c left join emplo e "
					+ "on c.eid=e.eid left join classes cl "
					+ "on cl.cid=c.cid left join subjects s "
					+ "on c.sid=s.sid where rid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				Subjects s=new Subjects();
				s.setSid(res.getInt(8));
				s.setSname(res.getString(9));
				s.setSremark(res.getString(10));
				//��װ��Ϣ
				cu.setEmplo(em);
				cu.setSubjects(s);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return cu;
	}

	//ģ����ѯ
	@Override
	public List<Curr> listCu(int index, String num) {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
			if(index==1){
				//ģ����ѯ���ݽ�ʦ����
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where e.ename like '%"+num+"%'";
			}else if(index==2){
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where x.xname like '%"+num+"%'";
			}else if(index==3){
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where g.gname like '%"+num+"%'";
			}else if(index==4){
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where s.sname like '%"+num+"%'";
			}
		pre=conn.prepareStatement(sql);
		res=pre.executeQuery();
		while(res.next()){
			//�γ�
			Curr cu=new Curr();
			cu.setRid(res.getInt(1));
			cu.setRcount(res.getInt(2));
			//��ʦ
			Emplo em=new Emplo();
			em.setEid(res.getInt(3));
			em.setEname(res.getString(4));
			em.setEsex(res.getString(5));
			em.setEage(res.getInt(6));
			em.setEedu(res.getString(7));
			//ְλ
			Zwinfo zw=new Zwinfo();
			zw.setZid(res.getInt(8));
			zw.setZname(res.getString(9));
			//��λ
			Postinfo po=new Postinfo();
			po.setPid(res.getInt(10));
			po.setPname(res.getString(11));
			//����
			Deptinfo de=new Deptinfo();
			de.setDid(res.getInt(12));
			de.setDname(res.getString(13));
			Xb x=new Xb();
			x.setXid(res.getInt(14));
			x.setXname(res.getString(15));
			//�꼶
			Grade gr=new Grade();
			gr.setGid(res.getInt(16));
			gr.setGname(res.getString(17));
			//�༶
			Classes cla=new Classes();
			cla.setCid(res.getInt(18));
			cla.setCname(res.getString(19));
			
			//��Ŀ
			Subjects s=new Subjects();
			s.setSid(res.getInt(20));
			s.setSname(res.getString(21));
			
			//��װ��Ϣ
			//����
			po.setDeptinfo(de);
			//��λ
			zw.setPostinfo(po);
			//ְλ
			em.setZwinfo(zw);

			//ϵ��
			gr.setXb(x);
			//�꼶
			cla.setGrade(gr);
			
			//�༶
			cu.setClasses(cla);
			//��ʦ
			cu.setEmplo(em);
			//��Ŀ
			cu.setSubjects(s);
			
			
			list.add(cu);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		jdbc.close(pre, res, conn);
	}
	return list;
	}

	@Override
	public List<Curr> listCu(int index,int index2) {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where rcount between '"+index+"' and '"+index2+"'  ";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//�γ�
				Curr cu=new Curr();
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				//��ʦ
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				//ְλ
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(8));
				zw.setZname(res.getString(9));
				//��λ
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(10));
				po.setPname(res.getString(11));
				//����
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(12));
				de.setDname(res.getString(13));
				Xb x=new Xb();
				x.setXid(res.getInt(14));
				x.setXname(res.getString(15));
				//�꼶
				Grade gr=new Grade();
				gr.setGid(res.getInt(16));
				gr.setGname(res.getString(17));
				//�༶
				Classes cla=new Classes();
				cla.setCid(res.getInt(18));
				cla.setCname(res.getString(19));
				
				//��Ŀ
				Subjects s=new Subjects();
				s.setSid(res.getInt(20));
				s.setSname(res.getString(21));
				
				//��װ��Ϣ
				//����
				po.setDeptinfo(de);
				//��λ
				zw.setPostinfo(po);
				//ְλ
				em.setZwinfo(zw);
	
				//ϵ��
				gr.setXb(x);
				//�꼶
				cla.setGrade(gr);
				
				//�༶
				cu.setClasses(cla);
				//��ʦ
				cu.setEmplo(em);
				//��Ŀ
				cu.setSubjects(s);
				
				
				list.add(cu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

	@Override
	public List<Curr> listCu(String name) {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
			
			
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname ,count(*)"
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid  "
						+ " group by c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "having s.sname='"+name+"'";
			
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//�γ�
				Curr cu=new Curr();
				
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				//��ʦ
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				//ְλ
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(8));
				zw.setZname(res.getString(9));
				//��λ
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(10));
				po.setPname(res.getString(11));
				//����
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(12));
				de.setDname(res.getString(13));
				Xb x=new Xb();
				x.setXid(res.getInt(14));
				x.setXname(res.getString(15));
				//�꼶
				Grade gr=new Grade();
				gr.setGid(res.getInt(16));
				gr.setGname(res.getString(17));
				//�༶
				Classes cla=new Classes();
				cla.setCid(res.getInt(18));
				cla.setCname(res.getString(19));
				
				//��Ŀ
				Subjects s=new Subjects();
				s.setSid(res.getInt(20));
				s.setSname(res.getString(21));
				
				//��װ��Ϣ
				//����
				po.setDeptinfo(de);
				//��λ
				zw.setPostinfo(po);
				//ְλ
				em.setZwinfo(zw);
	
				//ϵ��
				gr.setXb(x);
				//�꼶
				cla.setGrade(gr);
				
				//�༶
				cu.setClasses(cla);
				//��ʦ
				cu.setEmplo(em);
				//��Ŀ
				cu.setSubjects(s);
				//��ʱ����
				cu.setCount(res.getInt(22));
				
				list.add(cu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

}
