package com.example.easy_shop;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegistShop extends BaseActivity {
	private TextView next;
	private EditText phone_number;
	private Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		initTitle("·µ»Ø", "×¢²á", null);
		init();
		setListner();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_titlebar_left:
			this.finish();
			break;
		case R.id.next:
			bundle.putString("phone_number", phone_number.getText().toString());
			startActivity(RegisterNext.class, bundle);
			break;
		}

	}

	@Override
	protected void init() {
		next = (TextView) findViewById(R.id.next);
		phone_number = (EditText) findViewById(R.id.phone_number);
		bundle = new Bundle();
	}

	@Override
	protected void setListner() {
		next.setOnClickListener(this);
	}
}