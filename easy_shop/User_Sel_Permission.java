package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.List_User_adapter;

import com.example.model.Register_Model;
import com.example.model.Register_Model.Data;
import com.lidroid.xutils.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class User_Sel_Permission extends BaseActivity {
	private ListView list_user;
	private List_User_adapter user_adapter;
	private List<Data> list = new ArrayList<Data>();
	private String url = "ESShopUser.GetUserList";
	private RequestParams params;
	private String userid;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_list);
		initTitle("·µ»Ø", getIntent().getStringExtra("title").toString(), null);
		init();
		setListner();
		params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid().toString());
		HttpXutils(this, url, params, Register_Model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Register_Model user_list = (Register_Model) object;
				if (user_list.getRet().equals("200")) {
					list.addAll(user_list.getData());
					user_adapter = new List_User_adapter(getApplicationContext(), list);
					list_user.setAdapter(user_adapter);
				} else {
					 Toast.makeText(context, user_list.getMsg(), 1).show();
				}

			}

			@Override
			public void onFailure(String code, String info) {
				// TODO Auto-generated method stub

			}
		});
		list_user.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				userid = list.get(position).getId();
				bundle.putString("userid", userid);
				startActivity(Authorization_Management.class, bundle);

			}
		});
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
		list_user = (ListView) findViewById(R.id.list_shop);
	}

	@Override
	protected void setListner() {

	}

}
