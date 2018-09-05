package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.Sort_Management_Adapter;
import com.example.model.Sort_Management_List;
import com.example.model.Sort_Management_List.Sort;
import com.lidroid.xutils.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class Type_Sel_Activity extends BaseActivity {
	private ListView list_shop;
	private Sort_Management_Adapter sort_management_Adapter;
	private List<Sort> list = new ArrayList<Sort>();
	private String url = "ESTypeManage.GetTypeList";
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_list);
		initTitle("返回", "分类选择", "");
		init();
		setListner();
		params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance()
				.getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid().toString());
		HttpXutils(this, url, params, Sort_Management_List.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Sort_Management_List sort_management_list = (Sort_Management_List) object;
						if (sort_management_list.getRet().equals("200")) {
							list.addAll(sort_management_list.getData());
							sort_management_Adapter = new Sort_Management_Adapter(
									getApplicationContext(), list);
							list_shop.setAdapter(sort_management_Adapter);
						} else {
							Toak(Type_Sel_Activity.this, sort_management_list.getMsg(), 1);
						}

					}

					@Override
					public void onFailure(String code, String info) {
						// TODO Auto-generated method stub

					}
				});
		list_shop.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// startActivity(Order_Revise_Out.class);
				Intent intent = new Intent(getApplicationContext(),
						Type_Sel_Goods_List.class);
				intent.putExtra("TypeId", list.get(position).getId());
				intent.putExtra("TypeName", list.get(position).getType());
				startActivity(intent);

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
		list_shop = (ListView) findViewById(R.id.list_shop);
	}

	@Override
	protected void setListner() {

	}

	

	

}
