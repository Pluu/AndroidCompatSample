package com.pluu.androidview.compat;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidview.R;

public class SlidingPanelLayoutActivity extends AppCompatActivity
	implements AdapterView.OnItemClickListener, SlidingPaneLayout.PanelSlideListener {

	@Bind(R.id.left_pane)
	ListView listView;
	@Bind(R.id.content_text)
	TextView contentText;
	@Bind(R.id.sliding_pane_layout)
	SlidingPaneLayout slidingPaneLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sliding_panel_layout);
		ButterKnife.bind(this);

		slidingPaneLayout.setPanelSlideListener(this);
		slidingPaneLayout.openPane();

		final int size = 30;
		List<String> values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			values.add("Item " + i);
		}

		listView.setAdapter(new ArrayAdapter<>(this,
											   android.R.layout.simple_list_item_1,
											   values));
		listView.setOnItemClickListener(this);
		slidingPaneLayout.closePane();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		slidingPaneLayout.closePane();
	}

	@Override
	public void onPanelSlide(View panel, float slideOffset) {

	}

	@Override
	public void onPanelOpened(View panel) {
		Toast.makeText(this, "Panel Open", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPanelClosed(View panel) {
		Toast.makeText(this, "Panel Close", Toast.LENGTH_SHORT).show();
	}
}
