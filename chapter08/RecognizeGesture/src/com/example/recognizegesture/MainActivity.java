package com.example.recognizegesture;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 定义手机编辑组件
	GestureOverlayView gestureOverlayView;
	// 记录手机上已有的手势库
	GestureLibrary gestureLibrariLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		gestureOverlayView = (GestureOverlayView) findViewById(R.id.gesture);
		gestureLibrariLibrary = GestureLibraries.fromFile(Environment
				.getExternalStorageDirectory().getPath() + "/mygestures");

		if (gestureLibrariLibrary.load()) {
			Toast.makeText(MainActivity.this, "手势文件装在成功", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(MainActivity.this, "手势文件装在失败", Toast.LENGTH_LONG)
					.show();
		}

		// 定义手势编辑组件绑定监听器
		gestureOverlayView
				.addOnGesturePerformedListener(new OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							Gesture gesture) {
						// 识别用户刚刚所绘制的手势
						ArrayList<Prediction> predictions = gestureLibrariLibrary
								.recognize(gesture);
						ArrayList<String> result = new ArrayList<String>();
						// 遍历所有找到的Prediction对象
						for (Prediction prediction : predictions) {
							// 只有相似度大于0.2的手势才会被输出
							if (prediction.score > 0.2) {
								result.add("与手势{" + prediction.name + "}相似度为:"
										+ prediction.score);
							}
						}

						if (result.size() > 0) {
							ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(
									MainActivity.this,
									android.R.layout.simple_dropdown_item_1line,
									result.toArray());
							new AlertDialog.Builder(MainActivity.this)
									.setAdapter(arrayAdapter, null)
									.setPositiveButton("确定", null).show();
						} else {
							Toast.makeText(MainActivity.this, "无法能找到匹配的手势",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
}
