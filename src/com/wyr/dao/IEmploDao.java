package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Emplo;

public interface IEmploDao {
	//员工信息查看
	public List<Emplo> lisEm();
	//员工信息添加
	public int add(Emplo em);
	//员工信息删除
	public int deleteEm(int id);
	//修改员工信息
	public int updateEm(Emplo em,int index);
	//获取员工
	public Emplo getOne(int id);
	
}
