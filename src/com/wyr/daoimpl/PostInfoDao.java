package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.IPostinfo;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Postinfo;
import com.wyr.until.JDBC;

public class PostInfoDao implements IPostinfo {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	//查询
	@Override
	public List<Postinfo> listPo() {
		List<Postinfo> list=new ArrayList<Postinfo>();
		try {
			conn=jdbc.getConnection();
			String sql="select d.did,d.dname,d.dremark,p.pid,p.pname,p.premark from  deptinfo d left join postinfo p on d.did=p.did";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				Deptinfo de=new Deptinfo();
				Postinfo po=new Postinfo();
				//部门信息
				de.setDid(res.getInt(1));
				de.setDname(res.getString(2));
				de.setDremark(res.getString(3));
				//岗位信息
				po.setPid(res.getInt(4));
				po.setPname(res.getString(5));
				po.setPremark(res.getString(6));
				//将查询到的信息封装在岗位表中再引用它
				po.setDeptinfo(de);
				list.add(po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}
	
	//删除
	@Override
	public int deletePo(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from postinfo where pid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();	
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

	
	//添加
	@Override
	public int addPo(Postinfo po) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into postinfo values('"+po.getPname()+"','"+po.getPremark()+"','"+po.getDeptinfo().getDid()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	
	@Override
	public int updatePo(Postinfo po, int index) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			if(index==1){
				String sql1="update postinfo set pname='"+po.getPname()+"' where pid='"+po.getPid()+"'";
				pre=conn.prepareStatement(sql1);
			}else if(index==2){
				String sql2="update postinfo set did='"+po.getDeptinfo().getDid()+"' where pid='"+po.getPid()+"'";
				pre=conn.prepareStatement(sql2);
			}
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	//获取单个岗位信息
	@Override
	public Postinfo getOne(int id) {
		Postinfo po=new Postinfo();
		try {
			conn=jdbc.getConnection();
			String sql="select d.did,d.dname,d.dremark,p.pid,p.pname,p.premark from  deptinfo d left join postinfo p on d.did=p.did where pid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
				while(res.next()){
					Deptinfo de=new Deptinfo();
					//部门信息
					de.setDid(res.getInt(1));
					de.setDname(res.getString(2));
					de.setDremark(res.getString(3));
					//岗位信息
					po.setPid(res.getInt(4));
					po.setPname(res.getString(5));
					po.setPremark(res.getString(6));
					//将查询到的信息封装在岗位表中再引用它
					po.setDeptinfo(de);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return po;
	}
	
}
