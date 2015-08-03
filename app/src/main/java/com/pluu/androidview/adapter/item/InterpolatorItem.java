package com.pluu.androidview.adapter.item;

import android.view.animation.Interpolator;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-01.
 */
public class InterpolatorItem {
	public final String title;
	public final Interpolator interpolator;

	public InterpolatorItem(String title, Interpolator interpolator) {
		this.title = title;
		this.interpolator = interpolator;
	}
}
