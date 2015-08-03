package com.pluu.androidview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-01.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

	private final LayoutInflater inflater;
	private final List<String> list;

	public RecyclerViewAdapter(LayoutInflater inflater, List<String> list) {
		this.inflater = inflater;
		this.list = list;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder vh, int position) {
		vh.textView.setText(list.get(position));
	}

	@Override
	public int getItemCount() {
		return list != null ? list.size() : 0;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public TextView textView;

		public ViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(android.R.id.text1);
		}
	}
}
