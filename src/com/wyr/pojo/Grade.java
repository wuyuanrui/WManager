package com.wyr.pojo;

import java.io.Serializable;

public class Grade implements Serializable {
	private int gid;
	private String gname;
	private String gremark;
	private Xb xb;//引用系别
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGremark() {
		return gremark;
	}
	public void setGremark(String gremark) {
		this.gremark = gremark;
	}
	public Xb getXb() {
		return xb;
	}
	public void setXb(Xb xb) {
		this.xb = xb;
	}
	
}
