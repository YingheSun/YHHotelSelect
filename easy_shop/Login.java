package com.example.easy_shop;

import java.util.UUID;

import com.example.model.Register_Model;
import com.lidroid.xutils.http.RequestParams;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends BaseActivity {
	private TextView tv_login;
	private TextView tv_login_forget;
	private ImageView iv_login_icon;
	private EditText et_login_phone;
	private EditText et_login_password;
	public static final String PREFS_NAME = "MyPrefsFile";
	private String url = "ESLogin.ESUserLogin";
	private String UUID;
	private RequestParams params = new RequestParams();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
		setListner();
		initScanArea();
	}

	public void initScanArea() {
		CommApplication.getInstance().setScanAreaType("QRType");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_login:
			UUID = getMyUUID();
			params.addBodyParameter("phonenum", et_login_phone.getText().toString());
			params.addBodyParameter("password", et_login_password.getText().toString());
			params.addBodyParameter("uuid", UUID);
			HttpXutils(this, url, params, Register_Model.class, new ServerResult() {
				@Override
				public void onSuccess(Object object) {
					Register_Model register_mode = (Register_Model) object;
					if (register_mode.getRet().equals("200")) {
						CommApplication.getInstance().setUserid(register_mode.getData().get(0).getId());
						CommApplication.getInstance().setShopid(register_mode.getData().get(0).getBandto());
						SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
						SharedPreferences.Editor editor = settings.edit();
						editor.putString("phonenum", et_login_phone.getText().toString());
						editor.putString("password", et_login_password.getText().toString());
						editor.putString("userid", register_mode.getData().get(0).getId());
						editor.putString("requetUrl", httpstr);
						editor.commit();
						finish();
						startActivity(MainActivity.class);
					} else if (register_mode.getRet().equals("103")) {
						startActivity(Register.class);
					}

				}

				@Override
				public void onFailure(String code, String info) {
					Toak(Login.this, "µÇÂ½Ê§°Ü", 1);
				}
			});

			break;
		case R.id.tv_login_forget:
			startActivity(Register.class);
			break;
		}
	}

	@Override
	protected void init() {
		tv_login = (TextView) findViewById(R.id.tv_login);
		tv_login_forget = (TextView) findViewById(R.id.tv_login_forget);
		et_login_phone = (EditText) findViewById(R.id.et_login_phone);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		iv_login_icon = (ImageView) findViewById(R.id.iv_login_icon);
		iv_login_icon.setImageResource(R.drawable.jianyun);

	}

	private String getMyUUID() {
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(),
				android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();
		return uniqueId;
	}

	@Override
	protected void setListner() {
		tv_login.setOnClickListener(this);
		tv_login_forget.setOnClickListener(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

}
