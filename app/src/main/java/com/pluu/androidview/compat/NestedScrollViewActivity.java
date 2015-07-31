package com.pluu.androidview.compat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pluu.androidview.R;

public class NestedScrollViewActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nested_scroll_view);
	}
}
