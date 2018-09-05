package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.easy_shop.R;
import com.example.model.Already_List.Already;
import com.example.model.Good_model.Datas;

public class Product_Adapter extends BaseAdapter {
	private Context context;

	private Context mContext;
	private List<Datas> dataList;

	public Product_Adapter(Context context, List<Datas> dataList) {
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
			convertView = View.inflate(mContext, R.layout.product_adapter,
					null);
			holder = new Holder();
			holder.shop_name = (TextView) convertView
					.findViewById(R.id.shop_name);
			holder.shop_num = (TextView) convertView
					.findViewById(R.id.shop_num);
			holder.shop_price = (TextView) convertView
					.findViewById(R.id.shop_price);
			holder.shop_ext = (TextView) convertView
					.findViewById(R.id.shop_ext);
			convertView.setTag(holder);

		}

		Datas prod = dataList.get(position);
		String SellFlag="停售";
		if(prod.getOnsellingflag().equals("1")){
			SellFlag="在售";
		}else if(prod.getOnsellingflag().equals("3")){
			SellFlag="未维护";
		}
		holder.shop_name.setText(prod.getGoods_name());
		holder.shop_num.setText(prod.getCost());
		holder.shop_price.setText(prod.getPrice());
		holder.shop_ext.setText(SellFlag);
		return convertView;
	}

	class Holder {
		TextView shop_ext;
		TextView shop_name;
		TextView shop_num;
		TextView shop_price;

	}
}
