package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Postinfo;

public interface IPostinfo {
	//岗位查询
	public List<Postinfo> listPo();
	//岗位删除
	public int deletePo(int id);
	//岗位添加
	public int addPo(Postinfo po);
	//岗位修改
	public int updatePo(Postinfo po,int index);
	//获取单个岗位信息
	public Postinfo getOne(int id);
}
