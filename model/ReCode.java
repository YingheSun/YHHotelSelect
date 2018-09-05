package com.example.model;

import java.util.List;

public class ReCode {
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
		private String changeline;

		public String getChangeline() {
			return changeline;
		}

		public void setChangeline(String changeline) {
			this.changeline = changeline;
		}
	}

}
