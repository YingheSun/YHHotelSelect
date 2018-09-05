package com.example.easy_shop;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

import com.example.model.Products_List;
import com.example.model.Register_Model;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class RegisterNext extends BaseActivity {
	private Button register;
	private EditText password;
	private String url = "ESRegister.UserAdd";
	private String user_name;
	private String id;
	private String bandto;
	private RequestParams params = new RequestParams();
	private String UUID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registernext);
		initTitle("返回", "注册", null);
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			this.finish();
			break;
		case R.id.register:
			UUID = getMyUUID();
			params.addBodyParameter("phonenum",
					getIntent().getStringExtra("phone_number"));
			params.addBodyParameter("username", "新用户");
			params.addBodyParameter("password", password.getText().toString());
			params.addBodyParameter("uuid", UUID);
			
			HttpXutils(this, url, params, Register_Model.class,
					new ServerResult() {
						@Override
						public void onSuccess(Object object) {
							Register_Model register_mode = (Register_Model) object;
							if (register_mode.getRet().equals("200")) {
								user_name = register_mode.getData().get(0)
										.getUser_name();
								id = register_mode.getData().get(0).getId();
								bandto = register_mode.getData().get(0)
										.getBandto();
								Toak(RegisterNext.this, "注册成功", 1);

								startActivity(MainActivity.class);
							} else {
								Toak(RegisterNext.this, register_mode.getMsg(),
										1);
							}
						}

						@Override
						public void onFailure(String code, String info) {

						}
					});
			break;
		}
	}

	private String getMyUUID() {
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());     
		String uniqueId = deviceUuid.toString(); 
		return uniqueId;
	}
	
	@Override
	protected void init() {
		register = (Button) findViewById(R.id.register);
		password = (EditText) findViewById(R.id.password);
	}

	@Override
	protected void setListner() {
		register.setOnClickListener(this);
	}
}
