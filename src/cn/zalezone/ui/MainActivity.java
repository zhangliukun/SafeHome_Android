package cn.zalezone.ui;

import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.TagInfo;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity{

	Button exitButton;
	Button propertyButton;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		initUI();
		
	}

	@Override
	public void initUI() {
		exitButton = (Button)findViewById(R.id.exit_button);
		propertyButton = (Button)findViewById(R.id.property);
		
		//回到登录界面的按钮
		exitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(loginIntent);
				finish();
			}
		});
		
		//跳转到房屋产权的activity
		propertyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TagInfo.ONCLICK_TAG,"propertyButton onClick");
				Intent propertyIntent = new Intent(getApplicationContext(),PropertyActivity.class);
				startActivity(propertyIntent);
			}
		});
	}

	@Override
	public void initData() {
		
	}

}
