package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.IDeptinfo;
import com.wyr.pojo.Deptinfo;
import com.wyr.until.JDBC;

public class DeptinfoDao implements IDeptinfo {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	
	//����ѯ
	@Override
	public List<Deptinfo> listDep() {
		List<Deptinfo> list=new ArrayList<Deptinfo>();
		try {
			conn=jdbc.getConnection();
			String sql="select * from deptinfo";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			System.out.println("��ǰ�α�λ�ã�"+res.getRow());
			
			while(res.next()){
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(1));
				de.setDname(res.getString(2));
				de.setDremark(res.getString(3));
				list.add(de);
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
	public int updateDep(Deptinfo dep) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="update deptinfo set dname='"+dep.getDname()+"',dremark='"+dep.getDremark()+"'  where did='"+dep.getDid()+"' ";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			//����ع�
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

	//ɾ��
	@Override
	public int deleteDep(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from deptinfo where did='"+id+"'";
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

	//���
	@Override
	public int addDep(Deptinfo dep) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="insert into deptinfo values('"+dep.getDname()+"','"+dep.getDremark()+"')";
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
	//��ȡ����
	@Override
	public Deptinfo getOne(int id) {
		Deptinfo de=new Deptinfo();
		try {
			conn=jdbc.getConnection();
			String sql="select * from deptinfo where did='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				de.setDid(res.getInt(1));
				de.setDname(res.getString(2));
				de.setDremark(res.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return de;
	}

}
