package cn.zalezone.ui;


import cn.zalezone.safehome_android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 登录页面
 * @author zlk
 *
 */

public class LoginActivity extends BaseActivity {

	EditText editName;
	EditText editPassword;
	Button loginButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//初始化UI组件
		initUI();
		
	}




	@Override
	public void initUI() {
		editName = (EditText)findViewById(R.id.edit_Name);
		editPassword = (EditText)findViewById(R.id.edit_password);
		loginButton = (Button)findViewById(R.id.login);
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(mainIntent);
				finish();
			}
		});
	}




	@Override
	public void initData() {
		
	}


}
