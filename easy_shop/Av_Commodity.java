package com.example.easy_shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Good_model;
import com.example.qr_codescan.MipcaActivityCapture;
import com.lidroid.xutils.http.RequestParams;

public class Av_Commodity extends BaseActivity {
	private EditText shop_name, cost_price, lowest_price, selling_price;
	private TextView code_number, tv_ground, tv_state;
	private Button tv_submit, bt_onsale;
	private String url = "ESPLTGoodInfo.GetInfo";
	private String on_url = "ESSaleFlag.MakeGoodOnSale";
	private String off_url = "ESSaleFlag.MakeGoodOffSale";
	private String sub_url = "ESGoodManage.SetGoodInfo";
	private String id, flag;
	private RequestParams params;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.av_commodity);
		initTitle("返回", "商品详情修改", "");
		init();
		setListner();
		params = new RequestParams();
		params.addBodyParameter("barcode", getIntent().getStringExtra("barcode").toString());
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid().toString());
		HttpXutils(this, url, params, Good_model.class, new ServerResult() {
			@Override
			public void onSuccess(Object object) {
				Good_model good_list = (Good_model) object;
				if (good_list.getRet().equals("200")) {
					id = good_list.getData().get(0).getId();
					flag = good_list.getData().get(0).getOnsellingflag();
					shop_name.setText(good_list.getData().get(0).getGoods_name());
					cost_price.setText(good_list.getData().get(0).getCost());
					lowest_price.setText(good_list.getData().get(0).getLowprice());
					selling_price.setText(good_list.getData().get(0).getPrice());
					String State = "未上架";
					String MState = "尚未维护";
					if (good_list.getData().get(0).getOnsellingflag().equals("3")) {
						State = "已下架";
						tv_ground.setTextColor(getResources().getColor(R.color.red));
						bt_onsale.setText("上架");
						bt_onsale.setTextColor(getResources().getColor(R.color.blue));
					} else if (good_list.getData().get(0).getOnsellingflag().equals("2")) {
						State = "上架中";
						tv_ground.setTextColor(getResources().getColor(R.color.blue));
						bt_onsale.setText("下架");
						bt_onsale.setTextColor(getResources().getColor(R.color.red));
					} else {
						tv_ground.setTextColor(getResources().getColor(R.color.red));
						bt_onsale.setText("上架");
					}
					if (good_list.getData().get(0).getManagetype().equals("done")) {
						MState = "已维护";
						tv_state.setTextColor(getResources().getColor(R.color.blue));
					} else {
						tv_state.setTextColor(getResources().getColor(R.color.red));
					}
					tv_ground.setText(State);
					tv_state.setText(MState);
				} else {
					Toast.makeText(getApplicationContext(), good_list.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_submit:
			sub_Http();
			break;
		case R.id.bt_onsale:
			changeFlag();
			break;
		}
	}

	@Override
	protected void init() {
		code_number = (TextView) findViewById(R.id.code_number);
		shop_name = (EditText) findViewById(R.id.shop_name);
		cost_price = (EditText) findViewById(R.id.cost_price);
		lowest_price = (EditText) findViewById(R.id.lowest_price);
		selling_price = (EditText) findViewById(R.id.selling_price);
		tv_ground = (TextView) findViewById(R.id.tv_ground);
		tv_state = (TextView) findViewById(R.id.tv_state);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		bt_onsale = (Button) findViewById(R.id.bt_onsale);
		code_number.setText(getIntent().getStringExtra("barcode").toString());
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
		bt_onsale.setOnClickListener(this);
	}

	public void sub_Http() {
		params = new RequestParams();
		params.addBodyParameter("goodid", id);
		params.addBodyParameter("name", shop_name.getText().toString());
		params.addBodyParameter("cost", cost_price.getText().toString());
		params.addBodyParameter("lowprice", lowest_price.getText().toString());
		params.addBodyParameter("price", selling_price.getText().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid().toString());
		HttpXutils(this, sub_url, params, Good_model.class, new ServerResult() {
			@Override
			public void onSuccess(Object object) {
				Good_model good_list = (Good_model) object;
				if (good_list.getRet().equals("200")) {
					Toast.makeText(getApplicationContext(), "修改成功", 1).show();
					finish();
				} else {
					Toast.makeText(getApplicationContext(), good_list.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}

	public void changeFlag() {
		if (flag.equals("2")) {
			params = new RequestParams();
			params.addBodyParameter("goodid", id);
			params.addBodyParameter("reqid", CommApplication.getInstance().getUserid().toString());
			HttpXutils(this, off_url, params, Good_model.class, new ServerResult() {
				@Override
				public void onSuccess(Object object) {
					Good_model good_list = (Good_model) object;
					if (good_list.getRet().equals("200")) {
						id = good_list.getData().get(0).getId();
						flag = good_list.getData().get(0).getOnsellingflag();
						shop_name.setText(good_list.getData().get(0).getGoods_name());
						cost_price.setText(good_list.getData().get(0).getCost());
						lowest_price.setText(good_list.getData().get(0).getLowprice());
						selling_price.setText(good_list.getData().get(0).getPrice());
						String State = "未上架";
						String MState = "尚未维护";
						if (good_list.getData().get(0).getOnsellingflag().equals("3")) {
							State = "已下架";
							tv_ground.setTextColor(getResources().getColor(R.color.red));
							bt_onsale.setText("上架");
							bt_onsale.setTextColor(getResources().getColor(R.color.blue));
						} else if (good_list.getData().get(0).getOnsellingflag().equals("2")) {
							State = "上架中";
							tv_ground.setTextColor(getResources().getColor(R.color.blue));
							bt_onsale.setText("下架");
							bt_onsale.setTextColor(getResources().getColor(R.color.red));
						} else {
							tv_ground.setTextColor(getResources().getColor(R.color.red));
							bt_onsale.setText("上架");
						}
						if (good_list.getData().get(0).getManagetype().equals("done")) {
							MState = "已维护";
							tv_state.setTextColor(getResources().getColor(R.color.blue));
						} else {
							tv_state.setTextColor(getResources().getColor(R.color.red));
						}
						tv_ground.setText(State);
						tv_state.setText(MState);
					} else {
						Toast.makeText(getApplicationContext(), good_list.getMsg(), 1).show();
					}
				}

				@Override
				public void onFailure(String code, String info) {

				}
			});
		} else {
			params = new RequestParams();
			params.addBodyParameter("goodid", id);
			params.addBodyParameter("reqid", CommApplication.getInstance().getUserid().toString());
			HttpXutils(this, on_url, params, Good_model.class, new ServerResult() {
				@Override
				public void onSuccess(Object object) {
					Good_model good_list = (Good_model) object;
					if (good_list.getRet().equals("200")) {
						id = good_list.getData().get(0).getId();
						flag = good_list.getData().get(0).getOnsellingflag();
						shop_name.setText(good_list.getData().get(0).getGoods_name());
						cost_price.setText(good_list.getData().get(0).getCost());
						lowest_price.setText(good_list.getData().get(0).getLowprice());
						selling_price.setText(good_list.getData().get(0).getPrice());
						String State = "未上架";
						String MState = "尚未维护";
						if (good_list.getData().get(0).getOnsellingflag().equals("3")) {
							State = "已下架";
							tv_ground.setTextColor(getResources().getColor(R.color.red));
							bt_onsale.setText("上架");
							bt_onsale.setTextColor(getResources().getColor(R.color.blue));
						} else if (good_list.getData().get(0).getOnsellingflag().equals("2")) {
							State = "上架中";
							tv_ground.setTextColor(getResources().getColor(R.color.blue));
							bt_onsale.setText("下架");
							bt_onsale.setTextColor(getResources().getColor(R.color.red));
						} else {
							tv_ground.setTextColor(getResources().getColor(R.color.red));
							bt_onsale.setText("上架");
						}
						if (good_list.getData().get(0).getManagetype().equals("done")) {
							MState = "已维护";
							tv_state.setTextColor(getResources().getColor(R.color.blue));
						} else {
							tv_state.setTextColor(getResources().getColor(R.color.red));
						}
						tv_ground.setText(State);
						tv_state.setText(MState);
					} else {
						Toast.makeText(getApplicationContext(), good_list.getMsg(), 1).show();
					}
				}

				@Override
				public void onFailure(String code, String info) {

				}
			});
		}
	}

}
