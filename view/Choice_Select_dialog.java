package com.example.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.easy_shop.R;

public class Choice_Select_dialog extends Dialog {
	Context context;

	public Choice_Select_dialog(Context context) {
		super(context);
		this.context = context;
	}

	public Choice_Select_dialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_select_dialog);
	}

}
