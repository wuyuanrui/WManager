package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Subjects;

public interface ISubjectsDao {
	public List<Subjects> listSu();
	public int add(Subjects su);
	public int delete(int id);
	public int update(Subjects su);
	public Subjects getOne(int id);
}
