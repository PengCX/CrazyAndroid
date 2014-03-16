package com.example.gesturetest;

import com.example.gesturetest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener {
	// �������Ƽ��ʵ��
	GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// �������Ƽ����
		detector = new GestureDetector(this, this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ����Activity�ϵĴ����¼�����GestureDetector����
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// ����ʱ�䰴��ʱ�����÷���
		Toast.makeText(this, "OnDown", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// ���û�����Ļ�ϡ��϶���ʱ�����÷���
		Toast.makeText(this, "onFling", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// ���û�����Ļ�ϳ���ʱ�����÷���
		Toast.makeText(this, "onLongPress", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// ����Ļ��������ʱ�����÷���
		Toast.makeText(this, "onScroll", Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// ���û��ڴ�����Ļ�ϰ��¡����һ�δ�ƶ����ɿ�ʱ�����÷���
		Toast.makeText(this, "onShowPress", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// ����Ļ�ϵ�����¼����ᴥ���÷���
		Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_LONG).show();
		return false;
	}

}
