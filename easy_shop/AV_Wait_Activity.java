package com.example.easy_shop;

import com.example.model.Good_model;
import com.example.qr_codescan.MipcaActivityCapture;
import com.lidroid.xutils.http.RequestParams;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AV_Wait_Activity extends BaseActivity {
	private EditText shop_name, cost_price, lowest_price, selling_price;
	private TextView code_number, code_number2, ensure_result;
	private Button tv_submit, bt_ensure;
	private String url = "ESGoodWait.GetGoodInfo";
	private String sub_url = "ESGoodWait.SetGoodInfo";
	private String id;
	private String scanone, scantwo, scanthree, scanfour;
	private String scanmode = "one";
	private String passflag = "NO";
	private int scantime;
	private RequestParams params;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.av_wait_commodity);
		initTitle("����", "��Ʒ�����޸�", "��������");
		init();
		setListner();
		params = new RequestParams();
		params.addBodyParameter("id", getIntent().getStringExtra("id").toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid().toString());
		HttpXutils(this, url, params, Good_model.class, new ServerResult() {
			@Override
			public void onSuccess(Object object) {
				Good_model good_list = (Good_model) object;
				if (good_list.getRet().equals("200")) {
					id = good_list.getData().get(0).getId();
					code_number.setText(good_list.getData().get(0).getBarcode());
					shop_name.setText(good_list.getData().get(0).getGoods_name());
					cost_price.setText(good_list.getData().get(0).getCost());
					lowest_price.setText(good_list.getData().get(0).getLowprice());
					selling_price.setText(good_list.getData().get(0).getPrice());
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
		case R.id.bt_ensure:
			bundle.putString("title", "OTHER");
			bundle.putString("scanType", "one");
			bundle.putString("returnable", "YES");
			bundle.putString("returnType", "immediately");
			CommApplication.getInstance().setScanAreaType("barcodeType");
			startActivity(MipcaActivityCapture.class, bundle);
			break;
		case R.id.tv_titlebar_right:
			scanmode = "four";
			scantime = 0;
			passflag = "NO";
			bt_ensure.setVisibility(View.VISIBLE);
			bt_ensure.setText("��һ��");
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
		tv_submit = (Button) findViewById(R.id.tv_submit);
		bt_ensure = (Button) findViewById(R.id.bt_ensure);
		bt_ensure.setText("��֤");
		code_number2 = (TextView) findViewById(R.id.code_number2);
		ensure_result = (TextView) findViewById(R.id.ensure_result);

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onResume();
		if (scanmode.equals("one")) {
			code_number2.setText(CommApplication.getInstance().getScanReturn().toString());
			getscanCheck();
		} else if (scantime < 4) {
			if (scantime == 0) {
				scanone = CommApplication.getInstance().getScanReturn().toString();
				code_number.setText(CommApplication.getInstance().getScanReturn().toString());
				scantime++;
				code_number.setTextColor(getResources().getColor(R.color.blue));
				code_number2.setTextColor(getResources().getColor(R.color.blue));
				code_number2.setText("");
				bt_ensure.setText("�ڶ���");
			} else if (scantime == 1) {
				scantwo = CommApplication.getInstance().getScanReturn().toString();
				code_number2.setText(CommApplication.getInstance().getScanReturn().toString());
				bt_ensure.setText("������");
				scantime++;
				getscanCheck();
			} else if (scantime == 2) {
				scanthree = CommApplication.getInstance().getScanReturn().toString();
				code_number2.setText(CommApplication.getInstance().getScanReturn().toString());
				bt_ensure.setText("���Ĵ�");
				scantime++;
				getscanCheck();
			} else if (scantime == 3) {
				scanfour = CommApplication.getInstance().getScanReturn().toString();
				code_number2.setText(CommApplication.getInstance().getScanReturn().toString());
				scantime++;
				getscanCheck();
			}
			
		} else if(scantime >= 4) {
			passflag = "YES";
			bt_ensure.setVisibility(View.GONE);
		}
	}

	public void getscanCheck() {
		if (code_number.getText().toString().equals(code_number2.getText().toString())) {
			ensure_result.setText("���һ��");
			ensure_result.setTextColor(getResources().getColor(R.color.blue));
			if (scanmode.equals("one")) {
				passflag = "YES";
				bt_ensure.setVisibility(View.GONE);
			} else if (scantime == 1){
				code_number2.setText("");
			}else if(scantime == 4){
				passflag = "YES";
				bt_ensure.setVisibility(View.GONE);
			}
		} else {
			ensure_result.setText("��ⲻһ��");
			ensure_result.setTextColor(getResources().getColor(R.color.red));
			scanmode = "four";
			Toast.makeText(this, "�����һ�£�����������գ�������ɨ��ȷ��", 1).show();
			code_number.setText("ɨ�������");
			code_number.setTextColor(getResources().getColor(R.color.red));
			code_number2.setText("����ȷɨ�Ĵ�ȷ��");
			code_number2.setTextColor(getResources().getColor(R.color.red));
			ensure_result.setText("");
			ensure_result.setTextColor(getResources().getColor(R.color.red));
			bt_ensure.setText("��һ��");
			scantime = 0;
		}
	}

	@Override
	protected void setListner() {
		tv_submit.setOnClickListener(this);
		bt_ensure.setOnClickListener(this);
	}

	public void sub_Http() {
		params = new RequestParams();
		params.addBodyParameter("goodid", id);
		params.addBodyParameter("barcode", code_number.getText().toString());
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
					Toast.makeText(getApplicationContext(), "�޸ĳɹ�", 1).show();
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

}
