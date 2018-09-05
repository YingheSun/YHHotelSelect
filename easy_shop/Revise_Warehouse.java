package com.example.easy_shop;

import com.example.model.Already_List;
import com.lidroid.xutils.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Revise_Warehouse extends BaseActivity {
	private TextView et_warehouse_number;
	private EditText et_warehouse_name;
	private EditText et_warehouse_address, et_warehouse_synopsis;
	private Button tv_submit;
	private String url = "ESStoreManage.GetStoreInfo";
	private String url_sub = "ESStoreManage.ChangeStoreInfo";
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.revise_warehouse);
		initTitle("返回", "修改仓库信息", null);
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
			https();
			break;
		}
	}

	@Override
	protected void init() {
		et_warehouse_number = (TextView) findViewById(R.id.et_warehouse_number);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		et_warehouse_name = (EditText) findViewById(R.id.et_warehouse_name);
		et_warehouse_address = (EditText) findViewById(R.id.et_warehouse_address);
		et_warehouse_synopsis = (EditText) findViewById(R.id.et_warehouse_synopsis);
		getStoreInfo();
	}

	public void getStoreInfo() {
		params = new RequestParams();
		params.addBodyParameter("storeid", getIntent().getStringExtra("storeid").toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url, params, Already_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Already_List already_list = (Already_List) object;
				if (already_list.getRet().equals("200")) {
					et_warehouse_number.setText(already_list.getData().get(0).getId());
					et_warehouse_name.setText(already_list.getData().get(0).getStroe());
					et_warehouse_address.setText(already_list.getData().get(0).getKindsnum());
					et_warehouse_synopsis.setText(already_list.getData().get(0).getNumbers());
				} else {
					Toast.makeText(getApplicationContext(), already_list.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}

	public void https() {
		params = new RequestParams();
		params.addBodyParameter("storeid", getIntent().getStringExtra("storeid").toString());
		params.addBodyParameter("storename", et_warehouse_name.getText().toString());
		params.addBodyParameter("kindsnum", et_warehouse_address.getText().toString());
		params.addBodyParameter("numbers", et_warehouse_synopsis.getText().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url_sub, params, Already_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Already_List already_list = (Already_List) object;
				if (already_list.getRet().equals("200")) {
//					et_warehouse_number.setText(already_list.getData().get(0).getId());
//					et_warehouse_name.setText(already_list.getData().get(0).getStroe());
//					et_warehouse_address.setText(already_list.getData().get(0).getKindsnum());
//					et_warehouse_synopsis.setText(already_list.getData().get(0).getNumbers());
					Toast.makeText(getApplicationContext(), "修改成功", 1).show();
					finish();
				} else {
					Toast.makeText(getApplicationContext(), already_list.getMsg(), 1).show();
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
