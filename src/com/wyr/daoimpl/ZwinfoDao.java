package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.IZwinfoDao;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Postinfo;
import com.wyr.pojo.Zwinfo;
import com.wyr.until.JDBC;

public class ZwinfoDao  implements IZwinfoDao{
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	//职位查询-岗位--部门
	@Override
	public List<Zwinfo> listZw() {
		List<Zwinfo> list=new ArrayList<Zwinfo>();
		try {
			conn=jdbc.getConnection();
			String sql="select z.zid,z.zname,z.zremark,p.pid,p.pname,p.premark,d.did,d.dname,d.dremark "
					+ "from zwinfo z left join postinfo p "
					+ "on z.pid=p.pid left join "
					+ "deptinfo d on p.did=d.did";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(1));
				zw.setZname(res.getString(2));
				zw.setZremark(res.getString(3));
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(4));
				po.setPname(res.getString(5));
				po.setPremark(res.getString(6));
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(7));
				de.setDname(res.getString(8));
				de.setDremark(res.getString(9));
				po.setDeptinfo(de);
				zw.setPostinfo(po);
				list.add(zw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}
	
	//删除职位
	@Override
	public int deleteZw(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from zwinfo where zid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	//修改职位-判断性的修改信息
	@Override
	public int updateZw(Zwinfo zw,int index) {
		
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql=null;
			if(index==1){
				 sql="update zwinfo  set zname='"+zw.getZname()+"',zremark='"+zw.getZremark()+"'  where zid='"+zw.getZid()+"'";
			}else if(index==2){
				 sql="update zwinfo  set pid='"+zw.getPostinfo().getPid()+"' where zid='"+zw.getZid()+"'";
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
	
	
	//添加职位-岗位信息
	@Override
	public int addZw(Zwinfo zw) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into zwinfo  values('"+zw.getZname()+"','"+zw.getZremark()+"',"+zw.getPostinfo().getPid()+")";
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
	
	//获取当前职位信息
	@Override
	public Zwinfo getOne(int id) {
		Zwinfo zw=new Zwinfo();
		try {
			conn=jdbc.getConnection();
			String sql="select z.zid,z.zname,z.zremark,p.pid,p.pname,p.premark,d.did,d.dname,d.dremark "
					+ "from zwinfo z left join postinfo p "
					+ "on z.pid=p.pid left join "
					+ "deptinfo d on p.did=d.did where zid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//职位信息
				zw.setZid(res.getInt(1));
				zw.setZname(res.getString(2));
				zw.setZremark(res.getString(3));
				//岗位信息
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(4));
				po.setPname(res.getString(5));
				po.setPremark(res.getString(6));
				//部门信息
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(7));
				de.setDname(res.getString(8));
				de.setDremark(res.getString(9));
				//添加部门信息
				po.setDeptinfo(de);
				//添加岗位信息
				zw.setPostinfo(po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return zw;
	}

}
