package com.example.easy_shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public abstract class BaseActivity extends Activity implements OnClickListener {
	public Context context;
	public static String httpstr = "http://site.developeresource.com/ESClouds/Public/shop/?service=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
	}

	/**
	 * ����������ʼ������
	 */
	protected abstract void init();

	/**
	 * ����������ʼ������
	 */
	protected abstract void setListner();

	/**
	 * ��ʼ������,����Ҫ�Ұ�ť�����ռ���
	 * 
	 * @param left
	 * @param title
	 * @param right
	 */
	protected void initTitle(String left, String title, String right) {
		TextView titlebar_left = (TextView) findViewById(R.id.tv_titlebar_left);
		TextView titlebar_title = (TextView) findViewById(R.id.tv_titlebar_title);
		TextView titlebar_right = (TextView) findViewById(R.id.tv_titlebar_right);

		if (left == null) {
			titlebar_left.setVisibility(View.INVISIBLE);
		} else {
			titlebar_left.setText(left);
			titlebar_left.setVisibility(View.VISIBLE);
		}
		if (right == null) {
			titlebar_right.setVisibility(View.INVISIBLE);
		} else {
			titlebar_right.setText(right);
			titlebar_right.setVisibility(View.VISIBLE);
		}
		titlebar_title.setText(title);
		titlebar_left.setOnClickListener(this);
		titlebar_right.setOnClickListener(this);
		titlebar_title.setOnClickListener(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}

	public void HttpXutils(final Activity activity, final String url,
			final RequestParams params, final Class<?> c,
			final ServerResult daoResult) {
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.POST, httpstr + url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String result) {
						daoResult.onFailure("0", result);
					}

					@Override
					public void onSuccess(ResponseInfo<String> results) {
						Gson json = new Gson();
						Object o;
						try {
							o = c.newInstance();
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						o = json.fromJson(results.result, c);
						daoResult.onSuccess(o);

					}
				});
	}

	public interface ServerResult {
		public void onFailure(String code, String info);

		public void onSuccess(Object object);
	}

	public void Toak(final Activity activity, final String str, final int lin) {
		Toast.makeText(activity, str, lin).show();
	}

	public void startActivity(Class target) {
		Intent intent = new Intent(this, target);
		startActivity(intent);
	}

	/**
	 * �����µ�Activity������һ���Ĳ���
	 */
	public void startActivity(Class<?> target, Bundle bundle) {
		Intent intent = new Intent(this, target);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
