package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Xb;

public interface IXbDao {
	public List<Xb> listXb();
	public int add(Xb xb);
	public int delete(int id);
	public int update(Xb xb);
	public Xb getOne(int id);
}
