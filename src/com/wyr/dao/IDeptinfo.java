package com.wyr.dao;

import java.util.List;

import com.wyr.pojo.Deptinfo;

public interface IDeptinfo {
	//���Ų�ѯ
	public List<Deptinfo> listDep();
	//�����޸�
	public int updateDep(Deptinfo dep);
	//����ɾ��
	public int deleteDep(int id);
	//��������
	public int addDep(Deptinfo dep);
	//��ȡ��������
	public Deptinfo getOne(int id);
}
