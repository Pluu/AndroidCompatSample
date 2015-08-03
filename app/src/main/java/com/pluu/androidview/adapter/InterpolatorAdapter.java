package com.pluu.androidview.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.pluu.androidview.adapter.item.InterpolatorItem;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-01.
 */
public class InterpolatorAdapter extends BaseAdapter {
	private final List<InterpolatorItem> list;

	public InterpolatorAdapter(List<InterpolatorItem> list) {this.list = list;}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public InterpolatorItem getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext())
										.inflate(android.R.layout.simple_spinner_dropdown_item, parent,
												 false);
		}

		TextView tv = ViewHolder.get(convertView, android.R.id.text1);
		tv.setText(getItem(position).title);

		return convertView;
	}

	private static class ViewHolder {
		@SuppressWarnings("unchecked")
		public static <T extends View> T get(View view, int id) {
			SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
			if (viewHolder == null) {
				viewHolder = new SparseArray<>();
				view.setTag(viewHolder);
			}
			View childView = viewHolder.get(id);
			if (childView == null) {
				childView = view.findViewById(id);
				viewHolder.put(id, childView);
			}
			return (T) childView;
		}
	}
}
