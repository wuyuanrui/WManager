package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Postinfo;

public interface IPostinfo {
	//��λ��ѯ
	public List<Postinfo> listPo();
	//��λɾ��
	public int deletePo(int id);
	//��λ���
	public int addPo(Postinfo po);
	//��λ�޸�
	public int updatePo(Postinfo po,int index);
	//��ȡ������λ��Ϣ
	public Postinfo getOne(int id);
}
