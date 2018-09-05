package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.Product_Adapter;
import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Good_model;
import com.example.model.Good_model.Datas;
import com.lidroid.xutils.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Wait_Goods_List extends BaseActivity {

	private ListView list_already;
	private String url_already = "ESGoodsList.GetWaitGoodList";
	private RequestParams params_already;
	private Product_Adapter already_adapter;
	private List<Datas> list = new ArrayList<Datas>();
	private String storeid;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_warehouse);
		initTitle("返回", "未维护商品列表", "");
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
		http();
		list_already.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), AV_Wait_Activity.class);
				intent.putExtra("id", list.get(position).getId().toString());
				startActivity(intent);
			}
		});

	}

	@Override
	protected void setListner() {

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		http();
	}

	public void http() {
		params_already = new RequestParams();
		params_already.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params_already.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		params_already.addBodyParameter("storeid", "all");
		HttpXutils(this, url_already, params_already, Good_model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Good_model already_list = (Good_model) object;
				if (already_list.getRet().equals("200")) {
					list.clear();
					list.addAll(already_list.getData());
					already_adapter = new Product_Adapter(getApplicationContext(), list);
					list_already.setAdapter(already_adapter);
				} else {
					Toak(Wait_Goods_List.this, already_list.getMsg(), 0);
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}
}
