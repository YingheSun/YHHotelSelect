package com.example.easy_shop;

import com.example.model.Already_List;
import com.lidroid.xutils.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Warehouse extends BaseActivity {
	private EditText et_warehouse_name;
	// private EditText et_warehouse_address, et_warehouse_synopsis;
	private Button tv_submit;
	private String url = "ESStoreManage.AddStore";
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_warehouse);
		initTitle("返回", "添加仓库", null);
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
			params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
			params.addBodyParameter("storename", et_warehouse_name.getText().toString());
			params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
			// params.addBodyParameter("storeaddress",
			// et_warehouse_address.getText().toString());
			// params.addBodyParameter("storedetail",
			// et_warehouse_synopsis.getText().toString());
			HttpXutils(this, url, params, Already_List.class, new ServerResult() {

				@Override
				public void onSuccess(Object object) {
					Already_List already_list = (Already_List) object;
					if (already_list.getRet().equals("200")) {
						Toast.makeText(getApplicationContext(), "添加成功", 1).show();
						finish();
					} else {
						Toast.makeText(getApplicationContext(), already_list.getMsg(), 1).show();
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
		tv_submit = (Button) findViewById(R.id.tv_submit);
		et_warehouse_name = (EditText) findViewById(R.id.et_warehouse_name);
		// et_warehouse_address = (EditText)
		// findViewById(R.id.et_warehouse_address);
		// et_warehouse_synopsis = (EditText)
		// findViewById(R.id.et_warehouse_synopsis);
	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
	}

}
