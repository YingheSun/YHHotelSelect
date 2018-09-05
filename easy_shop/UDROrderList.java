package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.Total_Order_Adapter;
import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.easy_shop.R.style;
import com.example.model.Order_model;
import com.example.model.Order_model.Order;
import com.example.qr_codescan.MipcaActivityCapture;
import com.example.view.Order_dialog;
import com.lidroid.xutils.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UDROrderList extends BaseActivity {

	private ListView list_order;
	private String url_already = "ESPLTOrder.GetUDROrder";
	private String OrderId, storeid;
	private Order_dialog order_dialog;
	private RequestParams params;
	private Total_Order_Adapter total_order_adapter;
	private List<Order> list = new ArrayList<Order>();
	private TextView tv_confirm, tv_cancel;
	private ImageView image_dialog_delete;
	private RadioGroup radio_group;
	private RadioButton radio_one;
	private RadioButton radio_two;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_outstanding_orders);
		initTitle("返回", "未完成入库订单", "");
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
		list_order.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				OrderId = list.get(position).getSeralordernum();
				storeid = list.get(position).getStoreid();
				order_dialog = new Order_dialog(UDROrderList.this, style.phonedialog);
				order_dialog.show();
				image_dialog_delete = (ImageView) order_dialog.findViewById(R.id.image_dialog_delete);
				tv_confirm = (TextView) order_dialog.findViewById(R.id.tv_confirm);
				tv_cancel = (TextView) order_dialog.findViewById(R.id.tv_cancel);
				radio_group = (RadioGroup) order_dialog.findViewById(R.id.radio_group);
				radio_one = (RadioButton) order_dialog.findViewById(R.id.radio_one);
				radio_two = (RadioButton) order_dialog.findViewById(R.id.radio_two);
				radio_group.check(R.id.radio_one);
				image_dialog_delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						order_dialog.dismiss();
					}
				});
				tv_confirm.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						if (radio_one.isChecked() == true) {
							restartScan();
							order_dialog.dismiss();
						} else if (radio_two.isChecked() == true) {
							order_dialog.dismiss();
							openOrder();
						}

					}
				});
				tv_cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						order_dialog.dismiss();
					}
				});
			}
		});

	}

	public void restartScan() {
		bundle.putString("restart", "YES");
		bundle.putString("OrderId", OrderId);
		bundle.putString("title", "STOREIN");
		bundle.putString("scanType", "complex");
		bundle.putString("returnable", "NO");
		bundle.putString("returnType", "sustained");
		bundle.putString("storeid", storeid);
		CommApplication.getInstance().setScanAreaType("barcodeType");
		startActivity(MipcaActivityCapture.class, bundle);
	}

	public void openOrder() {
		Intent intent = new Intent(getApplicationContext(), Look_Activity.class);
		intent.putExtra("order", OrderId);
		startActivity(intent);
	}

	@Override
	protected void setListner() {

	}

}
