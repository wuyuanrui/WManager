package com.wyr.pojo;

import java.io.Serializable;

public class Zwinfo implements Serializable {
	private int zid;
	private String zname;
	private String zremark;
	private Postinfo postinfo;//引用岗位表
	public int getZid() {
		return zid;
	}
	public void setZid(int zid) {
		this.zid = zid;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}
	public String getZremark() {
		return zremark;
	}
	public void setZremark(String zremark) {
		this.zremark = zremark;
	}
	public Postinfo getPostinfo() {
		return postinfo;
	}
	public void setPostinfo(Postinfo postinfo) {
		this.postinfo = postinfo;
	}
	
}
