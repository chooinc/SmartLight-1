package com.gizwits.smartlight.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.gizwits.framework.activity.BaseActivity;
import com.gizwits.light.R;
import com.xtremeprog.xpgconnect.XPGWifiCentralControlDevice;

public class AddSubDeviceActivity extends BaseActivity implements OnClickListener{
	/** The iv back. */
	private ImageView ivBack;
	
	private XPGWifiCentralControlDevice centralControlDevice;

	/**
	 * ClassName: Enum handler_key. <br/>
	 * <br/>
	 * date: 2014-11-26 17:51:10 <br/>
	 * 
	 * @author Lien
	 */
	private enum handler_key {

		/** 开始配置 */
		START,

		/** 停止配置 */
		STOP;

	}

	/**
	 * The handler.
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			handler_key key = handler_key.values()[msg.what];
			switch (key) {
			case START:
				Log.e("add", "add "+centralControlDevice.getDid() +" "+centralControlDevice.getProductName());
				mCenter.cAddSubDevice(centralControlDevice);//发送中控自动配置指令
				break;
			default:
				break;

			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_sub);
		centralControlDevice = (XPGWifiCentralControlDevice) mXpgWifiDevice;
		initViews();
		initEvent();
	}
	
	private void initViews(){
		ivBack = (ImageView) findViewById(R.id.ivBack);
	}
	private void initEvent(){
		ivBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ivBack:
			onBackPressed();
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		handler.removeMessages(0);
		finish();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(handler_key.START.ordinal());
		super.onResume();
	}


}
