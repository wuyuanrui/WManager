package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.ISubjectsDao;
import com.wyr.pojo.Subjects;
import com.wyr.until.JDBC;

public class SubjectsDao implements ISubjectsDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	//科目查看
	@Override
	public List<Subjects> listSu() {
		List<Subjects> list=new ArrayList<Subjects>();
		try {
			conn=jdbc.getConnection();
			String sql="select * from subjects";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				Subjects su=new Subjects();
				su.setSid(res.getInt(1));
				su.setSname(res.getString(2));
				su.setSremark(res.getString(3));
				list.add(su);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

	//添加
	@Override
	public int add(Subjects su) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into subjects values('"+su.getSname()+"','"+su.getSremark()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	//删除操作
	@Override
	public int delete(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from subjects";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	
	//修改操作
	@Override
	public int update(Subjects su) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="update subjects set sname='"+su.getSname()+"',sremark='"+su.getSremark()+"' where sid='"+su.getSid()+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	
	//获取
	@Override
	public Subjects getOne(int id) {
		Subjects su=new Subjects();
		try {
			conn=jdbc.getConnection();
			String sql="select * from subjects where sid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				su.setSid(res.getInt(1));
				su.setSname(res.getString(2));
				su.setSremark(res.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return su;
	}

}
