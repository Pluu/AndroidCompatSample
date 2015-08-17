package com.pluu.androidview.compat;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pluu.androidview.BaseActivity;
import com.pluu.androidview.R;

public class ViewCompatActivity extends BaseActivity {

	@Bind(android.R.id.button1)
	Button button1;
	@Bind(R.id.container)
	LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_compat);
		ButterKnife.bind(this);
		enableHoneAsUp();

		ViewCompat.setElevation(button1, 3f);

		ViewCompat
			.animate(button1)
			.setInterpolator(new FastOutLinearInInterpolator())
			.setDuration(500L);
	}

	@OnClick(R.id.fadeOut)
	public void onFadeOutClick() {
		ViewCompat
			.animate(button1).alpha(0);
	}

	@OnClick(R.id.fadeIn)
	public void onFadeInClick() {
		ViewCompat
			.animate(button1).alpha(1);
	}

	@OnClick(R.id.moveOver)
	public void onMoveOverClick() {
		int xValue = container.getWidth() - button1.getWidth();
		int yValue = container.getHeight() - button1.getHeight();
		ViewCompat
			.animate(button1).x(xValue).y(yValue);
	}

	@OnClick(R.id.moveBack)
	public void onMoveBackClick() {
		ViewCompat
			.animate(button1).x(0).y(0);
	}

	@OnClick(R.id.rotate)
	public void onRotateClick() {
		ViewCompat
			.animate(button1)
			.rotationXBy(720)
			.rotationYBy(720)
			.rotationBy(720);
	}
}
