package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easy_shop.R;
import com.example.model.Chg_List.ChgData;


public class List_Shop_Chg_Adapter extends BaseAdapter {
	private Context context;

	private Context mContext;
	private List<ChgData> data;

	public List_Shop_Chg_Adapter(Context context, List<ChgData> data) {
		mContext = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
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
			convertView = View.inflate(mContext, R.layout.list_shop_on_adapter,
					null);
			holder = new Holder();
			holder.shop_name = (TextView) convertView
					.findViewById(R.id.shop_name);
			holder.shop_num = (TextView) convertView
					.findViewById(R.id.shop_num);
			holder.shop_price = (TextView) convertView
					.findViewById(R.id.shop_price);
			holder.shop_discount = (TextView) convertView
					.findViewById(R.id.shop_discount);
			convertView.setTag(holder);
			holder.shop_total = (TextView) convertView
					.findViewById(R.id.shop_total);
			convertView.setTag(holder);

		}

		ChgData prod = data.get(position);
		holder.shop_name.setText(prod.getGoodname());
		holder.shop_num.setText(prod.getNumber());
		holder.shop_price.setText(prod.getPrice());

		return convertView;
	}

	class Holder {
		TextView shop_name;
		TextView shop_num;
		TextView shop_price;
		TextView shop_discount;
		TextView shop_total;

	}

}
