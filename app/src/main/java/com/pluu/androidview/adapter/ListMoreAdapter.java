package com.pluu.androidview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.pluu.androidview.R;

/**
 * Created by nohhs on 2015-07-31.
 */
public class ListMoreAdapter extends BaseAdapter {

	public interface ListItemClickListener {
		void onListClick(View view);
	}

	private final List<String> list;
	private final ListItemClickListener listener;

	public ListMoreAdapter(List<String> list, ListItemClickListener listener) {
		super();
		this.list = list;
		this.listener = listener;
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public String getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext())
										.inflate(R.layout.layout_list_more_vert, null);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.textView);
			holder.img = (ImageView)convertView.findViewById(R.id.more);
			final int color = parent.getContext().getResources().getColor(R.color.swipe_color_2);
			holder.img.setColorFilter(color);
			holder.img.setOnClickListener(clickListener);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text.setText(getItem(position));
		return convertView;
	}

	private View.OnClickListener clickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (listener != null) {
				listener.onListClick(v);
			}
		}
	};

	private class ViewHolder {
		public TextView text;
		public ImageView img;
	}
}
