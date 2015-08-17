package com.pluu.androidview.compat;

import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidview.BaseActivity;
import com.pluu.androidview.R;
import com.pluu.androidview.adapter.ListMoreAdapter;

public class PopupMenuActivity extends BaseActivity
	implements AdapterView.OnItemClickListener,
			   ListMoreAdapter.ListItemClickListener,
			   PopupMenu.OnMenuItemClickListener {

	@Bind(android.R.id.list)
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup_menu);
		ButterKnife.bind(this);
		enableHoneAsUp();

		final int size = 30;
		List<String> values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			values.add("Item " + i);
		}
		list.setAdapter(new ListMoreAdapter(values, this));
		list.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "Select " + position, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onListClick(View view) {
		PopupMenu popupMenu = new PopupMenu(this, view);
		popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
		popupMenu.setOnMenuItemClickListener(this);
		popupMenu.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		Toast.makeText(this, "Select " + item.getTitle(), Toast.LENGTH_SHORT).show();
		return false;
	}
}
