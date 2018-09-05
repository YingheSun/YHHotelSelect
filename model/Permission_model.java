package com.example.model;

import java.util.List;

import com.example.model.Order_model.Order;

public class Permission_model {
	private String ret;
	private String msg;
	public List<Permission> data;

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

	public List<Permission> getData() {
		return data;
	}

	public void setData(List<Permission> data) {
		this.data = data;
	}

	public class Permission {
		private String permissionname;
		private String permission;
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPermissionname() {
			return permissionname;
		}

		public void setPermissionname(String permissionname) {
			this.permissionname = permissionname;
		}

		public String getPermission() {
			return permission;
		}

		public void setPermission(String permission) {
			this.permission = permission;
		}

	}
}