package com.example.model;

import java.util.List;

public class Register_Model {
	private String ret;
	private String msg;
	private List<Data> data;

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
		private String id;
		private String user_name;
		private String user_level;
		private String permission;
		private String phonenum;
		private String password;
		private String states;
		private String roleid;
		private String bandto;
		private String uuid;
		private String time;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getUser_level() {
			return user_level;
		}

		public void setUser_level(String user_level) {
			this.user_level = user_level;
		}

		public String getPermission() {
			return permission;
		}

		public void setPermission(String permission) {
			this.permission = permission;
		}

		public String getPhonenum() {
			return phonenum;
		}

		public void setPhonenum(String phonenum) {
			this.phonenum = phonenum;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getStates() {
			return states;
		}

		public void setStates(String states) {
			this.states = states;
		}

		public String getRoleid() {
			return roleid;
		}

		public void setRoleid(String roleid) {
			this.roleid = roleid;
		}

		public String getBandto() {
			return bandto;
		}

		public void setBandto(String bandto) {
			this.bandto = bandto;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}
}
