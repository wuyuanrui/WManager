package com.wyr.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBC {
	private static Connection conn=null;
	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url="jdbc:sqlserver://localhost:1433;databaseName=manager";
	private static String user="sa";
	private static String pwd="123456";
	private static PreparedStatement pre=null;
	
	public static Connection getConnection(){
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public static void close(PreparedStatement pre,ResultSet res, Connection conn){
		try {
			if(res!=null){
				res.close();
			}
			if(pre!=null){
				pre.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int Update(String sql){
		int i=0;
		try {
			conn=getConnection();
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	
	
}
