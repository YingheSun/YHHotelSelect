package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easy_shop.R;
import com.example.model.Sort_Management_List.Sort;

public class Sort_Management_Adapter extends BaseAdapter {
	private Context context;

	private Context mContext;
	private List<Sort> dataList;

	public Sort_Management_Adapter(Context context, List<Sort> dataList) {
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
					R.layout.sort_management_adapter, null);
			holder = new Holder();
			holder.shop_name = (TextView) convertView
					.findViewById(R.id.shop_name);
			holder.shop_num = (TextView) convertView
					.findViewById(R.id.shop_num);

			convertView.setTag(holder);

		}

		Sort prod = dataList.get(position);
		holder.shop_name.setText(prod.getType());
		holder.shop_num.setText(prod.getNumber());

		return convertView;
	}

	class Holder {
		TextView shop_name;
		TextView shop_num;

	}
}
