package com.example.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.adapter.Total_Order_Adapter.Holder;
import com.example.easy_shop.R;
import com.example.model.Order_model.Order;
import com.example.model.War_List_model.War;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class War_List_Adapter extends BaseAdapter {
	private Context context;

	private Context mContext;
	private List<War> dataList;

	public War_List_Adapter(Context context, List<War> dataList) {
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
			convertView = View.inflate(mContext, R.layout.war_cell, null);
			holder = new Holder();
			holder.cell1 = (TextView) convertView.findViewById(R.id.cell1);
			holder.cell2 = (TextView) convertView.findViewById(R.id.cell2);
			holder.cell3 = (TextView) convertView.findViewById(R.id.cell3);
			holder.cell4 = (TextView) convertView.findViewById(R.id.cell4);
			convertView.setTag(holder);

		}
		War prod = dataList.get(position);
		holder.cell1.setText(prod.getStroe());
		holder.cell2.setText(prod.getGoods_name());
		holder.cell3.setText(prod.getWarningline());
		holder.cell4.setText(prod.getNumber());
		return convertView;
	}

	class Holder {
		public TextView cell4;
		public TextView cell3;
		public TextView cell2;
		public TextView cell1;

	}

}
