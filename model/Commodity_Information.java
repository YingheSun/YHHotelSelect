package com.example.model;

import java.util.List;

public class Commodity_Information {
	private String ret;
	private String msg;
	public List<Data> data;

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

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public class Data {
		private String id;
		private String shop_name;
		private String shop_address;
		private String callphone;
		private String telephone;
		private String introduce;
		private String weixin;
		private String gpsx;
		private String gpsy;
		private String owner;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getShop_name() {
			return shop_name;
		}

		public void setShop_name(String shop_name) {
			this.shop_name = shop_name;
		}

		public String getShop_address() {
			return shop_address;
		}

		public void setShop_address(String shop_address) {
			this.shop_address = shop_address;
		}

		public String getCallphone() {
			return callphone;
		}

		public void setCallphone(String callphone) {
			this.callphone = callphone;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getIntroduce() {
			return introduce;
		}

		public void setIntroduce(String introduce) {
			this.introduce = introduce;
		}

		public String getWeixin() {
			return weixin;
		}

		public void setWeixin(String weixin) {
			this.weixin = weixin;
		}

		public String getGpsx() {
			return gpsx;
		}

		public void setGpsx(String gpsx) {
			this.gpsx = gpsx;
		}

		public String getGpsy() {
			return gpsy;
		}

		public void setGpsy(String gpsy) {
			this.gpsy = gpsy;
		}

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

	}

}
