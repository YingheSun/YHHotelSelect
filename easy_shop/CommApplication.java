package com.example.easy_shop;

import android.app.Application;

public class CommApplication extends Application {
	public String order;
	public String shopid;
	public String storeid;
	public String userid;
	public String count;
	public String expense;
	public String scanAreaType;
	public String scanReturn;

	public String getScanReturn() {
		return scanReturn;
	}

	public void setScanReturn(String scanReturn) {
		this.scanReturn = scanReturn;
	}

	public String getScanAreaType() {
		return scanAreaType;
	}

	public void setScanAreaType(String scanAreaType) {
		this.scanAreaType = scanAreaType;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	private static CommApplication instance;

	public static CommApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

}
