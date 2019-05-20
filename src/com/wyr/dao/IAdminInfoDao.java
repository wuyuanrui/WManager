package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Admininfo;

public interface IAdminInfoDao {
	//查询
	public List<Admininfo> listAd();
	//添加
	public int addAd(Admininfo ad);
	//获取单个
	public Admininfo getOne(int id);
	//修改
	public int updateAd(Admininfo ad,int index);
	//删除
	public int deleteAd(int id);
}
