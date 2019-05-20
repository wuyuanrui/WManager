package com.wyr.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wyr.dao.ICurrDao;
import com.wyr.pojo.Classes;
import com.wyr.pojo.Curr;
import com.wyr.pojo.Deptinfo;
import com.wyr.pojo.Emplo;
import com.wyr.pojo.Grade;
import com.wyr.pojo.Postinfo;
import com.wyr.pojo.Subjects;
import com.wyr.pojo.Xb;
import com.wyr.pojo.Zwinfo;
import com.wyr.until.JDBC;

public class CurrDao implements ICurrDao {
	private Connection conn=null;
	private PreparedStatement pre=null;
	private ResultSet res=null;
	private JDBC jdbc=null;
	
	public CurrDao(){
		jdbc=new JDBC();
	}
	
	//查询课程安排-员工-班级-科目
	/*--System.out.println("课程ID\t课时量\t教师\t所属职位\t所属岗位\t所属部门\t所属年级\t指导班级\t课程名");*/
	@Override
	public List<Curr> listCu() {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
			
			
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid";
			
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//课程
				Curr cu=new Curr();
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				//教师
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				//职位
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(8));
				zw.setZname(res.getString(9));
				//岗位
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(10));
				po.setPname(res.getString(11));
				//部门
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(12));
				de.setDname(res.getString(13));
				Xb x=new Xb();
				x.setXid(res.getInt(14));
				x.setXname(res.getString(15));
				//年级
				Grade gr=new Grade();
				gr.setGid(res.getInt(16));
				gr.setGname(res.getString(17));
				//班级
				Classes cla=new Classes();
				cla.setCid(res.getInt(18));
				cla.setCname(res.getString(19));
				
				//科目
				Subjects s=new Subjects();
				s.setSid(res.getInt(20));
				s.setSname(res.getString(21));
				
				//封装信息
				//部门
				po.setDeptinfo(de);
				//岗位
				zw.setPostinfo(po);
				//职位
				em.setZwinfo(zw);
	
				//系别
				gr.setXb(x);
				//年级
				cla.setGrade(gr);
				
				//班级
				cu.setClasses(cla);
				//教师
				cu.setEmplo(em);
				//科目
				cu.setSubjects(s);
				
				
				list.add(cu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

	//添加--员-班级-科目
	@Override
	public int addCu(Curr cu) {
		int i=0;
		try {

			conn=jdbc.getConnection();
			conn.setAutoCommit(false);
			String sql="insert into curr values('"+cu.getRcount()+"','"+cu.getEmplo().getEid()+"','"+cu.getClasses().getCid()+"','"+cu.getSubjects().getSid()+"')";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
			conn.commit();
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

	@Override
	public int updateCu(Curr cu,int index) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql=null;
			if(index==1){
				//修改课时
				 sql="update curr set rcount='"+cu.getRcount()+"' where rid='"+cu.getRid()+"'";
			}else if(index==2){
				//修改班级
				sql="update curr set cid='"+cu.getClasses().getCid()+"' where rid='"+cu.getRid()+"'";
			}else if(index==3){
				//修改科目
				 sql="update curr set sid='"+cu.getSubjects().getSid()+"' where rid='"+cu.getRid()+"'";
			}else if(index==4){
				//更改老师
				sql="update curr set eid='"+cu.getEmplo().getEid()+"' where rid='"+cu.getRid()+"'";
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
	
	//删除
	@Override
	public int deleteCu(int id) {
		int i=0;
		try {
			conn=jdbc.getConnection();
			String sql="delete from curr where rid='"+id+"'";
			pre=conn.prepareStatement(sql);
			i=pre.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return i;
	}

	
	
	//查询一个
	@Override
	public Curr getOne(int id) {
		Curr cu=new Curr();
		try {
			conn=jdbc.getConnection();
			String sql="select c.rid,c.rcount,e.eid,e.ename,e.esex,e.eage,e.eedu,s.sid,s.sname,s.sremark from curr c left join emplo e "
					+ "on c.eid=e.eid left join classes cl "
					+ "on cl.cid=c.cid left join subjects s "
					+ "on c.sid=s.sid where rid='"+id+"'";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				Subjects s=new Subjects();
				s.setSid(res.getInt(8));
				s.setSname(res.getString(9));
				s.setSremark(res.getString(10));
				//封装信息
				cu.setEmplo(em);
				cu.setSubjects(s);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return cu;
	}

	//模糊查询
	@Override
	public List<Curr> listCu(int index, String num) {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
			if(index==1){
				//模糊查询根据教师名字
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where e.ename like '%"+num+"%'";
			}else if(index==2){
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where x.xname like '%"+num+"%'";
			}else if(index==3){
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where g.gname like '%"+num+"%'";
			}else if(index==4){
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where s.sname like '%"+num+"%'";
			}
		pre=conn.prepareStatement(sql);
		res=pre.executeQuery();
		while(res.next()){
			//课程
			Curr cu=new Curr();
			cu.setRid(res.getInt(1));
			cu.setRcount(res.getInt(2));
			//教师
			Emplo em=new Emplo();
			em.setEid(res.getInt(3));
			em.setEname(res.getString(4));
			em.setEsex(res.getString(5));
			em.setEage(res.getInt(6));
			em.setEedu(res.getString(7));
			//职位
			Zwinfo zw=new Zwinfo();
			zw.setZid(res.getInt(8));
			zw.setZname(res.getString(9));
			//岗位
			Postinfo po=new Postinfo();
			po.setPid(res.getInt(10));
			po.setPname(res.getString(11));
			//部门
			Deptinfo de=new Deptinfo();
			de.setDid(res.getInt(12));
			de.setDname(res.getString(13));
			Xb x=new Xb();
			x.setXid(res.getInt(14));
			x.setXname(res.getString(15));
			//年级
			Grade gr=new Grade();
			gr.setGid(res.getInt(16));
			gr.setGname(res.getString(17));
			//班级
			Classes cla=new Classes();
			cla.setCid(res.getInt(18));
			cla.setCname(res.getString(19));
			
			//科目
			Subjects s=new Subjects();
			s.setSid(res.getInt(20));
			s.setSname(res.getString(21));
			
			//封装信息
			//部门
			po.setDeptinfo(de);
			//岗位
			zw.setPostinfo(po);
			//职位
			em.setZwinfo(zw);

			//系别
			gr.setXb(x);
			//年级
			cla.setGrade(gr);
			
			//班级
			cu.setClasses(cla);
			//教师
			cu.setEmplo(em);
			//科目
			cu.setSubjects(s);
			
			
			list.add(cu);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		jdbc.close(pre, res, conn);
	}
	return list;
	}

	@Override
	public List<Curr> listCu(int index,int index2) {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid where rcount between '"+index+"' and '"+index2+"'  ";
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//课程
				Curr cu=new Curr();
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				//教师
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				//职位
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(8));
				zw.setZname(res.getString(9));
				//岗位
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(10));
				po.setPname(res.getString(11));
				//部门
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(12));
				de.setDname(res.getString(13));
				Xb x=new Xb();
				x.setXid(res.getInt(14));
				x.setXname(res.getString(15));
				//年级
				Grade gr=new Grade();
				gr.setGid(res.getInt(16));
				gr.setGname(res.getString(17));
				//班级
				Classes cla=new Classes();
				cla.setCid(res.getInt(18));
				cla.setCname(res.getString(19));
				
				//科目
				Subjects s=new Subjects();
				s.setSid(res.getInt(20));
				s.setSname(res.getString(21));
				
				//封装信息
				//部门
				po.setDeptinfo(de);
				//岗位
				zw.setPostinfo(po);
				//职位
				em.setZwinfo(zw);
	
				//系别
				gr.setXb(x);
				//年级
				cla.setGrade(gr);
				
				//班级
				cu.setClasses(cla);
				//教师
				cu.setEmplo(em);
				//科目
				cu.setSubjects(s);
				
				
				list.add(cu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

	@Override
	public List<Curr> listCu(String name) {
		List<Curr> list=new ArrayList<Curr>();
		try {
			conn=jdbc.getConnection();
			String sql=null;
			
			
				sql="select c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname ,count(*)"
						+ "from curr c left join emplo e "
						+ "on c.eid=e.eid left join classes cl "
						+ "on cl.cid=c.cid left join subjects s "
						+ "on c.sid=s.sid left join zwinfo z "
						+ "on z.zid=e.zid left join postinfo p "
						+ "on z.pid=p.pid left join deptinfo d "
						+ "on p.did=d.did left join grade g "
						+ "on g.gid=cl.gid left join xb x "
						+ "on x.xid=g.xid  "
						+ " group by c.rid,c.rcount   ,e.eid,e.ename,e.esex,e.eage,e.eedu,  z.zid,z.zname,  p.pid,p.pname,  d.did,d.dname,   x.xid,x.xname,   g.gid,g.gname,  cl.cid,cl.cname,  s.sid,s.sname "
						+ "having s.sname='"+name+"'";
			
			pre=conn.prepareStatement(sql);
			res=pre.executeQuery();
			while(res.next()){
				//课程
				Curr cu=new Curr();
				
				cu.setRid(res.getInt(1));
				cu.setRcount(res.getInt(2));
				//教师
				Emplo em=new Emplo();
				em.setEid(res.getInt(3));
				em.setEname(res.getString(4));
				em.setEsex(res.getString(5));
				em.setEage(res.getInt(6));
				em.setEedu(res.getString(7));
				//职位
				Zwinfo zw=new Zwinfo();
				zw.setZid(res.getInt(8));
				zw.setZname(res.getString(9));
				//岗位
				Postinfo po=new Postinfo();
				po.setPid(res.getInt(10));
				po.setPname(res.getString(11));
				//部门
				Deptinfo de=new Deptinfo();
				de.setDid(res.getInt(12));
				de.setDname(res.getString(13));
				Xb x=new Xb();
				x.setXid(res.getInt(14));
				x.setXname(res.getString(15));
				//年级
				Grade gr=new Grade();
				gr.setGid(res.getInt(16));
				gr.setGname(res.getString(17));
				//班级
				Classes cla=new Classes();
				cla.setCid(res.getInt(18));
				cla.setCname(res.getString(19));
				
				//科目
				Subjects s=new Subjects();
				s.setSid(res.getInt(20));
				s.setSname(res.getString(21));
				
				//封装信息
				//部门
				po.setDeptinfo(de);
				//岗位
				zw.setPostinfo(po);
				//职位
				em.setZwinfo(zw);
	
				//系别
				gr.setXb(x);
				//年级
				cla.setGrade(gr);
				
				//班级
				cu.setClasses(cla);
				//教师
				cu.setEmplo(em);
				//科目
				cu.setSubjects(s);
				//课时数量
				cu.setCount(res.getInt(22));
				
				list.add(cu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbc.close(pre, res, conn);
		}
		return list;
	}

}
