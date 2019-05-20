package com.wyr.pojo;

import java.io.Serializable;

public class Postinfo implements Serializable {
	private int pid;
	private String pname;
	private String premark;
	
	private Deptinfo deptinfo;//引用部门
	public Deptinfo getDeptinfo() {
		return deptinfo;
	}
	public void setDeptinfo(Deptinfo deptinfo) {
		this.deptinfo = deptinfo;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPremark() {
		return premark;
	}
	public void setPremark(String premark) {
		this.premark = premark;
	}
}
