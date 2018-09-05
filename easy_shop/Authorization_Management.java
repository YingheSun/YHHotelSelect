package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.List_Already_Adapter;
import com.example.adapter.List_Authorization_Adapter;
import com.example.model.Already_List;
import com.example.model.Already_List.Already;
import com.example.model.Permission_model;
import com.example.model.Permission_model.Permission;
import com.lidroid.xutils.http.RequestParams;

public class Authorization_Management extends BaseActivity {

	private ListView list_already;
	private String url_already = "ESUserPermission.GetUserList";
	private RequestParams params_already;
	private List_Authorization_Adapter already_adapter;
	private List<Permission> list = new ArrayList<Permission>();
	private String storeid;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authorization_management);
		initTitle("返回", "权限管理", "");
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
		list_already = (ListView) findViewById(R.id.list_already);
		params_already = new RequestParams();
		params_already.addBodyParameter("shopid", CommApplication.getInstance()
				.getShopid().toString());
		params_already.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid());
		HttpXutils(this, url_already, params_already, Permission_model.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Permission_model already_list = (Permission_model) object;
						if (already_list.getRet().equals("200")) {
							list.addAll(already_list.getData());
							already_adapter = new List_Authorization_Adapter(
									getApplicationContext(), list);
							list_already.setAdapter(already_adapter);
						} else {

						}
					}

					@Override
					public void onFailure(String code, String info) {

					}
				});
		list_already.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});

	}

	@Override
	protected void setListner() {

	}

}
