package com.example.easy_shop;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Products_List;
import com.example.model.Sales_List;
import com.lidroid.xutils.http.RequestParams;

public class Money_Out_Count extends BaseActivity {
	private EditText et_pay_money, et_pocket_money;
	private TextView et_total_money, tv_odd_change;
	private Button tv_submit;
	private double total, pay, pocket;
	private RequestParams params_submit;
	private String order;
	private String url_submit = "ESCommitOut.CommitOrder";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.money_out_count);
		initTitle("返回", "确认出库", null);
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
//			params_submit.addBodyParameter("storeid", "9");
			params_submit.addBodyParameter("userid", CommApplication
					.getInstance().getUserid());
			params_submit.addBodyParameter("reqid", CommApplication
					.getInstance().getUserid());
			params_submit.addBodyParameter("count",
					getIntent().getStringExtra("total").toString());
			params_submit.addBodyParameter("expense", et_pocket_money.getText()
					.toString());
			params_submit.addBodyParameter("moneyin", et_pay_money.getText()
					.toString());
			params_submit.addBodyParameter("moneyout", tv_odd_change.getText()
					.toString());
			HttpXutils(this, url_submit, params_submit, Sales_List.class,
					new ServerResult() {

						@Override
						public void onSuccess(Object object) {
							Sales_List sales_list = (Sales_List) object;
							if (sales_list.getRet().equals("200")) {
								Toak(Money_Out_Count.this, "商品出库成功", 1);
								startActivity(MainActivity.class);
							} else {
								Toast.makeText(getApplicationContext(),
										sales_list.getMsg().toString(), 1)
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
		et_total_money = (TextView) findViewById(R.id.et_total_money);
		et_pocket_money = (EditText) findViewById(R.id.et_pocket_money);
		et_pay_money = (EditText) findViewById(R.id.et_pay_money);
		tv_odd_change = (TextView) findViewById(R.id.tv_odd_change);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		et_pocket_money.setText(getIntent().getStringExtra("total"));
		et_total_money.setText(getIntent().getStringExtra("total"));
		total = Double.parseDouble(et_total_money.getText().toString());
		pocket = Double.parseDouble(et_pocket_money.getText().toString());
		pay = Double.parseDouble(et_pay_money.getText().toString());
		tv_odd_change.setText(Double.toString(pay - pocket));
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
					pocket = Double.parseDouble(et_pocket_money.getText()
							.toString());
					tv_odd_change.setText(Double.toString(pay - pocket));
				} else {
					total = Double.parseDouble(et_total_money.getText()
							.toString());
					pay = Double.parseDouble(et_pay_money.getText().toString());
					pocket = Double.parseDouble(et_pocket_money.getText()
							.toString());
					tv_odd_change.setText(Double.toString(pay - pocket));
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
					pocket = Double.parseDouble(et_pocket_money.getText()
							.toString());
					tv_odd_change.setText(Double.toString(pay - pocket));
				} else {
					total = Double.parseDouble(et_total_money.getText()
							.toString());
					pay = Double.parseDouble(et_pay_money.getText().toString());
					pocket = Double.parseDouble(et_pocket_money.getText()
							.toString());
					tv_odd_change.setText(Double.toString(pay - pocket));
				}
			}
		});

	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
	}

}
