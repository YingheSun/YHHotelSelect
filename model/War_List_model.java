package com.example.model;

import java.util.List;

public class War_List_model {
	private String ret;
	private String msg;
	public List<War> data;

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

	public List<War> getData() {
		return data;
	}

	public void setData(List<War> data) {
		this.data = data;
	}

	public class War {
		private String number;
		private String warningline;
		private String stroe;
		private String goods_name;

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getWarningline() {
			return warningline;
		}

		public void setWarningline(String warningline) {
			this.warningline = warningline;
		}

		public String getStroe() {
			return stroe;
		}

		public void setStroe(String stroe) {
			this.stroe = stroe;
		}

		public String getGoods_name() {
			return goods_name;
		}

		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}

	}
}