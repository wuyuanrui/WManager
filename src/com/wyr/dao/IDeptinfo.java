package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Deptinfo;

public interface IDeptinfo {
	//部门查询
	public List<Deptinfo> listDep();
	//部门修改
	public int updateDep(Deptinfo dep);
	//部门删除
	public int deleteDep(int id);
	//部门增加
	public int addDep(Deptinfo dep);
	//获取单个部门
	public Deptinfo getOne(int id);
}
