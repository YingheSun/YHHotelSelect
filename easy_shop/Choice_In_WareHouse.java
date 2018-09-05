package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.List_Already_Adapter;
import com.example.model.Already_List;
import com.example.model.Already_List.Already;
import com.example.qr_codescan.MipcaActivityCapture;
import com.lidroid.xutils.http.RequestParams;

public class Choice_In_WareHouse extends BaseActivity {

	private ListView list_already;
	private String url_already = "ESStoreSel.ExtraStoreList";
	private RequestParams params_already;
	private List_Already_Adapter already_adapter;
	private List<Already> list = new ArrayList<Already>();
	private String storeid;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_warehouse);
		initTitle("返回", "选择入库库房", "");
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;

		}
	}

	@Override
	protected void init() {
		Toast.makeText(context, "请选择您要入库的库房", 1).show();
		list_already = (ListView) findViewById(R.id.list_already);
		params_already = new RequestParams();
		params_already.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params_already.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		params_already.addBodyParameter("outstoreid", getIntent().getStringExtra("OutStore"));
		HttpXutils(this, url_already, params_already, Already_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Already_List already_list = (Already_List) object;
				if (already_list.getRet().equals("200")) {
					list.addAll(already_list.getData());
					already_adapter = new List_Already_Adapter(getApplicationContext(), list);
					list_already.setAdapter(already_adapter);
				} else {
					
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
		list_already.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				storeid = list.get(position).getId();
				bundle.putString("title", "CHANGINGSTORE");
				bundle.putString("restart", "NO");
				bundle.putString("scanType", "complex");
				bundle.putString("returnable", "NO");
				bundle.putString("returnType", "sustained");
				bundle.putString("OutStore", getIntent().getStringExtra("OutStore"));
				bundle.putString("InStore", storeid);
				CommApplication.getInstance().setScanAreaType("barcodeType");
				startActivity(MipcaActivityCapture.class, bundle);

			}
		});

	}

	@Override
	protected void setListner() {

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		params_already = new RequestParams();
		params_already.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params_already.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		params_already.addBodyParameter("state", "using");
		HttpXutils(this, url_already, params_already, Already_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Already_List already_list = (Already_List) object;
				if (already_list.getRet().equals("200")) {
					list.clear();
					list.addAll(already_list.getData());
					already_adapter = new List_Already_Adapter(getApplicationContext(), list);
					list_already.setAdapter(already_adapter);
				} else {

				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});

	}
}
