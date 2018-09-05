package com.example.model;

import java.util.List;

public class Chg_List {
	private String ret;
	private String msg;
	public List<ChgData> data;

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

	public List<ChgData> getData() {
		return data;
	}

	public void setData(List<ChgData> data) {
		this.data = data;
	}

	public class ChgData {
		private String id;
		private String barcode;
		private String number;
		private String count;
		private String order_num;
		private String other;
		private String discount;
		private String shopid;
		private String storeid;
		private String goodname;
		private String price;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBarcode() {
			return barcode;
		}

		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getOrder_num() {
			return order_num;
		}

		public void setOrder_num(String order_num) {
			this.order_num = order_num;
		}

		public String getOther() {
			return other;
		}

		public void setOther(String other) {
			this.other = other;
		}

		public String getDiscount() {
			return discount;
		}

		public void setDiscount(String discount) {
			this.discount = discount;
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

		public String getGoodname() {
			return goodname;
		}

		public void setGoodname(String goodname) {
			this.goodname = goodname;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

	}
}
