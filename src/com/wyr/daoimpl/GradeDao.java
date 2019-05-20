package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.IGradeDao;
import com.wyr.pojo.Grade;
import com.wyr.pojo.Xb;
import com.wyr.until.JDBC;

public class GradeDao implements IGradeDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	public GradeDao(){
		jdbc=new JDBC();
	}
	@Override
	public List<Grade> listGr() {
		List<Grade> list=new ArrayList<Grade>();
		try {
			conn=jdbc.getConnection();
			String sql="select g.*,x.xname,x.xremark from grade g left join xb x on g.xid=x.xid";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				Grade gr=new Grade();
				gr.setGid(res.getInt(1));
				gr.setGname(res.getString(2));
				gr.setGremark(res.getString(3));
				Xb x=new Xb();
				x.setXid(res.getInt(4));
				x.setXname(res.getString(5));
				x.setXremark(res.getString(6));
				//年级封装系别信息
				gr.setXb(x);
				list.add(gr);
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
	public int add(Grade gr) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into grade values('"+gr.getGname()+"','"+gr.getGremark()+"','"+gr.getXb().getXid()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	//删除
	@Override
	public int delete(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from grade where gid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	//修改
	@Override
	public int update(Grade gr,int index) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql=null;
			if(index==1){
				sql="update grade set gname='"+gr.getGname()+"',  gremark='"+gr.getGremark()+"' where gid='"+gr.getGid()+"'";
			}else if(index==2){
				sql="update grade set xid='"+gr.getXb().getXid()+"' where gid='"+gr.getGid()+"'";
			}
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.getConnection();
		}
		return i;
	}

	@Override
	public Grade getOne(int id) {
		Grade gr=new Grade();
		try {
			conn=jdbc.getConnection();
			String sql="select g.*,x.xname,x.xremark from grade g left join xb x on g.xid=x.xid where gid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				gr.setGid(res.getInt(1));
				gr.setGname(res.getString(2));
				gr.setGremark(res.getString(3));
				Xb x=new Xb();
				x.setXid(res.getInt(4));
				x.setXname(res.getString(5));
				x.setXremark(res.getString(6));
				//封装系别信息
				gr.setXb(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return gr;
	}

}
