package com.pluu.androidview;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by nohhs on 2015-08-17.
 */
public class BaseActivity extends AppCompatActivity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void enableHoneAsUp() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
