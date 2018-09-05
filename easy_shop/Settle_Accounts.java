package com.example.easy_shop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Acc_CloseAcc_model;
import com.example.model.Acc_TD_model;
import com.example.model.Already_List;
import com.lidroid.xutils.http.RequestParams;

public class Settle_Accounts extends BaseActivity {
	private TextView store, user, to_order;
	private EditText total_money;
	private Button tv_submit;
	private String get_url = "ESPLTAcc.GetAccount";
	private String commit_url = "ESPLTAcc.CloseAccount";
	private RequestParams params;
	private String UnDoneNum;
	private int clickButtonNum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settle_accounts);
		initTitle("返回", "日结账", null);
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_submit:
			if (!UnDoneNum.equals("0") && clickButtonNum == 0) {
				Toast.makeText(getApplicationContext(), "您尚有未完成的订单，如果确定将这些订单都无效华，请再次点击确认提交", 1).show();
				clickButtonNum++;
			} else {
				clickButtonNum++;
				if(clickButtonNum > 2){
					Toast.makeText(getApplicationContext(), "不能多次提交，请退出页面后再试", 1).show();
				}else if (total_money.getText().toString().equals("0")){
					Toast.makeText(getApplicationContext(), "您现在没有什么可以结账的", 1).show();
				}else{
				params = new RequestParams();
				params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
				params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
				params.addBodyParameter("account", total_money.getText().toString());
				HttpXutils(this, commit_url, params, Acc_CloseAcc_model.class, new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Acc_CloseAcc_model already_list = (Acc_CloseAcc_model) object;
						if (already_list.getRet().equals("200")) {
							Toast.makeText(getApplicationContext(), already_list.getData().get(0).getDate()+"成功结账￥"+already_list.getData().get(0).getAccount()+"元", 1).show();
						} else {
							Toast.makeText(getApplicationContext(), already_list.getMsg(), 1).show();
						}
					}

					@Override
					public void onFailure(String code, String info) {

					}
				});
			}
			}
			break;
		}
	}

	@Override
	protected void init() {
		store = (TextView) findViewById(R.id.store);
		user = (TextView) findViewById(R.id.user);
		total_money = (EditText) findViewById(R.id.total_money);
		to_order = (TextView) findViewById(R.id.to_order);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, get_url, params, Acc_TD_model.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Acc_TD_model already_list = (Acc_TD_model) object;
				if (already_list.getRet().equals("200")) {
					store.setText("商店名称：" + already_list.getData().get(0).getShopName());
					user.setText("用户姓名：" + already_list.getData().get(0).getUserName());
					total_money.setText(already_list.getData().get(0).getExpAll());
					to_order.setText("剩余未处理订单数量：" + already_list.getData().get(0).getUDAllOrder() + "个");
					UnDoneNum = already_list.getData().get(0).getUDAllOrder();
					if (!already_list.getData().get(0).getUDAllOrder().equals("0")) {
						to_order.setTextColor(android.graphics.Color.RED);
					} else {
						to_order.setTextColor(android.graphics.Color.GRAY);
					}
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
		tv_submit.setOnClickListener(this);
	}

}
