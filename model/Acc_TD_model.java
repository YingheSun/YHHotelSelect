package com.example.model;

import java.util.List;

public class Acc_TD_model {
	private String ret;
	private String msg;
	public List<Acc> data;

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

	public List<Acc> getData() {
		return data;
	}

	public void setData(List<Acc> data) {
		this.data = data;
	}

	public class Acc {
		private String shopName;
		private String userName;
		private String countAll;
		private String expAll;
		private String moneyInAll;
		private String moneyOutAll;
		private String orderNum;
		private String orderCNum;
		private String orderRNum;
		private String orderZNum;
		private String UDAllOrder;
		private String UDCAllOrder;
		private String UDRAllOrder;
		private String UDZAllORder;

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getCountAll() {
			return countAll;
		}

		public void setCountAll(String countAll) {
			this.countAll = countAll;
		}

		public String getExpAll() {
			return expAll;
		}

		public void setExpAll(String expAll) {
			this.expAll = expAll;
		}

		public String getMoneyInAll() {
			return moneyInAll;
		}

		public void setMoneyInAll(String moneyInAll) {
			this.moneyInAll = moneyInAll;
		}

		public String getMoneyOutAll() {
			return moneyOutAll;
		}

		public void setMoneyOutAll(String moneyOutAll) {
			this.moneyOutAll = moneyOutAll;
		}

		public String getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		}

		public String getOrderCNum() {
			return orderCNum;
		}

		public void setOrderCNum(String orderCNum) {
			this.orderCNum = orderCNum;
		}

		public String getOrderRNum() {
			return orderRNum;
		}

		public void setOrderRNum(String orderRNum) {
			this.orderRNum = orderRNum;
		}

		public String getOrderZNum() {
			return orderZNum;
		}

		public void setOrderZNum(String orderZNum) {
			this.orderZNum = orderZNum;
		}

		public String getUDAllOrder() {
			return UDAllOrder;
		}

		public void setUDAllOrder(String uDAllOrder) {
			UDAllOrder = uDAllOrder;
		}

		public String getUDCAllOrder() {
			return UDCAllOrder;
		}

		public void setUDCAllOrder(String uDCAllOrder) {
			UDCAllOrder = uDCAllOrder;
		}

		public String getUDRAllOrder() {
			return UDRAllOrder;
		}

		public void setUDRAllOrder(String uDRAllOrder) {
			UDRAllOrder = uDRAllOrder;
		}

		public String getUDZAllORder() {
			return UDZAllORder;
		}

		public void setUDZAllORder(String uDZAllORder) {
			UDZAllORder = uDZAllORder;
		}

	}

}
