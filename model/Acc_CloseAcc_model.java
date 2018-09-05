package com.example.model;

import java.util.List;

public class Acc_CloseAcc_model {
	private String ret;
	private String msg;
	public List<AccCl> data;
	
	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<AccCl> getData() {
		return data;
	}

	public void setData(List<AccCl> data) {
		this.data = data;
	}

	public class AccCl {
		private String id;
		private String shopid;
		private String userid;
		private String account;
		private String time;
		private String date;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getShopid() {
			return shopid;
		}
		public void setShopid(String shopid) {
			this.shopid = shopid;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		
		
	}

}

