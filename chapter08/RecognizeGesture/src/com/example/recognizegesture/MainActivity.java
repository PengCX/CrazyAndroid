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
	// �����ֻ��༭���
	GestureOverlayView gestureOverlayView;
	// ��¼�ֻ������е����ƿ�
	GestureLibrary gestureLibrariLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		gestureOverlayView = (GestureOverlayView) findViewById(R.id.gesture);
		gestureLibrariLibrary = GestureLibraries.fromFile(Environment
				.getExternalStorageDirectory().getPath() + "/mygestures");

		if (gestureLibrariLibrary.load()) {
			Toast.makeText(MainActivity.this, "�����ļ�װ�ڳɹ�", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(MainActivity.this, "�����ļ�װ��ʧ��", Toast.LENGTH_LONG)
					.show();
		}

		// �������Ʊ༭����󶨼�����
		gestureOverlayView
				.addOnGesturePerformedListener(new OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							Gesture gesture) {
						// ʶ���û��ո������Ƶ�����
						ArrayList<Prediction> predictions = gestureLibrariLibrary
								.recognize(gesture);
						ArrayList<String> result = new ArrayList<String>();
						// ���������ҵ���Prediction����
						for (Prediction prediction : predictions) {
							// ֻ�����ƶȴ���0.2�����ƲŻᱻ���
							if (prediction.score > 0.2) {
								result.add("������{" + prediction.name + "}���ƶ�Ϊ:"
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
									.setPositiveButton("ȷ��", null).show();
						} else {
							Toast.makeText(MainActivity.this, "�޷����ҵ�ƥ�������",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}
}
