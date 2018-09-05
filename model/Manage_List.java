package com.example.model;

import java.util.List;

public class Manage_List {
	private String ret;
	private String msg;
	public List<Manage> data;

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

	public List<Manage> getData() {
		return data;
	}

	public void setData(List<Manage> data) {
		this.data = data;
	}

	public class Manage {
		private String id;
		private String showname;
		private String shopid;
		private String type;
		private String gototype;
		private String isclickallow;
		private String listnumber;
		private String isroot;
		private String jumpto;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getShowname() {
			return showname;
		}

		public void setShowname(String showname) {
			this.showname = showname;
		}

		public String getShopid() {
			return shopid;
		}

		public void setShopid(String shopid) {
			this.shopid = shopid;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getGototype() {
			return gototype;
		}

		public void setGototype(String gototype) {
			this.gototype = gototype;
		}

		public String getIsclickallow() {
			return isclickallow;
		}

		public void setIsclickallow(String isclickallow) {
			this.isclickallow = isclickallow;
		}

		public String getListnumber() {
			return listnumber;
		}

		public void setListnumber(String listnumber) {
			this.listnumber = listnumber;
		}

		public String getIsroot() {
			return isroot;
		}

		public void setIsroot(String isroot) {
			this.isroot = isroot;
		}

		public String getJumpto() {
			return jumpto;
		}

		public void setJumpto(String jumpto) {
			this.jumpto = jumpto;
		}

	}

}
