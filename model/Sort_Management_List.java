package com.example.model;

import java.util.List;

public class Sort_Management_List {
	private String ret;
	private String msg;
	public List<Sort> data;

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

	public List<Sort> getData() {
		return data;
	}

	public void setData(List<Sort> data) {
		this.data = data;
	}

	public class Sort {
		private String id;
		private String type;
		private String shopid;
		private String number;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getShopid() {
			return shopid;
		}

		public void setShopid(String shopid) {
			this.shopid = shopid;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

	}
}
