package com.example.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easy_shop.R;
import com.example.model.Already_List.Already;
import com.example.model.Order_model.Order;

public class Total_Order_Adapter extends BaseAdapter {
	private Context context;

	private Context mContext;
	private List<Order> dataList;

	public Total_Order_Adapter(Context context, List<Order> dataList) {
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
			convertView = View.inflate(mContext, R.layout.total_order_adapter, null);
			holder = new Holder();
			holder.order_number = (TextView) convertView.findViewById(R.id.order_number);
			holder.order_time = (TextView) convertView.findViewById(R.id.order_time);
			convertView.setTag(holder);

		}
		Order prod = dataList.get(position);
		String date = "系统时间缺失";
		if (!prod.getTime().isEmpty()){
			date = timestampToDate(prod.getTime());
		}
		holder.order_number.setText(prod.getSeralordernum());
		holder.order_time.setText(date);
//		holder.order_time.setText(prod.getTime());
		return convertView;
	}

	class Holder {
		TextView order_number;
		TextView order_time;

	}

	@SuppressLint("SimpleDateFormat")
	public static String timestampToDate(String beginDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd日HH:mm");
		String sd = sdf.format(new Date(Long.parseLong(beginDate)));
		return sd;
	}

}
