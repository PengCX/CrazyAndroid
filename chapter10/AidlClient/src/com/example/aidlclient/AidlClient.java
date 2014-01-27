package com.example.aidlclient;

import com.example.aidlservice.ICat;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;

public class AidlClient extends Activity {
	private ICat catService;

	private Button getButton;
	private EditText colorEditText, weightEditText;

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			catService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// ��ȡԶ��Service��onBinder�������صĶ������
			catService = ICat.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aidl_client);

		getButton = (Button) findViewById(R.id.getbutton);
		colorEditText = (EditText) findViewById(R.id.coloredittext);
		weightEditText = (EditText) findViewById(R.id.weightedittext);

		// ��������Ҫ�󶨵�Service��Intent
		Intent intent = new Intent();
		intent.setAction("org.crazyit.aidl.action.AIDL_SERVICE");
		// ��Զ�̵ķ���
		bindService(intent, conn, Service.BIND_AUTO_CREATE);

		getButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ����ʾԶ��service��״̬
				try {
					colorEditText.setText(catService.getColor());
					weightEditText.setText(catService.getWeight() + "");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// �����
		this.unbindService(conn);
	}
}
