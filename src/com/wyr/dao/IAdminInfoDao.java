package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Admininfo;

public interface IAdminInfoDao {
	//��ѯ
	public List<Admininfo> listAd();
	//���
	public int addAd(Admininfo ad);
	//��ȡ����
	public Admininfo getOne(int id);
	//�޸�
	public int updateAd(Admininfo ad,int index);
	//ɾ��
	public int deleteAd(int id);
}
