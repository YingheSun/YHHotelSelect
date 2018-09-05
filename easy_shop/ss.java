package com.example.easy_shop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ss extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// HttpXutils(this, url, params, Jie.class, new ServerResult() {
		//
		// @Override
		// public void onSuccess(Object object) {
		// // TODO Auto-generated method stub
		// Jie jie = (Jie) object;
		//
		// }
		//
		// @Override
		// public void onFailure(String code, String info) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		initTitle("返回", "杰哥", "提交");
		Toak(this, "我我我我", 1);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:

			break;
		case R.id.tv_titlebar_right:
			break;
		}
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListner() {
		// TODO Auto-generated method stub

	}

}
