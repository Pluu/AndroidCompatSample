package com.pluu.androidview.animation;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidview.R;
import com.pluu.androidview.adapter.InterpolatorAdapter;
import com.pluu.androidview.adapter.item.InterpolatorItem;

public class InterpolatorActivity extends AppCompatActivity
	implements AdapterView.OnItemSelectedListener {

	@Bind(R.id.spinner)
	Spinner spinner;
	@Bind(R.id.container)
	RelativeLayout container;
	@Bind(R.id.view)
	View moveView;

	private InterpolatorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interpolator);
		ButterKnife.bind(this);

		List<InterpolatorItem> list = new ArrayList<>();
		list.add(
			new InterpolatorItem("FastOutLinearInInterpolator", new FastOutLinearInInterpolator()));
		list.add(
			new InterpolatorItem("FastOutSlowInInterpolator", new FastOutSlowInInterpolator()));
		list.add(
			new InterpolatorItem("LinearOutSlowInInterpolator", new LinearOutSlowInInterpolator()));

		adapter = new InterpolatorAdapter(list);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		final Interpolator interpolator = adapter.getItem(position).interpolator;

		int xValue = container.getWidth() - moveView.getWidth();

		ViewCompat.setX(moveView, 0);

		ViewCompat
			.animate(moveView)
			.setInterpolator(interpolator)
			.setDuration(android.R.integer.config_mediumAnimTime)
			.setStartDelay(250L)
			.x(xValue);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) { }
}
