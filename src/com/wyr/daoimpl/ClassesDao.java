package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.IClassesDao;
import com.wyr.pojo.Classes;
import com.wyr.pojo.Grade;
import com.wyr.pojo.Xb;
import com.wyr.until.JDBC;

public class ClassesDao implements IClassesDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	public ClassesDao(){
		jdbc=new JDBC();
	}
	
	//����
	@Override
	public List<Classes> listCla() {
		List<Classes> list=new ArrayList<Classes>();
		try {
			conn=jdbc.getConnection();
			String sql="select c.*,g.gname,g.gremark,g.xid,x.xname,x.xremark from classes c left join"
					+ " grade g on c.gid=g.gid left join "
					+ "xb x on g.xid=x.xid";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				Classes c=new Classes();
				c.setCid(res.getInt(1));
				c.setCname(res.getString(2));
				c.setCremark(res.getString(3));
				Grade g=new Grade();
				g.setGid(res.getInt(4));
				g.setGname(res.getString(5));
				g.setGremark(res.getString(6));
				Xb x=new Xb();
				x.setXid(res.getInt(7));
				x.setXname(res.getString(8));
				x.setXremark(res.getString(9));
				//��Ϣ��װ
				g.setXb(x);
				c.setGrade(g);
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

	//�޸�
	@Override
	public int update(Classes cla) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="update classes set cname='"+cla.getCname()+"',cremark='"+cla.getCremark()+"' where cid='"+cla.getCid()+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	//���
	@Override
	public int addCla(Classes cla) {
		int i=0;
		try {
			
			conn=jdbc.getConnection();
			
			String sql="insert into classes values('"+cla.getCname()+"','"+cla.getCremark()+"','"+cla.getGrade().getGid()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	//ɾ��
	@Override
	public int delete(int id) {
		int i=0;
		try {			
			conn=jdbc.getConnection();
			String sql="delete from classes where cid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	//��ȡ
	@Override
	public Classes getOne(int id) {
		Classes cla=new Classes();
		try {
			conn=jdbc.getConnection();
			String sql="select c.*,g.gname,g.gremark,g.xid,x.xname,x.xremark from classes c left join"
					+ " grade g on c.gid=g.gid left join "
					+ "xb x on g.xid=x.xid where cid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				
				cla.setCid(res.getInt(1));
				cla.setCname(res.getString(2));
				cla.setCremark(res.getString(3));
				Grade g=new Grade();
				g.setGid(res.getInt(4));
				g.setGname(res.getString(5));
				g.setGremark(res.getString(6));
				Xb x=new Xb();
				x.setXid(res.getInt(7));
				x.setXname(res.getString(8));
				x.setXremark(res.getString(9));
				//��Ϣ��װ
				g.setXb(x);
				cla.setGrade(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return cla;
	}

}
