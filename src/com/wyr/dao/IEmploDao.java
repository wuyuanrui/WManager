package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Emplo;

public interface IEmploDao {
	//Ա����Ϣ�鿴
	public List<Emplo> lisEm();
	//Ա����Ϣ���
	public int add(Emplo em);
	//Ա����Ϣɾ��
	public int deleteEm(int id);
	//�޸�Ա����Ϣ
	public int updateEm(Emplo em,int index);
	//��ȡԱ��
	public Emplo getOne(int id);
	
}
