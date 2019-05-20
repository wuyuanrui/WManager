package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.IXbDao;
import com.wyr.pojo.Subjects;
import com.wyr.pojo.Xb;
import com.wyr.until.JDBC;

public class XbDao implements IXbDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	public XbDao(){
		jdbc=new JDBC();
	}
	
	@Override
	public List<Xb> listXb() {
		List<Xb> list=new ArrayList<Xb>();
		try {
			conn=jdbc.getConnection();
			String sql="select * from xb";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				Xb x=new Xb();
				x.setXid(res.getInt(1));
				x.setXname(res.getString(2));
				x.setXremark(res.getString(3));
				list.add(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

	@Override
	public int add(Xb xb) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into xb values('"+xb.getXname()+"','"+xb.getXremark()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	//É¾³ý
	@Override
	public int delete(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from xb where xid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	//ÐÞ¸Ä
	@Override
	public int update(Xb xb) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="update xb set xname='"+xb.getXname()+"' ,xremark='"+xb.getXremark()+"' where xid='"+xb.getXid()+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	//»ñÈ¡
	@Override
	public Xb getOne(int id) {
		Xb x=new Xb();
		try {
			conn=jdbc.getConnection();
			String sql="select * from xb where xid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				x.setXid(res.getInt(1));
				x.setXname(res.getString(2));
				x.setXremark(res.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return x;
	}

}
