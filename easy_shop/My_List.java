package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.List_Manage_Adapter;
import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.model.Manage_List;
import com.example.model.Manage_List.Manage;
import com.lidroid.xutils.http.RequestParams;

public class My_List extends BaseActivity {
	private ListView list_shop;
	private List_Manage_Adapter manage_adapter;
	private List<Manage> list = new ArrayList<Manage>();
	private String url = "ESListMy.GetOtherList";
	private RequestParams params;
	private String type;
	private String title;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_list);
		initTitle("·µ»Ø", getIntent().getStringExtra("title").toString(), null);
		init();
		setListner();
		params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance()
				.getShopid().toString());
		params.addBodyParameter("type", getIntent().getStringExtra("type")
				.toString());
		params.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid());
		HttpXutils(this, url, params, Manage_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Manage_List manage_list = (Manage_List) object;
				if (manage_list.getRet().equals("200")) {
					list.addAll(manage_list.getData());
					manage_adapter = new List_Manage_Adapter(
							getApplicationContext(), list);
					list_shop.setAdapter(manage_adapter);
				} else {
					// Toast.makeText(context, "0", 1).show();
				}

			}

			@Override
			public void onFailure(String code, String info) {
				// TODO Auto-generated method stub

			}
		});
		list_shop.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				type = list.get(position).getGototype();
				title = list.get(position).getShowname();
				if (list.get(position).getJumpto().equals("GLIST")) {
					bundle.putString("type", type);
					bundle.putString("title", title);
//					startActivity(Shop_List_Two.class, bundle);
				} else if (list.get(position).getJumpto().equals("GWAITLIST")) {
					bundle.putString("type", type);
					bundle.putString("title", title);
//					startActivity(Shop_List_Two.class, bundle);

				} else if (list.get(position).getJumpto().equals("GLOWLIST")) {
					bundle.putString("type", type);
					bundle.putString("title", title);
//					startActivity(Shop_List_Two.class, bundle);
				} else if (list.get(position).getJumpto().equals("GOODSEARCH")) {
					bundle.putString("type", type);
					bundle.putString("title", title);
//					startActivity(Shop_List_Two.class, bundle);
				}
			}
		});
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
		list_shop = (ListView) findViewById(R.id.list_shop);
	}

	@Override
	protected void setListner() {

	}

}