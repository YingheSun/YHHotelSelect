package com.example.model;

import java.util.List;

public class Default_Store {
	private String ret;
	private String msg;
	public List<Datas> data;

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

	public List<Datas> getData() {
		return data;
	}

	public void setData(List<Datas> data) {
		this.data = data;
	}

	public class Datas {
		private String id;
		private String stroe;
		private String numbers;
		private String kindsnum;
		private String owner;
		private String shopid;
		private String state;
		private String extra2;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getStroe() {
			return stroe;
		}

		public void setStroe(String stroe) {
			this.stroe = stroe;
		}

		public String getNumbers() {
			return numbers;
		}

		public void setNumbers(String numbers) {
			this.numbers = numbers;
		}

		public String getKindsnum() {
			return kindsnum;
		}

		public void setKindsnum(String kindsnum) {
			this.kindsnum = kindsnum;
		}

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getShopid() {
			return shopid;
		}

		public void setShopid(String shopid) {
			this.shopid = shopid;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getExtra2() {
			return extra2;
		}

		public void setExtra2(String extra2) {
			this.extra2 = extra2;
		}
	}

}
