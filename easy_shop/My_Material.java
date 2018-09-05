package com.example.easy_shop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adapter.List_Already_Adapter;
import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Already_List;
import com.example.model.Register_Model;
import com.lidroid.xutils.http.RequestParams;

public class My_Material extends BaseActivity {
	private EditText et_name, et_tel;
	private Button tv_submit;
	private RequestParams params;
	private String url_Info = "ESUserInfo.GetUserInfo";
	private String url_chgInfo = "ESUserInfo.ChgMyInfo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_material);
		initTitle("返回", "我的信息", null);
		init();
		getMyInfo();
		setListner();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_submit:
			updateInfo();
			break;
		}
	}

	public void updateInfo() {
		params = new RequestParams();
		params.addBodyParameter("userid", CommApplication.getInstance().getUserid());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		params.addBodyParameter("name", et_name.getText().toString());
		params.addBodyParameter("phonenum", et_tel.getText().toString());
		HttpXutils(this, url_chgInfo, params, Register_Model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Register_Model Info_list = (Register_Model) object;
				if (Info_list.getRet().equals("200")) {
					Toast.makeText(context, "修改个人信息成功", 1).show();
					finish();
				} else {
					Toast.makeText(context, Info_list.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}

	public void getMyInfo() {
		params = new RequestParams();
		params.addBodyParameter("userid", CommApplication.getInstance().getUserid());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url_Info, params, Register_Model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Register_Model Info_list = (Register_Model) object;
				if (Info_list.getRet().equals("200")) {
					et_name.setText(Info_list.getData().get(0).getUser_name());
					et_tel.setText(Info_list.getData().get(0).getPhonenum());
				} else {

				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}

	@Override
	protected void init() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_tel = (EditText) findViewById(R.id.et_tel);
		tv_submit = (Button) findViewById(R.id.tv_submit);

	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
	}

}
