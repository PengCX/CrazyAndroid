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
	// 定义手势检测实例
	GestureDetector detector;
	ImageView imageView;
	// 控制图片的Matrix对象
	Matrix matrix;
	// 初始的化图片资源
	Bitmap bitmap;
	// 定义图片的宽、高
	int width, height;
	// 控制图片的缩放比例
	float currentScale = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// 创建手势检测器
		detector = new GestureDetector(this, this);
		imageView = (ImageView) findViewById(R.id.imageview1);
		
		matrix = new Matrix();

		// 获取缩放的源图片
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		// 获取图片的宽高
		width = bitmap.getWidth();
		// 获取位图的高度
		height = bitmap.getHeight();
		
		// 设置ImageView初始化时显示的图片
		imageView.setImageBitmap(BitmapFactory.decodeResource(
				this.getResources(), R.drawable.ic_launcher));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 将该Activity上的触碰事件交给GestureDetector处理
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

		// 根据手势的速度来计算缩放比，如果velocityX>0,放大图像，否则缩小图像
		currentScale += currentScale * velocityX / 4000.0f;
		// 保证currentScale不会等于0
		currentScale = currentScale > 0.01 ? currentScale : 0.01f;
		// 重置Matrix
		matrix.reset();
		// 缩放Matrix
		matrix.setScale(currentScale, currentScale, 200, 200);

		BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();
		// 如果图片还未被回收，先强制回收该图片
		if (!tmp.getBitmap().isRecycled()) {
			tmp.getBitmap().recycle();
		}

		// 根据原原始位图和Matrix创建信图片
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		// 显示新的位图
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
