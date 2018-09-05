package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easy_shop.R;
import com.example.model.Already_List.Already;
import com.example.model.Permission_model.Permission;
import com.example.view.SlideSwitchView;
import com.example.view.SlideSwitchView.OnSwitchChangedListener;

public class List_Authorization_Adapter extends BaseAdapter implements
		OnSwitchChangedListener {

	private Context mContext;
	private List<Permission> dataList;

	public List_Authorization_Adapter(Context context, List<Permission> dataList) {
		mContext = context;
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView != null) {
			holder = (Holder) convertView.getTag();
		} else {
			convertView = View.inflate(mContext,
					R.layout.list_authorization_adapter, null);

			holder = new Holder();
			holder.shop_name = (TextView) convertView
					.findViewById(R.id.shop_name);
			holder.mSlideSwitchView = (SlideSwitchView) convertView
					.findViewById(R.id.mSlideSwitchView);
			holder.mSlideSwitchView.setOnChangeListener(this);

			convertView.setTag(holder);

		}

		Permission prod = dataList.get(position);
		holder.shop_name.setText(prod.getPermissionname());

		return convertView;
	}

	class Holder {
		TextView shop_name;
		SlideSwitchView mSlideSwitchView;

	}

	@Override
	public void onSwitchChange(SlideSwitchView switchView, boolean isChecked) {
		switch (switchView.getId()) {
		
		case R.id.mSlideSwitchView:
			if (isChecked) {
				Toast.makeText(mContext, "¹Ø±Õ", 0).show();
			} else {
				 Toast.makeText(mContext, "´ò¿ª", 0).show();
			}
			break;

		}
	}

}
