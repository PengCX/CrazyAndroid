package com.example.aidlservice;

import java.util.Timer;
import java.util.TimerTask;

import com.example.aidlservice.ICat.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlService extends Service {
	String[] colors = new String[] { "红色", "黄色", "黑色" };
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
				// 随机地改变service组件内的color，weight属性的值
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
		 * 返回CatBinder对象，在绑定本地Service情况下，
		 * 该catBinder会直接传给客户端的ServiceConnected对象的ServiceConnected
		 * ()方法的第二个参数；在绑定远程Service的情况下
		 * ，只将catBinder对象的代理传给客户端的ServiceConnected对象的ServiceConnected()方法的第二个参数
		 */
		return catBinder;
	}
	
	@Override
	public void onDestroy() {
		timer.cancel();
	}

	/**
	 * 继承Stub,也就是实现了ICat接口，并实现了IBinder接口
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
