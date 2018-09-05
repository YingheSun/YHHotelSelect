package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.List_Shop_Adapter;
import com.example.model.Order_Number;
import com.example.model.Products_List;
import com.example.model.Products_List.Products;
import com.lidroid.xutils.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Look_Activity extends BaseActivity {
	private ListView list_shop;
	private List_Shop_Adapter shop_adapter;
	private List<Products> list = new ArrayList<Products>();
	private String url = "ESConfirmOrder.GetInOrder";
	private RequestParams params = new RequestParams();
	private String order;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.look_activity);
		initTitle("·µ»Ø", "Ó††ÎÏêÇé", null);
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
		list_shop = (ListView) findViewById(R.id.list_shop);
		order = getIntent().getStringExtra("order");
		params.addBodyParameter("order", order);
		params.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid());
		HttpXutils(this, url, params, Products_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Products_List products_list = (Products_List) object;
				if (products_list.getRet().equals("200")) {
					list.clear();
					list.addAll(products_list.getData().get(0).getRetlist());
					shop_adapter = new List_Shop_Adapter(
							getApplicationContext(), list);
					list_shop.setAdapter(shop_adapter);
				} else {
					// Toast.makeText(context, "0", 1).show();
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
				Intent intent = new Intent(getApplicationContext(),
						Order_Revise.class);
				intent.putExtra("order", getIntent().getStringExtra("order")
						.toString());
				intent.putExtra("barcode", list.get(position).getBarcode());
				intent.putExtra("goodname", list.get(position).getGoodname());
				intent.putExtra("number", list.get(position).getNumber());
				intent.putExtra("other", list.get(position).getOther());
				intent.putExtra("count", list.get(position).getCount());
				intent.putExtra("price", list.get(position).getPrice());
				startActivity(intent);
			}
		});
	}

	@Override
	protected void setListner() {
	}

	@Override
	protected void onResume() {
		super.onResume();
		params.addBodyParameter("order", order);
		params.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid());
		HttpXutils(this, url, params, Products_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Products_List products_list = (Products_List) object;
				if (products_list.getRet().equals("200")) {
					list.clear();
					list.addAll(products_list.getData().get(0).getRetlist());
					shop_adapter = new List_Shop_Adapter(
							getApplicationContext(), list);
					list_shop.setAdapter(shop_adapter);
				} else {
					// Toast.makeText(context, "0", 1).show();
				}

			}

			@Override
			public void onFailure(String code, String info) {
				// TODO Auto-generated method stub

			}
		});

	}
}
