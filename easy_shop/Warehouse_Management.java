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
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.List_Already_Adapter;
import com.example.adapter.List_Shop_Adapter;
import com.example.easy_shop.R.style;
import com.example.model.Already_List;
import com.example.model.Already_List.Already;
import com.example.view.Scrap_dialog;
import com.example.view.Select_dialog;
import com.lidroid.xutils.http.RequestParams;

public class Warehouse_Management extends BaseActivity {
	private RadioButton tv_one;
	private RadioButton tv_two;
	private RadioGroup rg_group;
	private ListView list_already;
	private ListView list_obsolete;
	private String url_already = "ESStoreManage.GetStoreList";
	private String url_obsolete = "ESStoreManage.GetStoreList";
	private RequestParams params_already;
	private RequestParams params_obsolete;
	private List_Already_Adapter already_adapter;
	private List_Already_Adapter obsolete_adapter;
	private List<Already> list = new ArrayList<Already>();
	private List<Already> list_one = new ArrayList<Already>();
	private Select_dialog select_dialog;
	private Scrap_dialog scrap_dialog;
	private TextView tv_confirm, tv_cancel;
	private TextView tv_yes, tv_no;
	private ImageView image_dialog_delete;
	private ImageView scrap_dialog_delete;
	private RadioGroup radio_group;
	private RadioButton radio_one;
	private RadioButton radio_two;
	private String storeid;
	private String storeid_obsolete;
	private Bundle bundle = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.warehouse_management);
		initTitle("返回", "库房管理", "添加");
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_titlebar_right:
			startActivity(Add_Warehouse.class);
			break;
		case R.id.tv_one:
			tv_one.setTextColor(getResources().getColor(R.color.white));
			tv_one.setBackgroundColor(getResources().getColor(R.color.blues));
			tv_two.setTextColor(getResources().getColor(R.color.blues));
			tv_two.setBackgroundColor(getResources().getColor(R.color.rad_back));
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
			list_already.setVisibility(View.VISIBLE);
			list_obsolete.setVisibility(View.GONE);
			break;
		case R.id.tv_two:
			tv_two.setTextColor(getResources().getColor(R.color.white));
			tv_two.setBackgroundColor(getResources().getColor(R.color.blues));
			tv_one.setTextColor(getResources().getColor(R.color.blues));
			tv_one.setBackgroundColor(getResources().getColor(R.color.rad_back));
			params_obsolete = new RequestParams();
			params_obsolete.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
			params_obsolete.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
			params_obsolete.addBodyParameter("state", "abended");
			HttpXutils(this, url_obsolete, params_obsolete, Already_List.class, new ServerResult() {

				@Override
				public void onSuccess(Object object) {
					Already_List already_list_one = (Already_List) object;
					if (already_list_one.getRet().equals("200")) {
						list_one.clear();
						list_one.addAll(already_list_one.getData());
						obsolete_adapter = new List_Already_Adapter(getApplicationContext(), list_one);
						list_obsolete.setAdapter(obsolete_adapter);
						obsolete_adapter.notifyDataSetChanged();
					} else {

					}
				}

				@Override
				public void onFailure(String code, String info) {

				}
			});
			list_obsolete.setVisibility(View.VISIBLE);
			list_already.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	protected void init() {
		tv_one = (RadioButton) findViewById(R.id.tv_one);
		tv_two = (RadioButton) findViewById(R.id.tv_two);
		rg_group = (RadioGroup) findViewById(R.id.rg_group);
		list_already = (ListView) findViewById(R.id.list_already);
		list_obsolete = (ListView) findViewById(R.id.list_obsolete);
		rg_group.check(R.id.tv_one);
		list_obsolete.setVisibility(View.GONE);
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
				select_dialog = new Select_dialog(Warehouse_Management.this, style.phonedialog);
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
							select_dialog.dismiss();
							bundle.putString("storeid", storeid);
							startActivity(Revise_Warehouse.class, bundle);
						} else if (radio_two.isChecked() == true) {
							select_dialog.dismiss();
							Https();
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
		params_obsolete = new RequestParams();
		params_obsolete.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params_obsolete.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		params_obsolete.addBodyParameter("state", "abended");
		HttpXutils(this, url_obsolete, params_obsolete, Already_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Already_List already_list_one = (Already_List) object;
				if (already_list_one.getRet().equals("200")) {
					list_one.addAll(already_list_one.getData());
					obsolete_adapter = new List_Already_Adapter(getApplicationContext(), list_one);
					list_obsolete.setAdapter(obsolete_adapter);
				} else {

				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});
		list_obsolete.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				storeid_obsolete = list_one.get(position).getId();
				scrap_dialog = new Scrap_dialog(Warehouse_Management.this, style.phonedialog);
				scrap_dialog.show();
				scrap_dialog_delete = (ImageView) scrap_dialog.findViewById(R.id.scrap_dialog_delete);
				tv_yes = (TextView) scrap_dialog.findViewById(R.id.tv_yes);
				tv_no = (TextView) scrap_dialog.findViewById(R.id.tv_no);
				scrap_dialog_delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						scrap_dialog.dismiss();
					}
				});
				tv_yes.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						scrap_dialog.dismiss();
						Https_ReuseStore();
					}
				});
				tv_no.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						scrap_dialog.dismiss();
					}
				});

			}
		});

	}

	@Override
	protected void setListner() {
		tv_one.setOnClickListener(this);
		tv_two.setOnClickListener(this);
	}

	public void Https() {
		String url = "ESStoreManage.AbendStore";
		RequestParams params = new RequestParams();
		params.addBodyParameter("storeid", storeid);
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url, params, Already_List.class, new ServerResult() {

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
					Toast.makeText(context, already_list.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});

	}

	public void Https_ReuseStore() {
		String url = "ESStoreManage.ReuseStore";
		RequestParams params = new RequestParams();
		params.addBodyParameter("storeid", storeid_obsolete);
		params.addBodyParameter("shopid", CommApplication.getInstance().getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance().getUserid());
		HttpXutils(this, url, params, Already_List.class, new ServerResult() {

			@Override
			public void onSuccess(Object object) {
				Already_List already_list_one = (Already_List) object;
				if (already_list_one.getRet().equals("200")) {
					list_one.clear();
					list_one.addAll(already_list_one.getData());
					obsolete_adapter = new List_Already_Adapter(getApplicationContext(), list_one);
					list_obsolete.setAdapter(obsolete_adapter);
					obsolete_adapter.notifyDataSetChanged();
				} else {
					Toast.makeText(context, already_list_one.getMsg(), 1).show();
				}
			}

			@Override
			public void onFailure(String code, String info) {

			}
		});

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
