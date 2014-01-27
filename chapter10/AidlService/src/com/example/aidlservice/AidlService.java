package com.example.aidlservice;

import java.util.Timer;
import java.util.TimerTask;

import com.example.aidlservice.ICat.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlService extends Service {
	String[] colors = new String[] { "��ɫ", "��ɫ", "��ɫ" };
	double[] weights = new double[] { 2.3, 3.1, 1.58 };
	private String color;
	private double weight;

	private CatBinder catBinder;
	Timer timer = new Timer();

	@Override
	public void onCreate() {
		super.onCreate();
		catBinder = new CatBinder();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// ����ظı�service����ڵ�color��weight���Ե�ֵ
				int rand = (int) (Math.random() * 3);
				color = colors[rand];
				weight = weights[rand];
				System.out.println("---------" + rand);
			}
		}, 0, 800);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		/**
		 * ����CatBinder�����ڰ󶨱���Service����£�
		 * ��catBinder��ֱ�Ӵ����ͻ��˵�ServiceConnected�����ServiceConnected
		 * ()�����ĵڶ����������ڰ�Զ��Service�������
		 * ��ֻ��catBinder����Ĵ������ͻ��˵�ServiceConnected�����ServiceConnected()�����ĵڶ�������
		 */
		return catBinder;
	}
	
	@Override
	public void onDestroy() {
		timer.cancel();
	}

	/**
	 * �̳�Stub,Ҳ����ʵ����ICat�ӿڣ���ʵ����IBinder�ӿ�
	 * 
	 * @author pengcx
	 * 
	 */
	public class CatBinder extends Stub {

		@Override
		public String getColor() throws RemoteException {
			return color;
		}

		@Override
		public double getWeight() throws RemoteException {
			return weight;
		}

	}
}
