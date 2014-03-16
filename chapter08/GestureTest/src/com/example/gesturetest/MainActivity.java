package com.example.gesturetest;

import com.example.gesturetest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener {
	// 定义手势检测实例
	GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// 创建手势检测器
		detector = new GestureDetector(this, this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 将该Activity上的触碰事件交给GestureDetector处理
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// 触碰时间按下时触发该方法
		Toast.makeText(this, "OnDown", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// 当用户在屏幕上“拖动”时触发该方法
		Toast.makeText(this, "onFling", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// 当用户在屏幕上长按时触发该方法
		Toast.makeText(this, "onLongPress", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// 当屏幕“滚动”时触发该方法
		Toast.makeText(this, "onScroll", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// 当用户在触摸屏幕上按下、而且还未移动和松开时触发该方法
		Toast.makeText(this, "onShowPress", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// 在屏幕上的轻击事件将会触发该方法
		Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_LONG).show();
		return false;
	}

}
