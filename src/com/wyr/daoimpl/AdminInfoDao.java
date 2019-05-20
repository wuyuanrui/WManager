package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.pept.transport.EventHandler;
import com.wyr.dao.IAdminInfoDao;
import com.wyr.pojo.Admininfo;
import com.wyr.until.JDBC;

public class AdminInfoDao implements IAdminInfoDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	//实例化类
	public AdminInfoDao(){
		jdbc=new JDBC();
	}
	//查询
	@Override
	public List<Admininfo> listAd() {
		List<Admininfo> list=new ArrayList<Admininfo>();
		try {
			conn=jdbc.getConnection();
			String sql="select * from admininfo";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			
			//结果集
			while(res.next()){
				Admininfo ad=new Admininfo();
				ad.setAid(res.getInt(1));
				ad.setAname(res.getString(2));
				ad.setApwd(res.getString(3));
				ad.setAremark(res.getString(4));
				list.add(ad);
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
	public int addAd(Admininfo ad) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into admininfo values('"+ad.getAname()+"','"+ad.getApwd()+"','"+ad.getAremark()+"')";
			pre=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			 i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}
	//获取单个
	@Override
	public Admininfo getOne(int id) {
		Admininfo ad=new Admininfo();
		try {
			conn=jdbc.getConnection();
			String sql="select * from admininfo where aid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				ad.setAid(res.getInt(1));
				ad.setAname(res.getString(2));
				ad.setApwd(res.getString(3));
				ad.setAremark(res.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return ad;
	}

	//修改
	@Override
	public int updateAd(Admininfo ad,int index) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="update admininfo set aname='"+ad.getAname()+"' where aid='"+ad.getAid()+"'";
			String sql2="update admininfo set apsw='"+ad.getApwd()+"' where aid='"+ad.getAid()+"'";
			String sql3="update admininfo set aremark='"+ad.getAremark()+"' where aid='"+ad.getAid()+"'";
			String sql4="update admininfo set aname='"+ad.getAname()+"',apsw='"+ad.getApwd()+"',aremark='"+ad.getAremark()+"' where aid='"+ad.getAid()+"'";
			if(index==1){
				pre=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			}else if(index==2){
				pre=conn.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
			}else if(index==3){
				pre=conn.prepareStatement(sql3,Statement.RETURN_GENERATED_KEYS);
			}else if(index==4){
				pre=conn.prepareStatement(sql4,Statement.RETURN_GENERATED_KEYS);
			}
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
	public int deleteAd(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from admininfo where aid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
				
		return i;
	}

}
