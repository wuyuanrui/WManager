package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.IEmploDao;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Emplo;
import com.wyr.pojo.Postinfo;
import com.wyr.pojo.Zwinfo;
import com.wyr.until.JDBC;

public class EmploDao implements IEmploDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	//��ѯԱ����Ϣ-ְλ-��λ
	@Override
	public List<Emplo> lisEm() {
		List<Emplo> list=new ArrayList<Emplo>();
		try {
			conn=jdbc.getConnection();
			String sql="select e.*,z.zname,z.zremark,z.pid,p.pname,p.premark,d.did,d.dname,d.dremark "
					+ "from emplo e left join zwinfo z "
					+ "on e.zid=z.zid left join postinfo p"
					+ " on z.pid=p.pid left join deptinfo d "
					+ "on p.did=d.did";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				Emplo em=new Emplo();
				em.setEid(res.getInt(1));
				em.setEname(res.getString(2));
				em.setEsex(res.getString(3));
				em.setEage(res.getInt(4));
				em.setEedu(res.getString(5));
				
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(6));
				zw.setZname(res.getString(7));
				zw.setZremark(res.getString(8));
				
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(9));
				po.setPname(res.getString(10));
				po.setPremark(res.getString(11));
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(12));
				de.setDname(res.getString(13));
				de.setDremark(res.getString(14));
				//��λ���ò�����Ϣ
				po.setDeptinfo(de);
				//ְλ���ø�λ��Ϣ
				zw.setPostinfo(po);
				//Ա������ְλ��Ϣ
				em.setZwinfo(zw);
				//��Ա�������ڼ�����
				list.add(em);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbc.close(pre, res, conn);
		}
		return list;
	}
	
	//���Ա��
	@Override
	public int add(Emplo em) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into emplo values('"+em.getEname()+"','"+em.getEsex()+"','"+em.getEage()+"','"+em.getEedu()+"','"+em.getZwinfo().getZid()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	
	//ɾ��Ա��
	@Override
	public int deleteEm(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from emplo where eid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			/*try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}*/
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	
	@Override
	public int updateEm(Emplo em,int index) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql=null;
			if(index==1){
				sql="update emplo set ename='"+em.getEname()+"' ,esex='"+em.getEsex()+"',eage='"+em.getEage()+"' ,eedu='"+em.getEedu()+"'  where eid='"+em.getEid()+"'";
			}else if(index==2){
				 sql="update emplo set zid='"+em.getZwinfo().getZid()+"' where eid='"+em.getEid()+"'";
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
	
	//��ȡĳ��Ա����Ϣ
	@Override
	public Emplo getOne(int id) {
		Emplo em=new Emplo();
		try {
			conn=jdbc.getConnection();
			String sql="select e.*,z.zname,z.zremark,  p.pname,p.premark ,d.dname,d.dremark "
					+ "from emplo e left join zwinfo z "
					+ "on e.zid=z.zid left join postinfo p "
					+ " on z.pid=p.pid left join deptinfo d "
					+ "on p.did=d.did where eid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//Ա����Ϣ��װ
				em.setEid(res.getInt(1));
				em.setEname(res.getString(2));
				em.setEsex(res.getString(3));
				em.setEage(res.getInt(4));
				em.setEedu(res.getString(5));
				//ְλ��Ϣ
				Zwinfo zw=new Zwinfo();
				zw.setZname(res.getString(6));
				zw.setZremark(res.getString(7));
				
				//��λ
				Postinfo po=new Postinfo();
				po.setPname(res.getString(8));
				po.setPremark(res.getString(9));
				//����
				Deptinfo de=new Deptinfo();
				de.setDname(res.getString(10));
				de.setDremark(res.getString(11));
				
				//��λ���ò�����Ϣ
				po.setDeptinfo(de);
				//ְλ���ø�λ��Ϣ
				zw.setPostinfo(po);
				//Ա������ְλ��Ϣ
				em.setZwinfo(zw);
				//��Ա�������ڼ�����
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbc.close(pre, res, conn);
		}
		return em;
	}

}
