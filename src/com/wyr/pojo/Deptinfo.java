package com.wyr.pojo;

import java.io.Serializable;

public class Deptinfo implements Serializable {
	private int did;
	private String dname;
	private String dremark;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDremark() {
		return dremark;
	}
	public void setDremark(String dremark) {
		this.dremark = dremark;
	}
}
