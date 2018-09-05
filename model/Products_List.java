package com.example.model;

import java.util.List;

import com.example.model.Card.Data;

public class Products_List {
	private String ret;
	private String msg;
	public List<OrderList> data;

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

	public List<OrderList> getData() {
		return data;
	}

	public void setData(List<OrderList> data) {
		this.data = data;
	}

	public class OrderList {
		private String total;
		private String linecount;
		private String managed;
		private String unmanaged;
		public List<Products> retlist;

		public List<Products> getRetlist() {
			return retlist;
		}

		public void setRetlist(List<Products> retlist) {
			this.retlist = retlist;
		}

		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

		public String getLinecount() {
			return linecount;
		}

		public void setLinecount(String linecount) {
			this.linecount = linecount;
		}

		public String getManaged() {
			return managed;
		}

		public void setManaged(String managed) {
			this.managed = managed;
		}

		public String getUnmanaged() {
			return unmanaged;
		}

		public void setUnmanaged(String unmanaged) {
			this.unmanaged = unmanaged;
		}

	}

	public class Products {

		private String id;
		private String barcode;
		private String number;
		private String price;
		private String count;
		private String seral_id;
		private String time;
		private String other;
		private String goodname;

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

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getSeral_id() {
			return seral_id;
		}

		public void setSeral_id(String seral_id) {
			this.seral_id = seral_id;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getOther() {
			return other;
		}

		public void setOther(String other) {
			this.other = other;
		}

		public String getGoodname() {
			return goodname;
		}

		public void setGoodname(String goodname) {
			this.goodname = goodname;
		}

	}
}
