package com.example.easy_shop;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.List_Already_Adapter;
import com.example.adapter.List_Shop_Adapter;
import com.example.adapter.Sort_Management_Adapter;
import com.example.easy_shop.BaseActivity.ServerResult;
import com.example.easy_shop.R.style;
import com.example.model.Already_List;
import com.example.model.Products_List;
import com.example.model.Sort_Management_List;
import com.example.model.Sort_Management_List.Sort;
import com.example.view.Commodity_dialog;
import com.lidroid.xutils.http.RequestParams;

public class Commodity_Classification extends BaseActivity {
	private Commodity_dialog commodity_dialog;
	private TextView tv_yes, tv_no;
	private EditText et_warehouse_name;
	private ImageView image_dialog_delete;
	private ListView list_shop;
	private Sort_Management_Adapter sort_management_Adapter;
	private List<Sort> list = new ArrayList<Sort>();
	private String url = "ESTypeManage.GetTypeList";
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_list);
		initTitle("返回", "分类管理", "添加");
		init();
		setListner();
		params = new RequestParams();
		params.addBodyParameter("shopid", CommApplication.getInstance()
				.getShopid().toString());
		params.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid());
		HttpXutils(this, url, params, Sort_Management_List.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Sort_Management_List sort_management_list = (Sort_Management_List) object;
						if (sort_management_list.getRet().equals("200")) {
							list.addAll(sort_management_list.getData());
							sort_management_Adapter = new Sort_Management_Adapter(
									getApplicationContext(), list);
							list_shop.setAdapter(sort_management_Adapter);
						} else {

						}

					}

					@Override
					public void onFailure(String code, String info) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			finish();
			break;
		case R.id.tv_titlebar_right:
			commodity_dialog = new Commodity_dialog(
					Commodity_Classification.this, style.phonedialog);
			commodity_dialog.show();
			image_dialog_delete = (ImageView) commodity_dialog
					.findViewById(R.id.image_dialog_delete);
			tv_yes = (TextView) commodity_dialog.findViewById(R.id.tv_yes);
			tv_no = (TextView) commodity_dialog.findViewById(R.id.tv_no);
			et_warehouse_name = (EditText) commodity_dialog
					.findViewById(R.id.et_warehouse_name);
			image_dialog_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					commodity_dialog.dismiss();
				}
			});
			tv_yes.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					commodity_dialog.dismiss();
					Https();
				}
			});
			tv_no.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					commodity_dialog.dismiss();
				}
			});

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

	public void Https() {
		String url_comm = "ESTypeManage.AddType";
		RequestParams params_comm = new RequestParams();
		params_comm.addBodyParameter("typename", et_warehouse_name.getText()
				.toString());
		params_comm.addBodyParameter("shopid", CommApplication.getInstance()
				.getShopid().toString());
		params_comm.addBodyParameter("reqid", CommApplication.getInstance()
				.getUserid());
		HttpXutils(this, url_comm, params_comm, Sort_Management_List.class,
				new ServerResult() {

					@Override
					public void onSuccess(Object object) {
						Sort_Management_List already_list = (Sort_Management_List) object;
						if (already_list.getRet().equals("200")) {
							list.clear();
							list.addAll(already_list.getData());
							sort_management_Adapter = new Sort_Management_Adapter(
									getApplicationContext(), list);
							list_shop.setAdapter(sort_management_Adapter);
							sort_management_Adapter.notifyDataSetChanged();
						} else {
							Toast.makeText(context, already_list.getMsg(), 1)
									.show();
						}
					}

					@Override
					public void onFailure(String code, String info) {

					}
				});

	}

}
