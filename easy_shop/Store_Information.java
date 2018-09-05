package com.example.easy_shop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Commodity_Information;
import com.example.model.ReCode;
import com.lidroid.xutils.http.RequestParams;

public class Store_Information extends BaseActivity {
	private TextView et_warehouse_name, et_warehouse_address, et_phone,
			et_phone_store, et_warehouse_synopsis, et_we_chat;
	private String url = "ESShop.GetShopInfo";
	private RequestParams params = new RequestParams();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_information);
		initTitle("返回", "商店信息", null);
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
		et_warehouse_name = (TextView) findViewById(R.id.et_warehouse_name);
		et_warehouse_address = (TextView) findViewById(R.id.et_warehouse_address);
		et_phone = (TextView) findViewById(R.id.et_phone);
		et_phone_store = (TextView) findViewById(R.id.et_phone_store);
		et_warehouse_synopsis = (TextView) findViewById(R.id.et_warehouse_synopsis);
		et_we_chat = (TextView) findViewById(R.id.et_we_chat);
		getShopInfo();
	}
	
	protected void getShopInfo() {
		params.addBodyParameter("id", CommApplication.getInstance().getShopid());
		HttpXutils(this, url, params, Commodity_Information.class,
				new ServerResult() {
					@Override
					public void onSuccess(Object object) {
						Commodity_Information shop_Model = (Commodity_Information) object;
						if (shop_Model.getRet().equals("200")) {
							et_warehouse_name.setText(shop_Model.getData().get(0)
									.getShop_name());
							et_warehouse_address.setText(shop_Model.getData().get(0).getShop_address());
							et_phone.setText(shop_Model.getData().get(0).getTelephone());
							et_phone_store.setText(shop_Model.getData().get(0).getCallphone());
							et_warehouse_synopsis.setText(shop_Model.getData().get(0).getIntroduce());
							et_we_chat.setText(shop_Model.getData().get(0).getWeixin());
						}
					}

					@Override
					public void onFailure(String code, String info) {
						// Toak(Login.this, "登陆失败", 1);
					}
				});

	}

	@Override
	protected void setListner() {
	}

}
