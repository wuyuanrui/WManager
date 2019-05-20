package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Emplo;
import com.wyr.pojo.Zwinfo;

public interface IZwinfoDao {
	//职位查看
	public List<Zwinfo> listZw();
	//职位删除
	public int deleteZw(int id);
	//职位修改
	public int updateZw(Zwinfo zw,int index);
	//职位添加
	public int addZw(Zwinfo zw);
	//获取当前职位
	public Zwinfo getOne(int id);
}
