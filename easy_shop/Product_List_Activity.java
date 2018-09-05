package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.List_Already_Adapter;
import com.example.adapter.Product_Adapter;
import com.example.model.Already_List;
import com.example.model.Already_List.Already;
import com.example.model.Good_model;
import com.example.model.Good_model.Datas;
import com.lidroid.xutils.http.RequestParams;

public class Product_List_Activity extends BaseActivity {

	private ListView list_already;
	private String url_already = "ESGoodsList.GetGoodsList";
	private RequestParams params_already;
	private Product_Adapter already_adapter;
	private List<Datas> list = new ArrayList<Datas>();
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_warehouse);
		initTitle("返回", "商品列表", "");
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
				bundle.putString("barcode", list.get(position).getBarcode().toString());
				startActivity(Av_Commodity.class, bundle);
			}
		});

	}

	@Override
	protected void setListner() {

	}

	protected void onRestart() {
		super.onRestart();
		http();
	}
	
	public void http(){
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
					Toak(Product_List_Activity.this, already_list.getMsg(), 0);
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}
}
