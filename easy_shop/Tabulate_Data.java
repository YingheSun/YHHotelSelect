package com.example.easy_shop;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Acc_TD_model;
import com.example.model.Already_List;
import com.lidroid.xutils.http.RequestParams;

public class Tabulate_Data extends BaseActivity {
	private TextView shop_name, user, order_amount, to_order, total_cash_register, single_storage, total_money,
			unfinished_chukuchan, out_money, transfer_single;
	private String url = "ESPLTShop.GetTDCount";
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabulate_data);
		initTitle("����", "��������", null);
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
		shop_name = (TextView) findViewById(R.id.shop_name);
		user = (TextView) findViewById(R.id.user);
		order_amount = (TextView) findViewById(R.id.order_amount);
		to_order = (TextView) findViewById(R.id.to_order);
		total_cash_register = (TextView) findViewById(R.id.total_cash_register);
		single_storage = (TextView) findViewById(R.id.single_storage);
		total_money = (TextView) findViewById(R.id.total_money);
		unfinished_chukuchan = (TextView) findViewById(R.id.unfinished_chukuchan);
		out_money = (TextView) findViewById(R.id.out_money);
		transfer_single = (TextView) findViewById(R.id.transfer_single);
		params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url, params, Acc_TD_model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Acc_TD_model already_list = (Acc_TD_model) object;
				if (already_list.getRet().equals("200")) {
					shop_name.setText("�̵�����:  " + already_list.getData().get(0).getShopName());
					user.setText("�û�����:" + already_list.getData().get(0).getUserName());
					order_amount.setText("�����ܼ�:��" + already_list.getData().get(0).getCountAll());
					to_order.setText("δ��ɶ���:" + already_list.getData().get(0).getUDAllOrder()+"��");
					total_cash_register.setText("�����ܼ�:��" + already_list.getData().get(0).getExpAll());
					single_storage.setText("δ����ⵥ:" + already_list.getData().get(0).getUDRAllOrder()+"��");
					total_money.setText("��Ǯ�ܼ�:��" + already_list.getData().get(0).getMoneyInAll());
					unfinished_chukuchan.setText("δ����ⵥ:" + already_list.getData().get(0).getUDCAllOrder()+"��");
					out_money.setText("֧Ǯ�ܼ�:��" + already_list.getData().get(0).getMoneyOutAll());
					transfer_single.setText("δ��ת�ⵥ:" + already_list.getData().get(0).getUDZAllORder()+"��");
				} else {
					Toast.makeText(getApplicationContext(), already_list.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}

	@Override
	protected void setListner() {
	}

}
