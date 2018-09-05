package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easy_shop.R;
import com.example.model.Already_List.Already;

public class List_Already_Adapter extends BaseAdapter {
	private Context context;

	private Context mContext;
	private List<Already> dataList;

	public List_Already_Adapter(Context context, List<Already> dataList) {
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
			convertView = View.inflate(mContext, R.layout.list_shop_adapter,
					null);
			holder = new Holder();
			holder.shop_name = (TextView) convertView
					.findViewById(R.id.shop_name);
			holder.shop_num = (TextView) convertView
					.findViewById(R.id.shop_num);
			holder.shop_price = (TextView) convertView
					.findViewById(R.id.shop_price);
			convertView.setTag(holder);

		}

		Already prod = dataList.get(position);
		holder.shop_name.setText(prod.getStroe());
		holder.shop_num.setText(prod.getKindsnum());
		holder.shop_price.setText(prod.getNumbers());

		return convertView;
	}

	class Holder {
		TextView shop_name;
		TextView shop_num;
		TextView shop_price;

	}
}
