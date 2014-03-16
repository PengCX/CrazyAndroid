package com.example.addgesture;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	EditText editText;
	GestureOverlayView gestureOverlayView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// ʹ���ı��༭��
		editText = (EditText) findViewById(R.id.gesture_name);
		// ��ȡ���Ʊ༭��ͼ
		gestureOverlayView = (GestureOverlayView) findViewById(R.id.gesture);
		// �������ƻ�ͼ����ɫ
		gestureOverlayView.setGestureColor(Color.RED);
		// �������ƵĻ��ƿ��
		gestureOverlayView.setGestureStrokeWidth(4);
		// Ϊgesture����������¼����¼�������
		gestureOverlayView
				.addOnGesturePerformedListener(new OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							final Gesture gesture) {
						// ����save.xml���沼�ִ������ͼ
						View saveDialog = getLayoutInflater().inflate(
								R.layout.save, null);
						// ��ȡsaveDialog���show���
						ImageView imageView = (ImageView) saveDialog
								.findViewById(R.id.show);
						// ��ȡsaveDialog��gesture_name���
						final EditText gestureEditText = (EditText) saveDialog
								.findViewById(R.id.gesture_name);
						// ����Gesture���������ƴ���һ��λͼ
						Bitmap bitmap = gesture.toBitmap(128, 128, 10,
								0xffff0000);
						imageView.setImageBitmap(bitmap);
						// ʹ�öԻ�����ʾsaveDialog���
						new AlertDialog.Builder(MainActivity.this)
								.setView(saveDialog)
								.setPositiveButton("����", new OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Log.i("peng", "onClick");
										// ��ȡ�ƶ��ļ���Ӧ�����ƿ�
										GestureLibrary guestureLibrary = GestureLibraries
												.fromFile(Environment
														.getExternalStorageDirectory()
														.getPath()
														+ "/mygestures");
										// �������
										guestureLibrary.addGesture(
												gestureEditText.getText()
														.toString(), gesture);
										guestureLibrary.save();
									}
								}).setNegativeButton("ȡ��", null).show();
					}
				});
	}
}
