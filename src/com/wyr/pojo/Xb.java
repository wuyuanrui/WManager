package com.wyr.pojo;

import java.io.Serializable;

public class Xb implements Serializable {
	private int xid;
	private String xname;
	private String xremark;
	public int getXid() {
		return xid;
	}
	public void setXid(int xid) {
		this.xid = xid;
	}
	public String getXname() {
		return xname;
	}
	public void setXname(String xname) {
		this.xname = xname;
	}
	public String getXremark() {
		return xremark;
	}
	public void setXremark(String xremark) {
		this.xremark = xremark;
	}
	
}
