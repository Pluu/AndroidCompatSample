package com.pluu.androidview.data;

import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pluu.androidview.BaseActivity;
import com.pluu.androidview.R;

public class EnumTestActivity extends BaseActivity {

	private final String TAG = EnumTestActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enum_test);
		ButterKnife.bind(this);
		enableHoneAsUp();
	}

	@OnClick(R.id.button)
	public void onButtonClick() {
		for (TYPE_LIST type : TYPE_LIST.values()) {
			Log.d(TAG, type.name() + " is " + type.isCustom());
		}
	}

	@OnClick(R.id.button1)
	public void onButton1Click() {
		for (RES_CODE code : RES_CODE.values()) {
			Log.d(TAG, code.toString());
		}
	}

	@OnClick(R.id.button2)
	public void onButton2Click() {
		RES_CODE code = RES_CODE.values()[RES_CODE.SUCCESS.ordinal()];
		Log.d(TAG, "ordinal > " + code.toString());

		code = RES_CODE.getCodeGenerator(RES_CODE.SUCCESS.getCode());
		Log.d(TAG, "Generator > " + code.toString());
	}

	@OnClick(R.id.button3)
	public void onButton3Click() {
		RES_CODE code = RES_CODE.values()[600];
	}

	@OnClick(R.id.button4)
	public void onButton4Click() {
		RES_CODE code = RES_CODE.getCodeGenerator(600);
	}
}
