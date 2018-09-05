package com.example.easy_shop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Already_List;
import com.example.model.Good_model;
import com.example.qr_codescan.MipcaActivityCapture;
import com.lidroid.xutils.http.RequestParams;

public class Detailed_Commodity extends BaseActivity {
	private TextView shop_name, cost_price, lowest_price, selling_price, tv_ground, tv_state;
	private EditText code_number;
	private Button tv_submit;
	private String url = "ESPLTGoodInfo.GetInfo";
	private RequestParams params;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailed_commodity);
		initTitle("返回", "商品详细", "扫码");
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_titlebar_right:
			bundle.putString("title", "OTHER");
			bundle.putString("scanType", "one");
			bundle.putString("returnable", "YES");
			bundle.putString("returnType", "immediately");
			CommApplication.getInstance().setScanAreaType("barcodeType");
			startActivity(MipcaActivityCapture.class, bundle);
			break;
		case R.id.tv_submit:
			params = new RequestParams();
			params.addBodyParameter("barcode", code_number.getText().toString());
			params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
			params.addBodyParameter("reqid", CommApplication.getInstance().getUserid().toString());
			HttpXutils(this, url, params, Good_model.class, new ServerResult() {
				@Override
				public void onSuccess(Object object) {
					Good_model good_list = (Good_model) object;
					if (good_list.getRet().equals("200")) {
						shop_name.setText("商品名称：" + good_list.getData().get(0).getGoods_name());
						cost_price.setText("进价：" + good_list.getData().get(0).getCost());
						lowest_price.setText("最低定价：" + good_list.getData().get(0).getLowprice());
						selling_price.setText("售价：" + good_list.getData().get(0).getPrice());
						String State = "未上架";
						String MState = "尚未维护";
						if (good_list.getData().get(0).getOnsellingflag().equals("3")) {
							State = "上架销售中";
							tv_ground.setTextColor(getResources().getColor(R.color.blue));
						} else {
							tv_ground.setTextColor(getResources().getColor(R.color.red));
						}
						if (good_list.getData().get(0).getManagetype().equals("done")) {
							MState = "已维护";
							tv_state.setTextColor(getResources().getColor(R.color.blue));
						} else {
							tv_state.setTextColor(getResources().getColor(R.color.red));
						}
						tv_ground.setText("上架状态：" + State);
						tv_state.setText("信息維護状态：" + MState);
					} else {
						Toast.makeText(getApplicationContext(), good_list.getMsg(), 1).show();
					}
				}

				@Override
				public void onFailure(String code, String info) {

				}
			});

			break;
		}
	}

	@Override
	protected void init() {
		code_number = (EditText) findViewById(R.id.code_number);
		shop_name = (TextView) findViewById(R.id.shop_name);
		cost_price = (TextView) findViewById(R.id.cost_price);
		lowest_price = (TextView) findViewById(R.id.lowest_price);
		selling_price = (TextView) findViewById(R.id.selling_price);
		tv_ground = (TextView) findViewById(R.id.tv_ground);
		tv_state = (TextView) findViewById(R.id.tv_state);
		tv_submit = (Button) findViewById(R.id.tv_submit);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onResume();
		code_number.setText(CommApplication.getInstance().getScanReturn().toString());
	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
	}

}
