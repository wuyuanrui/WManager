package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Emplo;
import com.wyr.pojo.Zwinfo;

public interface IZwinfoDao {
	//ְλ�鿴
	public List<Zwinfo> listZw();
	//ְλɾ��
	public int deleteZw(int id);
	//ְλ�޸�
	public int updateZw(Zwinfo zw,int index);
	//ְλ���
	public int addZw(Zwinfo zw);
	//��ȡ��ǰְλ
	public Zwinfo getOne(int id);
}
