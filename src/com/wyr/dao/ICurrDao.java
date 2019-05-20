package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Curr;

public interface ICurrDao {
	public List<Curr> listCu();
	
	public int addCu(Curr cu);
	
	public int updateCu(Curr cu,int index);
	
	public int deleteCu(int id);
	
	public Curr getOne(int id);
	//模糊查询
	public List<Curr> listCu(int index,String num);
	//课时量查询
	public List<Curr> listCu(int index,int index2);
	//分组查询科目上课人数
	public List<Curr> listCu(String name);
	
}
