package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wyr.dao.ILoginDao;
import com.wyr.pojo.Admininfo;
import com.wyr.until.JDBC;

public class LoginDao implements ILoginDao{
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	public LoginDao(){
		jdbc=new JDBC();
	}
	
	//ÓÃ»§µÇÂ¼
	public  Admininfo login(String name,String pwd){
		Admininfo an=new Admininfo();
		try {
			conn=jdbc.getConnection();
			String sql="select aname,apsw from admininfo where aname='"+name+"' and apsw='"+pwd+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				an.setAname(res.getString(1));
				an.setApwd(res.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			jdbc.close(pre, res, conn);
		}
		return an;
	}
	
}
