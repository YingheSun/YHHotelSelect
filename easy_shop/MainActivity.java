package com.example.easy_shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Commodity_Information;
import com.example.qr_codescan.MipcaActivityCapture;
import com.lidroid.xutils.http.RequestParams;

public class MainActivity extends BaseActivity {
	private String url = "ESShop.GetShopInfo";
	private TextView tv_card;
	private TextView tv_cstore;
	private TextView tv_ku;
	private TextView tv_shou;
	private TextView tv_guan;
	private TextView tv_zhi;
	private TextView tv_my;
	private TextView tv_shopheader;
	private long firstTime = 0;
	private Bundle bundle = new Bundle();
	private RequestParams params = new RequestParams();
	private String customerFlag;
	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getShopInfo();
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_card:
			if(customerFlag.equals("YES")){
				Toak(MainActivity.this, "您目前为访客模式，没有获得该权限，请联系客服，或使用具有该权限的账户登录", 0);
			}else{
				bundle.putString("restart", "NO");
				bundle.putString("title", "CARDIN");
				bundle.putString("scanType", "one");
				bundle.putString("returnable", "NO");
				bundle.putString("returnType", "immediately");
				CommApplication.getInstance().setScanAreaType("QRType");
				startActivity(MipcaActivityCapture.class, bundle);
			}
			
			break;
		case R.id.tv_ku:
			// bundle.putString("title", "STOREIN");
			// bundle.putString("scanType", "complex");
			// bundle.putString("returnable", "NO");
			// bundle.putString("returnType", "sustained");
			// CommApplication.getInstance().setScanAreaType("barcodeType");
			// startActivity(MipcaActivityCapture.class, bundle);
			if(customerFlag.equals("YES")){
				Toak(MainActivity.this, "您目前为访客模式，没有获得该权限，请联系客服，或使用具有该权限的账户登录", 0);
			}else{
			startActivity(Choice_Warehouse.class);
			}
			break;
		case R.id.tv_shou:
			if(customerFlag.equals("YES")){
				Toak(MainActivity.this, "您目前为访客模式，没有获得该权限，请联系客服，或使用具有该权限的账户登录", 0);
			}else{
			bundle.putString("restart", "NO");
			bundle.putString("title", "SELLING");
			bundle.putString("scanType", "complex");
			bundle.putString("returnable", "NO");
			bundle.putString("returnType", "sustained");
			CommApplication.getInstance().setScanAreaType("barcodeType");
			startActivity(MipcaActivityCapture.class, bundle);
			}
			break;
		case R.id.tv_cstore:
			// bundle.putString("title", "CHANGESTORE");
			// bundle.putString("scanType", "complex");
			// bundle.putString("returnable", "NO");
			// bundle.putString("returnType", "sustained");
			// CommApplication.getInstance().setScanAreaType("barcodeType");
			// startActivity(MipcaActivityCapture.class, bundle);
			if(customerFlag.equals("YES")){
				Toak(MainActivity.this, "您目前为访客模式，没有获得该权限，请联系客服，或使用具有该权限的账户登录", 0);
			}else{
			startActivity(Choice_Out_Warehouse.class);
			}
			break;
		case R.id.tv_guan:
			if(customerFlag.equals("YES")){
				Toak(MainActivity.this, "您目前为访客模式，没有获得该权限，请联系客服，或使用具有该权限的账户登录", 0);
			}else{
			startActivity(Manage_Activity.class);
			}
			break;
		case R.id.tv_zhi:
			if(customerFlag.equals("YES")){
				Toak(MainActivity.this, "您目前为访客模式，没有获得该权限，请联系客服，或使用具有该权限的账户登录", 0);
			}else{
			startActivity(Set_Up_Activity.class);
			}
			break;
		case R.id.tv_my:
			if(customerFlag.equals("YES")){
				Toak(MainActivity.this, "您目前为访客模式，没有获得该权限，请联系客服，或使用具有该权限的账户登录", 0);
			}else{
				startActivity(My_Activity.class);
			}
			break;
		}
	}

	protected void getShopInfo() {
		params.addBodyParameter("id", CommApplication.getInstance().getShopid());
		HttpXutils(this, url, params, Commodity_Information.class, new ServerResult() {
			@Override
			public void onSuccess(Object object) {
				Commodity_Information shop_Model = (Commodity_Information) object;
				if (shop_Model.getRet().equals("200")) {
					// Toak(MainActivity.this, "欢迎来到"
					// + shop_Model.getData().get(0)
					// .getShop_name(), 0);
					tv_shopheader.setText(shop_Model.getData().get(0).getShop_name());
					customerFlag = "NO";
				} else {
					Toak(MainActivity.this, shop_Model.getMsg(), 0);
					tv_shopheader.setText("访客模式:您尚未存在于任何的店铺当中，如果想要注册商店信息，请联系客服");
					tv_shopheader.setTextColor(getResources().getColor(R.color.red));
					tv_shopheader.setTextSize(15.0f);
					customerFlag = "YES";
//					SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//					SharedPreferences.Editor editor = settings.edit();
//					editor.clear();
//					editor.commit();
				}
			}

			@Override
			public void onFailure(String code, String info) {
				// Toak(Login.this, "登陆失败", 1);
			}
		});

	}

	@Override
	protected void init() {
		tv_card = (TextView) findViewById(R.id.tv_card);
		tv_cstore = (TextView) findViewById(R.id.tv_cstore);
		tv_ku = (TextView) findViewById(R.id.tv_ku);
		tv_shou = (TextView) findViewById(R.id.tv_shou);
		tv_guan = (TextView) findViewById(R.id.tv_guan);
		tv_zhi = (TextView) findViewById(R.id.tv_zhi);
		tv_my = (TextView) findViewById(R.id.tv_my);
		tv_shopheader = (TextView) findViewById(R.id.tv_shopheader);
	}

	@Override
	protected void setListner() {
		tv_card.setOnClickListener(this);
		tv_cstore.setOnClickListener(this);
		tv_ku.setOnClickListener(this);
		tv_shou.setOnClickListener(this);
		tv_guan.setOnClickListener(this);
		tv_zhi.setOnClickListener(this);
		tv_my.setOnClickListener(this);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long secondTime = System.currentTimeMillis();
			if (secondTime - firstTime > 2000) {
				Toast.makeText(MainActivity.this, "再次按返回键退出程序", Toast.LENGTH_SHORT).show();
				firstTime = secondTime;
				return true;
			} else {
				System.exit(0);
			}
		}
		return super.onKeyUp(keyCode, event);
	}

}
