package com.pluu.androidview.compat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidview.R;
import com.pluu.androidview.widget.ViewDragLayout;

public class ViewDragHelperActivity extends AppCompatActivity
	implements AdapterView.OnItemClickListener {

	@Bind(R.id.listView)
	ListView listView;
	@Bind(R.id.dragLayout)
	ViewDragLayout dragLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_drag_helper);
		ButterKnife.bind(this);

		final int size = 30;
		List<String> values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			values.add("Item " + i);
		}

		listView.setAdapter(new ArrayAdapter<>(this,
											   android.R.layout.simple_list_item_1,
											   values));
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		dragLayout.maximize();
	}
}
