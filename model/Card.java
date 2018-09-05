package com.example.model;

import java.util.List;

public class Card {
	private String ret;
	private String msg;
	public List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
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

	public class Data {
		private String userid;
		private String cardshop;
		private String time;
		private String gpsx;
		private String gpsy;
		private String state;
		private String id;

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getCardshop() {
			return cardshop;
		}

		public void setCardshop(String cardshop) {
			this.cardshop = cardshop;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
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

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}
}
