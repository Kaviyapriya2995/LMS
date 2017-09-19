package com.library.beans;

public class Login {
	protected String uname=null;
	protected String pwd = null;
	public Login(String uname,String pwd)
	{
		this.uname=uname;
		this.pwd=pwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	
	
}
