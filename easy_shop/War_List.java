package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.Total_Order_Adapter;
import com.example.adapter.War_List_Adapter;
import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Order_model;
import com.example.model.Order_model.Order;
import com.example.model.War_List_model;
import com.example.model.War_List_model.War;
import com.lidroid.xutils.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class War_List extends BaseActivity {

	private ListView list_order;
	private String url_already = "ESPLTGWar.GetAllWar";
	private RequestParams params;
	private War_List_Adapter war_adapter;
	private List<War> list = new ArrayList<War>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_outstanding_orders);
		initTitle("·µ»Ø", "¿â´æ±¨¾¯", "");
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
		list_order = (ListView) findViewById(R.id.list_order);
		params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url_already, params, War_List_model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				War_List_model war_list = (War_List_model) object;
				if (war_list.getRet().equals("200")) {
					list.addAll(war_list.getData());
					war_adapter = new War_List_Adapter(getApplicationContext(), list);
					list_order.setAdapter(war_adapter);
				} else {
					Toast.makeText(getApplicationContext(), war_list.getMsg().toString(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
		// list_order.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		//
		// }
		// });

	}

	@Override
	protected void setListner() {

	}

}
