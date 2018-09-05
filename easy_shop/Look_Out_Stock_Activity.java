package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.List_Shop_Chg_Adapter;
import com.example.model.Chg_List;
import com.example.model.Chg_List.ChgData;
import com.lidroid.xutils.http.RequestParams;

public class Look_Out_Stock_Activity extends BaseActivity {
	private ListView list_shop;
	private List_Shop_Chg_Adapter shop_chg_adapter;
	private List<ChgData> list = new ArrayList<ChgData>();
	private String url = "ESConfirmOrder.GetChgOrder";
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
		HttpXutils(this, url, params, Chg_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Chg_List chg_list = (Chg_List) object;
				if (chg_list.getRet().equals("200")) {
					list.clear();
					list.addAll(chg_list.getData());
					shop_chg_adapter = new List_Shop_Chg_Adapter(
							getApplicationContext(), list);
					list_shop.setAdapter(shop_chg_adapter);
				} else {
					 Toast.makeText(context, chg_list.getMsg(), 1).show();
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
						Order_Revise_Out.class);
				intent.putExtra("order", getIntent().getStringExtra("order")
						.toString());
				intent.putExtra("barcode", list.get(position).getBarcode());
				intent.putExtra("goodname", list.get(position).getGoodname());
				intent.putExtra("number", list.get(position).getNumber());
				intent.putExtra("other", list.get(position).getOther());
				intent.putExtra("count", list.get(position).getCount());
				intent.putExtra("price", list.get(position).getPrice());
				intent.putExtra("discount", list.get(position).getDiscount());
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
		HttpXutils(this, url, params, Chg_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Chg_List chg_list = (Chg_List) object;
				if (chg_list.getRet().equals("200")) {
					list.clear();
					list.addAll(chg_list.getData());
					shop_chg_adapter = new List_Shop_Chg_Adapter(
							getApplicationContext(), list);
					list_shop.setAdapter(shop_chg_adapter);
				} else {
					Toast.makeText(context, chg_list.getMsg(), 1).show();
				}

			}

			@Override
			public void onFailure(String code, String info) {
				// TODO Auto-generated method stub

			}
		});

	}

}
