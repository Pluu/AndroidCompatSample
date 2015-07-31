package com.pluu.androidview.compat;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pluu.androidview.R;

public class ContentLoadingProgressBarActivity extends AppCompatActivity {

	@Bind(R.id.view2)
	ContentLoadingProgressBar view2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_loading_progress_bar);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btnShow)
	public void onShowClick() {
		view2.show();
	}

	@OnClick(R.id.btnHide)
	public void onHideClick() {
		view2.hide();
	}

}
