package com.pluu.androidview.compat;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidview.R;

public class SwipeRefreshLayoutActivity extends AppCompatActivity
	implements SwipeRefreshLayout.OnRefreshListener {

	@Bind(R.id.swipe_refresh_widget)
	SwipeRefreshLayout swipeRefreshWidget;
	@Bind(android.R.id.list)
	ListView list;

	private Handler mHandler = new Handler();
	private final Runnable mRefreshDone = new Runnable() {

		@Override
		public void run() {
			refreshData();
			swipeRefreshWidget.setRefreshing(false);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_refresh_layout);
		ButterKnife.bind(this);

		swipeRefreshWidget
			.setColorSchemeResources(R.color.swipe_color_1,
									 R.color.swipe_color_2,
									 R.color.swipe_color_3,
									 R.color.swipe_color_4,
									 R.color.swipe_color_5);

		refreshData();
		swipeRefreshWidget.setOnRefreshListener(this);
	}

	private void refreshData() {
		final int size = 30;
		List<String> values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			values.add("Item " + i);
		}

		ArrayAdapter<String> arrayAdapter
			= new ArrayAdapter<>(this,
								 android.R.layout.simple_list_item_1,
								 android.R.id.text1,
								 values);

		list.setAdapter(arrayAdapter);
	}

	@Override
	public void onRefresh() {
		refresh();
	}

	private void refresh() {
		mHandler.removeCallbacks(mRefreshDone);
		mHandler.postDelayed(mRefreshDone, 5000);
	}
}
