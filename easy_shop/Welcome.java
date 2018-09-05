package com.example.easy_shop;

import java.util.UUID;

import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Register_Model;
import com.lidroid.xutils.http.RequestParams;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;

public class Welcome extends BaseActivity {
	public static final String PREFS_NAME = "MyPrefsFile";
	private String url = "ESLogin.ESQuickLogin";
	private String userid;
	private String uuid;
	private Handler handler = new Handler();
	private RequestParams params = new RequestParams();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wel_come);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		userid = settings.getString("userid", "");
		uuid = getMyUUID();
		params.addBodyParameter("userid", userid);
		params.addBodyParameter("uuid", uuid);
		HttpXutils(this, url, params, Register_Model.class, new ServerResult() {
			@Override
			public void onSuccess(Object object) {
				Register_Model register_mode = (Register_Model) object;
				if (register_mode.getRet().equals("200")) {
					CommApplication.getInstance().setUserid(register_mode.getData().get(0).getId());
					CommApplication.getInstance().setShopid(register_mode.getData().get(0).getBandto());
					SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("requetUrl", httpstr);
					editor.commit();
					handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							startActivity(MainActivity.class);
							finish();
						}
					}, 800);

				} else {
					handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							startActivity(Login.class);
							finish();
						}
					}, 800);

				}
			}

			@Override
			public void onFailure(String code, String info) {
				// Toak(Login.this, "µÇÂ½Ê§°Ü", 1);
			}
		});
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
	public void onClick(View v) {

	}

	@Override
	protected void init() {

	}

	@Override
	protected void setListner() {

	}

}
