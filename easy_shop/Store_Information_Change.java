package com.example.easy_shop;

import com.example.model.Commodity_Information;
import com.example.model.Manage_List;
import com.example.model.ReCode;
import com.lidroid.xutils.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Store_Information_Change extends BaseActivity {
	private EditText et_warehouse_name, et_warehouse_address, et_phone,
			et_phone_store, et_warehouse_synopsis, et_we_chat;
	private Button tv_submit;
	private String url_first = "ESShopInfo.GetShopInfo";
	private String url = "ESShopInfo.ChgShopInfo";
	private RequestParams params_first;
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_information_change);
		initTitle("返回", "商店信息修改", null);
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_submit:
			params = new RequestParams();
			params.addBodyParameter("shopid", CommApplication.getInstance()
					.getShopid().toString());
			params.addBodyParameter("shopname", et_warehouse_name.getText()
					.toString());
			params.addBodyParameter("address", et_warehouse_address.getText()
					.toString());
			params.addBodyParameter("callphone", et_phone.getText().toString());
			params.addBodyParameter("telephone", et_phone_store.getText()
					.toString());
			params.addBodyParameter("introduce", et_warehouse_synopsis
					.getText().toString());
			params.addBodyParameter("weixin", et_we_chat.getText().toString());
			params.addBodyParameter("reqid", CommApplication.getInstance()
					.getUserid());
			HttpXutils(this, url, params, ReCode.class, new ServerResult() {

				@Override
				public void onSuccess(Object object) {
					ReCode recode = (ReCode) object;
					if (recode.getRet().equals("200")) {
						Toast.makeText(context, "修改成功", 1).show();
						finish();
					}
				}

				@Override
				public void onFailure(String code, String info) {

				}
			});
			break;
		}
	}

	@Override
	protected void init() {
		et_warehouse_name = (EditText) findViewById(R.id.et_warehouse_name);
		et_warehouse_address = (EditText) findViewById(R.id.et_warehouse_address);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_phone_store = (EditText) findViewById(R.id.et_phone_store);
		et_warehouse_synopsis = (EditText) findViewById(R.id.et_warehouse_synopsis);
		et_we_chat = (EditText) findViewById(R.id.et_we_chat);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		params_first = new RequestParams();
		params_first.addBodyParameter("shopid", CommApplication.getInstance()
				.getShopid().toString());
		params_first.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid());
		HttpXutils(this, url_first, params_first, Commodity_Information.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Commodity_Information commodity_information = (Commodity_Information) object;
						if (commodity_information.getRet().equals("200")) {
							et_warehouse_name.setText(commodity_information
									.getData().get(0).getShop_name());
							et_warehouse_address.setText(commodity_information
									.getData().get(0).getShop_address());
							et_phone.setText(commodity_information.getData()
									.get(0).getCallphone());
							et_phone_store.setText(commodity_information
									.getData().get(0).getTelephone());
							et_warehouse_synopsis.setText(commodity_information
									.getData().get(0).getIntroduce());
							et_we_chat.setText(commodity_information.getData()
									.get(0).getWeixin());
						}
					}

					@Override
					public void onFailure(String code, String info) {

					}
				});
	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
	}

}
