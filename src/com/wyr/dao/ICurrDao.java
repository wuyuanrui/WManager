package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Curr;

public interface ICurrDao {
	public List<Curr> listCu();
	
	public int addCu(Curr cu);
	
	public int updateCu(Curr cu,int index);
	
	public int deleteCu(int id);
	
	public Curr getOne(int id);
	//ģ����ѯ
	public List<Curr> listCu(int index,String num);
	//��ʱ����ѯ
	public List<Curr> listCu(int index,int index2);
	//�����ѯ��Ŀ�Ͽ�����
	public List<Curr> listCu(String name);
	
}
