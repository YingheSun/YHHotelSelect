package com.example.model;

import java.util.List;

import com.example.model.Manage_List.Manage;

public class Manage_List_Two {
	private String ret;
	private String msg;
	public List<Manage_Two> data;

	public List<Manage_Two> getData() {
		return data;
	}

	public void setData(List<Manage_Two> data) {
		this.data = data;
	}

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

	public class Manage_Two {
		private String id;
		private String shopid;
		private String goods_name;
		private String goods_type;
		private String barcode;
		private String onsellingflag;
		private String cost;
		private String lowprice;
		private String price;
		private String storgeid;
		private String time;
		private String managetype;

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

		public String getGoods_name() {
			return goods_name;
		}

		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}

		public String getGoods_type() {
			return goods_type;
		}

		public void setGoods_type(String goods_type) {
			this.goods_type = goods_type;
		}

		public String getBarcode() {
			return barcode;
		}

		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}

		public String getOnsellingflag() {
			return onsellingflag;
		}

		public void setOnsellingflag(String onsellingflag) {
			this.onsellingflag = onsellingflag;
		}

		public String getCost() {
			return cost;
		}

		public void setCost(String cost) {
			this.cost = cost;
		}

		public String getLowprice() {
			return lowprice;
		}

		public void setLowprice(String lowprice) {
			this.lowprice = lowprice;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getStorgeid() {
			return storgeid;
		}

		public void setStorgeid(String storgeid) {
			this.storgeid = storgeid;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getManagetype() {
			return managetype;
		}

		public void setManagetype(String managetype) {
			this.managetype = managetype;
		}

	}

}
