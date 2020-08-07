package com.board.model;

public class CommentDTO {
	private int cnum;
	private String userid;
	private String msg;
	private String reg_date;
	private int bnum;
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMsg() {
		return msg == null ? "" : msg.trim();
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReg_date() {
		return reg_date == null ? "" : reg_date.trim();
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
}