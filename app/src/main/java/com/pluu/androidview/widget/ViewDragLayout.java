package com.pluu.androidview.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.pluu.androidview.R;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-01.
 */
public class ViewDragLayout extends RelativeLayout {

	private final ViewDragHelper mDragHelper;

	private View mHeaderView;
	private View mDescView;

	private float mInitialMotionX;
	private float mInitialMotionY;

	private int mDragRange;
	private int mTop;
	private float mDragOffset;

	public ViewDragLayout(Context context) {
		this(context, null);
	}

	public ViewDragLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mHeaderView = findViewById(R.id.dragHeader);
		mDescView = findViewById(R.id.dragContent);
	}

	public ViewDragLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
	}

	public void maximize() {
		smoothSlideTo(0f);
	}

	boolean smoothSlideTo(float slideOffset) {
		final int topBound = getPaddingTop();
		int y = (int) (topBound + slideOffset * mDragRange);

		if (mDragHelper.smoothSlideViewTo(mHeaderView, mHeaderView.getLeft(), y)) {
			ViewCompat.postInvalidateOnAnimation(this);
			return true;
		}
		return false;
	}

	private class DragHelperCallback extends ViewDragHelper.Callback {

		@Override
		public boolean tryCaptureView(View child, int pointerId) {
			return child == mHeaderView;
		}

		@Override
		public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
			mTop = top;

			mDragOffset = (float) top / mDragRange;

			ViewCompat.setPivotX(mHeaderView, mHeaderView.getWidth());
			mHeaderView.setPivotY(mHeaderView.getHeight());

			float scale = 1 - (mDragOffset * 0.4f);
			ViewCompat.setAlpha(mHeaderView, scale);
			ViewCompat.setScaleX(mHeaderView, scale);
			ViewCompat.setScaleY(mHeaderView, scale);
			ViewCompat.setAlpha(mDescView, 1 - mDragOffset);

			requestLayout();
		}

		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			int top = getPaddingTop();
			if (yvel > 0 || (yvel == 0 && mDragOffset > 0.5f)) {
				top += mDragRange;
			}
			mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
		}

		@Override
		public int getViewVerticalDragRange(View child) {
			return mDragRange;
		}

		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			final int topBound = getPaddingTop();
			final int bottomBound = getHeight() - mHeaderView.getHeight() - mHeaderView.getPaddingBottom();
			return Math.min(Math.max(top, topBound), bottomBound);
		}
	}

	@Override
	public void computeScroll() {
		if (mDragHelper.continueSettling(true)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = MotionEventCompat.getActionMasked(ev);
		if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
			mDragHelper.cancel();
			return false;
		}
		return mDragHelper.shouldInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		mDragHelper.processTouchEvent(ev);

		final int action = MotionEventCompat.getActionMasked(ev);
		final int idx = MotionEventCompat.getActionIndex(ev);
		final float x = MotionEventCompat.getX(ev, idx);
		final float y = MotionEventCompat.getY(ev, idx);

		boolean isHeaderViewUnder = mDragHelper.isViewUnder(mHeaderView, (int) x, (int) y);
		boolean isHeaderViewHit = isViewHit(mHeaderView, (int) x, (int) y);
		switch (action & MotionEventCompat.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN: {
				mInitialMotionX = x;
				mInitialMotionY = y;
				break;
			}

			case MotionEvent.ACTION_UP: {
				if (mDragOffset == 1.0f || mDragOffset == 0f) {
					// Click
					smoothSlideTo(Math.abs(1 - Math.round(mDragOffset)));
				} else if (isHeaderViewHit) {
					// Moving
					smoothSlideTo(Math.round(mDragOffset));
				}
				break;
			}
		}

		return isHeaderViewUnder
			&& isHeaderViewHit
			|| isViewHit(mDescView, (int) x, (int) y);
	}

	private boolean isViewHit(View view, int x, int y) {
		int[] viewLocation = new int[2];
		view.getLocationOnScreen(viewLocation);
		int[] parentLocation = new int[2];
		this.getLocationOnScreen(parentLocation);
		int screenX = parentLocation[0] + x;
		int screenY = parentLocation[1] + y;
		return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() &&
			screenY >= viewLocation[1] && screenY < viewLocation[1] + view.getHeight();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
		int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(ViewCompat.resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
							 ViewCompat.resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mDragRange = getHeight() - mHeaderView.getHeight();

		int height = mHeaderView.getMeasuredHeight();
		mHeaderView.layout(0, mTop, r, mTop + height);
		mDescView.layout(0, mTop + height, r, mTop + b);
	}


}
