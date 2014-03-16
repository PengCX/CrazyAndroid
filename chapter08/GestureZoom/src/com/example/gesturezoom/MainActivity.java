package com.example.gesturezoom;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnGestureListener {
	// �������Ƽ��ʵ��
	GestureDetector detector;
	ImageView imageView;
	// ����ͼƬ��Matrix����
	Matrix matrix;
	// ��ʼ�Ļ�ͼƬ��Դ
	Bitmap bitmap;
	// ����ͼƬ�Ŀ���
	int width, height;
	// ����ͼƬ�����ű���
	float currentScale = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// �������Ƽ����
		detector = new GestureDetector(this, this);
		imageView = (ImageView) findViewById(R.id.imageview1);
		
		matrix = new Matrix();

		// ��ȡ���ŵ�ԴͼƬ
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		// ��ȡͼƬ�Ŀ��
		width = bitmap.getWidth();
		// ��ȡλͼ�ĸ߶�
		height = bitmap.getHeight();
		
		// ����ImageView��ʼ��ʱ��ʾ��ͼƬ
		imageView.setImageBitmap(BitmapFactory.decodeResource(
				this.getResources(), R.drawable.ic_launcher));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ����Activity�ϵĴ����¼�����GestureDetector����
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityX = velocityX < -4000 ? -4000 : velocityX;

		// �������Ƶ��ٶ����������űȣ����velocityX>0,�Ŵ�ͼ�񣬷�����Сͼ��
		currentScale += currentScale * velocityX / 4000.0f;
		// ��֤currentScale�������0
		currentScale = currentScale > 0.01 ? currentScale : 0.01f;
		// ����Matrix
		matrix.reset();
		// ����Matrix
		matrix.setScale(currentScale, currentScale, 200, 200);

		BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();
		// ���ͼƬ��δ�����գ���ǿ�ƻ��ո�ͼƬ
		if (!tmp.getBitmap().isRecycled()) {
			tmp.getBitmap().recycle();
		}

		// ����ԭԭʼλͼ��Matrix������ͼƬ
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		// ��ʾ�µ�λͼ
		imageView.setImageBitmap(bitmap2);
	
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}
