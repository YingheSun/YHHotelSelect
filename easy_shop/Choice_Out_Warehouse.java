package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.List_Already_Adapter;
import com.example.easy_shop.R.style;
import com.example.model.Already_List;
import com.example.model.Already_List.Already;
import com.example.model.Default_Store;
import com.example.qr_codescan.MipcaActivityCapture;
import com.example.view.Choice_Select_dialog;
import com.example.view.Scrap_dialog;
import com.example.view.Select_dialog;
import com.lidroid.xutils.http.RequestParams;

public class Choice_Out_Warehouse extends BaseActivity {

	private ListView list_already;
	private String url_already = "ESStoreSel.GetStoreList";
	private RequestParams params_already;
	private List_Already_Adapter already_adapter;
	private List<Already> list = new ArrayList<Already>();
	private String storeid;
	private Bundle bundle = new Bundle();
	private Choice_Select_dialog select_dialog;
	private TextView tv_confirm, tv_cancel;
	private ImageView image_dialog_delete;
	private RadioGroup radio_group;
	private RadioButton radio_one;
	private RadioButton radio_two;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_warehouse);
		initTitle("返回", "选择出库库房", "");
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
		Toast.makeText(context, "请选择您要转库的库房", 1).show();
		list_already = (ListView) findViewById(R.id.list_already);
		params_already = new RequestParams();
		params_already.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params_already.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		params_already.addBodyParameter("state", "using");
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
				select_dialog = new Choice_Select_dialog(Choice_Out_Warehouse.this, style.phonedialog);
				select_dialog.show();
				image_dialog_delete = (ImageView) select_dialog.findViewById(R.id.image_dialog_delete);
				tv_confirm = (TextView) select_dialog.findViewById(R.id.tv_confirm);
				tv_cancel = (TextView) select_dialog.findViewById(R.id.tv_cancel);
				radio_group = (RadioGroup) select_dialog.findViewById(R.id.radio_group);
				radio_one = (RadioButton) select_dialog.findViewById(R.id.radio_one);
				radio_two = (RadioButton) select_dialog.findViewById(R.id.radio_two);
				radio_group.check(R.id.radio_one);
				image_dialog_delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						select_dialog.dismiss();
					}
				});
				tv_confirm.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						if (radio_one.isChecked() == true) {
							reqWithDefaultStore();
							select_dialog.dismiss();
						} else if (radio_two.isChecked() == true) {
							select_dialog.dismiss();
							startOtherSel(storeid);
						}

					}
				});
				tv_cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						select_dialog.dismiss();
					}
				});
			}
		});

	}

	@Override
	protected void setListner() {

	}

	public void reqWithDefaultStore() {
		String url = "ESDefault.GetMainStore";
		RequestParams params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url, params, Default_Store.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Default_Store default_Store = (Default_Store) object;
				if (default_Store.getRet().equals("200")) {
					if (storeid.equals(default_Store.getData().get(0).getId())) {
						Toast.makeText(context, "入库和出库的仓库不能为同一个", 1).show();
					} else {
						startScanActivity(storeid, default_Store.getData().get(0).getId());
					}
				} else {
					Toast.makeText(context, default_Store.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
	}

	public void startScanActivity(String OutStore, String InStore) {
		bundle.putString("title", "CHANGINGSTORE");
		bundle.putString("restart", "NO");
		bundle.putString("scanType", "complex");
		bundle.putString("returnable", "NO");
		bundle.putString("returnType", "sustained");
		bundle.putString("OutStore", OutStore);
		bundle.putString("InStore", InStore);
		CommApplication.getInstance().setScanAreaType("barcodeType");
		startActivity(MipcaActivityCapture.class, bundle);
	}

	public void startOtherSel(String OutStore) {
		bundle.putString("OutStore", OutStore);
		startActivity(Choice_In_WareHouse.class, bundle);
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
					already_adapter.notifyDataSetChanged();
				} else {

				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});

	}
}
