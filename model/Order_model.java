package com.example.model;

import java.util.List;


public class Order_model {
	private String ret;
	private String msg;
	public List<Order> data;

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

	public List<Order> getData() {
		return data;
	}

	public void setData(List<Order> data) {
		this.data = data;
	}

	public class Order {
		private String id;
		private String ordertype;
		private String seralordernum;
		private String shopid;
		private String storeid;
		private String responser;
		private String states;
		private String count;
		private String expense;
		private String moneyin;
		private String moneyout;
		private String storeinto;
		private String time;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getOrdertype() {
			return ordertype;
		}

		public void setOrdertype(String ordertype) {
			this.ordertype = ordertype;
		}

		public String getSeralordernum() {
			return seralordernum;
		}

		public void setSeralordernum(String seralordernum) {
			this.seralordernum = seralordernum;
		}

		public String getShopid() {
			return shopid;
		}

		public void setShopid(String shopid) {
			this.shopid = shopid;
		}

		public String getStoreid() {
			return storeid;
		}

		public void setStoreid(String storeid) {
			this.storeid = storeid;
		}

		public String getResponser() {
			return responser;
		}

		public void setResponser(String responser) {
			this.responser = responser;
		}

		public String getStates() {
			return states;
		}

		public void setStates(String states) {
			this.states = states;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getExpense() {
			return expense;
		}

		public void setExpense(String expense) {
			this.expense = expense;
		}

		public String getMoneyin() {
			return moneyin;
		}

		public void setMoneyin(String moneyin) {
			this.moneyin = moneyin;
		}

		public String getMoneyout() {
			return moneyout;
		}

		public void setMoneyout(String moneyout) {
			this.moneyout = moneyout;
		}

		public String getStoreinto() {
			return storeinto;
		}

		public void setStoreinto(String storeinto) {
			this.storeinto = storeinto;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}
}
