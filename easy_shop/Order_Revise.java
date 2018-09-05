package com.example.easy_shop;

import com.example.model.Products_List;
import com.lidroid.xutils.http.RequestParams;

import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Order_Revise extends BaseActivity {
	private TextView tv_order_number;
	private TextView tv_code_number;
	private EditText et_food_name;
	private EditText et_number;
	private EditText et_univalent;
	private EditText et_total;
	private EditText et_other;
	private Button tv_submit;
	private String url = "ESOrderInInfo.ChangeInfo";
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_revise);
		initTitle("返回", "修改入库单", null);
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
			params = new RequestParams();
			params.addBodyParameter("barcode",
					getIntent().getStringExtra("barcode"));
			params.addBodyParameter("number", et_number.getText().toString());
			params.addBodyParameter("price", et_univalent.getText().toString());
			params.addBodyParameter("order", getIntent()
					.getStringExtra("order"));
			params.addBodyParameter("other", et_other.getText().toString());
			params.addBodyParameter("count", et_total.getText().toString());
			params.addBodyParameter("goodname", et_food_name.getText()
					.toString());
			params.addBodyParameter("reqid", CommApplication.getInstance()
					.getUserid());
			HttpXutils(this, url, params, Products_List.class,
					new ServerResult() {

						@Override
						public void onSuccess(Object object) {
							Products_List products_list = (Products_List) object;
							if (products_list.getRet().equals("200")) {
//								Toast.makeText(context, "修改成功", 1).show();
								finish();
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
		tv_order_number = (TextView) findViewById(R.id.tv_order_number);
		tv_code_number = (TextView) findViewById(R.id.tv_code_number);
		et_food_name = (EditText) findViewById(R.id.et_food_name);
		et_number = (EditText) findViewById(R.id.et_number);
		et_univalent = (EditText) findViewById(R.id.et_univalent);
		et_total = (EditText) findViewById(R.id.et_total);
		et_other = (EditText) findViewById(R.id.et_other);
		tv_submit = (Button) findViewById(R.id.tv_submit);
		tv_order_number.setText("订单号：" + getIntent().getStringExtra("order"));
		tv_code_number.setText("条码号：" + getIntent().getStringExtra("barcode"));
		et_food_name.setText(getIntent().getStringExtra("goodname"));
		et_univalent.setText(getIntent().getStringExtra("price"));
		et_total.setText(getIntent().getStringExtra("count"));
		et_other.setText(getIntent().getStringExtra("other"));
		et_number.setText(getIntent().getStringExtra("number"));
		et_food_name.setCursorVisible(false);
		et_food_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				et_food_name.setCursorVisible(true);
			}
		});
		// CharSequence charSequence = et_food_name.getText();
		// if (charSequence instanceof Spannable) {
		// Spannable spanText = (Spannable) charSequence;
		// Selection.setSelection(spanText, charSequence.length());
		// }

	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
	}

}
