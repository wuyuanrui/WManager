package com.wyr.pojo;

import java.io.Serializable;

public class Admininfo implements Serializable {
	private int aid;
	private String aname;
	private String apwd;
	private String aremark;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public String getAremark() {
		return aremark;
	}
	public void setAremark(String aremark) {
		this.aremark = aremark;
	}
}
