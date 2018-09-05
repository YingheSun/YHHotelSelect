package com.example.easy_shop;

import com.example.model.Products_List;
import com.lidroid.xutils.http.RequestParams;
import com.mining.app.zxing.decoding.CaptureActivityHandler;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Money_Count extends BaseActivity {
	private EditText et_total_money, et_pay_money;
	private TextView tv_odd_change;
	private Button tv_submit;
	private double total, pay;
	private RequestParams params_submit;
	private String order;
	private String url_submit = "ESCommitIn.CommitOrder";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.money_count);
		initTitle("返回", "确认入库", null);
		init();
		et_total_money.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				et_total_money.setCursorVisible(true);
			}
		});
		setListner();
		setUnWriteListener();
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
			params_submit.addBodyParameter("shopid", CommApplication
					.getInstance().getShopid().toString());
			params_submit.addBodyParameter("storeid", getIntent()
					.getStringExtra("storeid").toString());
			params_submit.addBodyParameter("userid", CommApplication
					.getInstance().getUserid());
			params_submit.addBodyParameter("reqid", CommApplication
					.getInstance().getUserid());
			params_submit.addBodyParameter("count",
					getIntent().getStringExtra("total").toString());
			params_submit.addBodyParameter("expense", "-"+et_total_money.getText()
					.toString());
			params_submit.addBodyParameter("moneyin", tv_odd_change.getText()
					.toString());
			params_submit.addBodyParameter("moneyout", et_pay_money.getText()
					.toString());
			HttpXutils(this, url_submit, params_submit, Products_List.class,
					new ServerResult() {

						@Override
						public void onSuccess(Object object) {
							Products_List products_list = (Products_List) object;
							if (products_list.getRet().equals("200")) {
								Toak(Money_Count.this, "商品入库成功", 1);
								startActivity(MainActivity.class);
							} else {
								Toast.makeText(getApplicationContext(),
										products_list.getMsg().toString(), 1)
										.show();
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
		et_total_money = (EditText) findViewById(R.id.et_total_money);
		et_pay_money = (EditText) findViewById(R.id.et_pay_money);
		tv_odd_change = (TextView) findViewById(R.id.tv_odd_change);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		et_total_money.setText(getIntent().getStringExtra("total"));
		total = Double.parseDouble(et_total_money.getText().toString());
		pay = Double.parseDouble(et_pay_money.getText().toString());
		tv_odd_change.setText(Double.toString(pay - total));
		et_total_money.setCursorVisible(false);
		order = getIntent().getStringExtra("order");
	}

	private void setUnWriteListener() {
		et_total_money.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				// Toast.makeText(context, "onTextChanged", 1).show();

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (et_total_money.getText().toString().length() == 0) {
					total = Double.parseDouble("0.0");
					pay = Double.parseDouble(et_pay_money.getText().toString());
					tv_odd_change.setText(Double.toString(pay - total));
				} else {
					total = Double.parseDouble(et_total_money.getText()
							.toString());
					pay = Double.parseDouble(et_pay_money.getText().toString());
					tv_odd_change.setText(Double.toString(pay - total));
				}
			}
		});

		et_pay_money.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				// Toast.makeText(context, "onTextChanged", 1).show();

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (et_pay_money.getText().toString().length() == 0) {
					total = Double.parseDouble(et_total_money.getText()
							.toString());
					pay = Double.parseDouble("0.0");
					tv_odd_change.setText(Double.toString(pay - total));
				} else {
					total = Double.parseDouble(et_total_money.getText()
							.toString());
					pay = Double.parseDouble(et_pay_money.getText().toString());
					tv_odd_change.setText(Double.toString(pay - total));
				}
			}
		});

	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
	}

}
