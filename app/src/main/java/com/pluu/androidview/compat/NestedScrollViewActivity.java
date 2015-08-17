package com.pluu.androidview.compat;

import android.os.Bundle;

import com.pluu.androidview.BaseActivity;
import com.pluu.androidview.R;

public class NestedScrollViewActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nested_scroll_view);
		enableHoneAsUp();
	}
}
