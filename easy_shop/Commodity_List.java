package com.example.easy_shop;

import android.os.Bundle;
import android.view.View;

public class Commodity_List extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commodity_list);
		initTitle("����", "��Ʒ�б�", null);
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		}
	}

	@Override
	protected void init() {

	}

	@Override
	protected void setListner() {

	}

}
