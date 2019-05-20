package com.wyr.pojo;

import java.io.Serializable;

public class Emplo implements Serializable {
	private int eid;
	private String ename;
	private String esex;
	private int eage;
	private String eedu;
	private  Zwinfo zwinfo;//引用职位
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEsex() {
		return esex;
	}
	public void setEsex(String esex) {
		this.esex = esex;
	}
	public int getEage() {
		return eage;
	}
	public void setEage(int eage) {
		this.eage = eage;
	}
	public String getEedu() {
		return eedu;
	}
	public void setEedu(String eedu) {
		this.eedu = eedu;
	}
	public Zwinfo getZwinfo() {
		return zwinfo;
	}
	public void setZwinfo(Zwinfo zwinfo) {
		this.zwinfo = zwinfo;
	}
}
