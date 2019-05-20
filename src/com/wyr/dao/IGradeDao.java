package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Grade;
import com.wyr.pojo.Xb;

public interface IGradeDao {
	public List<Grade> listGr();
	public int add(Grade gr);
	public int delete(int id);
	public int update(Grade gr,int index);
	public Grade getOne(int id);
	
	
}
