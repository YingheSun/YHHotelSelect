package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.List_Shop_Adapter;
import com.example.adapter.List_Shop_On_Adapter;
import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Default_Store;
import com.example.model.Products_List;
import com.example.model.Products_List.Products;
import com.example.model.Sales_List;
import com.example.model.Sales_List.Sales;
import com.lidroid.xutils.http.RequestParams;

public class Firm_Sales_Stock extends BaseActivity {
	private ListView list_order;
	private List_Shop_On_Adapter shop_on_adapter;
	private List<Sales> list = new ArrayList<Sales>();
	private String url = "ESConfirmOrder.GetOutOrder";
	private String url_submit = "ESCommitChg.CommitOrder";
	private Button tv_submit;
	private RequestParams params = new RequestParams();
	private RequestParams params_submit;
	private String order;
	private TextView order_number, total_money, total_number, already_input, no_entry;
	private Bundle bundle = new Bundle();
	private Sales_List sales_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firm_order2);
		initTitle("返回", "确认订单", null);
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			this.finish();
			break;
		case R.id.tv_submit:
			params_submit = new RequestParams();
			params_submit.addBodyParameter("order", order);
			params_submit.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
			params_submit.addBodyParameter("storeout", getIntent().getStringExtra("OutStore").toString());
			params_submit.addBodyParameter("storein", getIntent().getStringExtra("InStore").toString());
			params_submit.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
			HttpXutils(this, url_submit, params_submit, Sales_List.class, new ServerResult() {

				@Override
				public void onSuccess(Object object) {
					Sales_List sales_list = (Sales_List) object;
					if (sales_list.getRet().equals("200")) {
						Toak(Firm_Sales_Stock.this, "商品转库成功", 1);
						startActivity(MainActivity.class);
					} else {
						Toast.makeText(getApplicationContext(), sales_list.getMsg().toString(), 1).show();
					}

				}

				@Override
				public void onFailure(String code, String info) {

				}
			});

			// bundle.putString("total",
			// sales_list.getData().get(0).getTotal());
			// bundle.putString("order", order);
			//// bundle.putString("storeid",
			// getIntent().getStringExtra("storeid")
			//// .toString());
			// startActivity(Money_Out_Count.class, bundle);
			// break;
		}
	}

	@Override
	protected void init() {
		order_number = (TextView) findViewById(R.id.order_number);
		total_money = (TextView) findViewById(R.id.total_money);
		total_number = (TextView) findViewById(R.id.total_number);
		already_input = (TextView) findViewById(R.id.already_input);
		no_entry = (TextView) findViewById(R.id.no_entry);
		list_order = (ListView) findViewById(R.id.list_order);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		order = getIntent().getStringExtra("order");
		params.addBodyParameter("order", order);
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url, params, Sales_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				sales_list = (Sales_List) object;
				if (sales_list.getRet().equals("200")) {
					order_number.setText("订单号：" + order);
					total_money.setText("总价：" + sales_list.getData().get(0).getTotal());
					total_number.setText("总条数：" + sales_list.getData().get(0).getLinecount());
					already_input.setText("库存报警数：" + sales_list.getData().get(0).getWarnings());
					no_entry.setText("");
					list.clear();
					list.addAll(sales_list.getData().get(0).getRetlist());
					shop_on_adapter = new List_Shop_On_Adapter(getApplicationContext(), list);
					list_order.setAdapter(shop_on_adapter);
				} else {
					Toast.makeText(getApplicationContext(), sales_list.getMsg().toString(), 1).show();
				}

			}

			@Override
			public void onFailure(String code, String info) {
				// TODO Auto-generated method stub

			}
		});
		list_order.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// startActivity(Order_Revise_Out.class);
				Intent intent = new Intent(getApplicationContext(), Order_Revise_Out.class);
				intent.putExtra("order", getIntent().getStringExtra("order").toString());
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
		tv_submit.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		params.addBodyParameter("order", order);
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url, params, Sales_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Sales_List sales_list = (Sales_List) object;
				if (sales_list.getRet().equals("200")) {
					order_number.setText("订单号：" + order);
					total_money.setText("总价：" + sales_list.getData().get(0).getTotal());
					total_number.setText("总条数：" + sales_list.getData().get(0).getLinecount());
					already_input.setText("库存报警数：" + sales_list.getData().get(0).getWarnings());
					no_entry.setText("");
					list.clear();
					list.addAll(sales_list.getData().get(0).getRetlist());
					shop_on_adapter = new List_Shop_On_Adapter(getApplicationContext(), list);
					list_order.setAdapter(shop_on_adapter);
				} else {
					Toast.makeText(getApplicationContext(), sales_list.getMsg().toString(), 1).show();
				}

			}

			@Override
			public void onFailure(String code, String info) {
				// TODO Auto-generated method stub

			}
		});

	}

}
