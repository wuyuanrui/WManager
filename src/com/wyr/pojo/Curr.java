package com.wyr.pojo;

import java.io.Serializable;

public class Curr implements Serializable {
	private int rid;
	private int rcount;
	private Emplo emplo;//����Ա��
	private Classes classes;//���ð༶
	private Subjects subjects;//���ÿ�Ŀ
	private int count;
	public int getRid() {
		return rid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public Emplo getEmplo() {
		return emplo;
	}
	public void setEmplo(Emplo emplo) {
		this.emplo = emplo;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public Subjects getSubjects() {
		return subjects;
	}
	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}
}
