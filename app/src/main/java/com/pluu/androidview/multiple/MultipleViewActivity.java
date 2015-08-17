package com.pluu.androidview.multiple;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidview.BaseActivity;
import com.pluu.androidview.R;
import com.pluu.androidview.adapter.RecyclerViewAdapter;

public class MultipleViewActivity extends BaseActivity {

	@Bind(R.id.recyclerView)
	RecyclerView recyclerView;
	private GridLayoutManager manager;

	private final Random r = new Random(System.currentTimeMillis());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_view);
		ButterKnife.bind(this);
		enableHoneAsUp();

		int columnCount = getResources().getInteger(R.integer.column_count);
		manager = new GridLayoutManager(this, columnCount);
		recyclerView.setLayoutManager(manager);

		final int size = 100;
		List<String> values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			values.add(getData());
		}

		recyclerView.setAdapter(new RecyclerViewAdapter(getLayoutInflater(), values));
	}

	private String getData() {
		int count = r.nextInt(10) + 1;

		StringBuilder strBuilder = new StringBuilder();
		int idx = 0;
		while (idx < count) {
			strBuilder.append("Count " + idx);
			idx++;
			if (idx < count) {
				strBuilder.append(", ");
			}
		}
		return strBuilder.toString();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
			|| newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			updateSpanCount();
		}
	}

	public void updateSpanCount() {
		int columnCount = getResources().getInteger(R.integer.column_count);
		manager.setSpanCount(columnCount);
		recyclerView.getAdapter().notifyDataSetChanged();
	}

}
