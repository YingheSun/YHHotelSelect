package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.adapter.Total_Order_Adapter;
import com.example.model.Already_List;
import com.example.model.Already_List.Already;
import com.example.model.Order_model.Order;
import com.example.model.Order_model;
import com.example.qr_codescan.MipcaActivityCapture;
import com.lidroid.xutils.http.RequestParams;

public class All_Outstanding_Orders extends BaseActivity {

	private ListView list_order;
	private String url_already = "ESPLTOrder.GetAllUDOrder";
	private RequestParams params;
	private Total_Order_Adapter total_order_adapter;
	private List<Order> list = new ArrayList<Order>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_outstanding_orders);
		initTitle("返回", "全部未完成订单", "");
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
		HttpXutils(this, url_already, params, Order_model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Order_model order_list = (Order_model) object;
				if (order_list.getRet().equals("200")) {
					list.addAll(order_list.getData());
					total_order_adapter = new Total_Order_Adapter(getApplicationContext(), list);
					list_order.setAdapter(total_order_adapter);
				} else {
					Toast.makeText(getApplicationContext(), order_list.getMsg().toString(), 1).show();
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
