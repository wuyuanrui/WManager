package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Classes;

public interface IClassesDao {
	
	public List<Classes> listCla();
	
	public int update(Classes cla);
	
	public int addCla(Classes cla);
	
	public int delete(int id);
	
	public Classes getOne(int id);
}
