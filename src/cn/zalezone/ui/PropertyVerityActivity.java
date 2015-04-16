package cn.zalezone.ui;

import cn.zalezone.safehome_android.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PropertyVerityActivity extends BaseActivity {

	TextView propertyPersonTextView;
	TextView propertyCertNumberTextView;
	TextView certificationNumberTextView;
	TextView telephoneNumberTextView;
	TextView purposeUseTextView;
	TextView areaTextView;
	TextView locationTextView;

	EditText VertifyCommentEditText;

	Button backButton;
	Button mainActivityButton;
	Button accessButton;
	Button notAccessButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_property_vertify);

		initData();
		initUI();
	}

	@Override
	public void initUI() {
		//初始化textView
		propertyPersonTextView = (TextView) findViewById(R.id.property_person);
		propertyCertNumberTextView = (TextView) findViewById(R.id.property_cert_number);
		certificationNumberTextView = (TextView) findViewById(R.id.identification_card);
		telephoneNumberTextView = (TextView) findViewById(R.id.telephone);
		purposeUseTextView = (TextView) findViewById(R.id.purpose_use);
		areaTextView = (TextView) findViewById(R.id.area_size);
		locationTextView = (TextView) findViewById(R.id.location);
		
		//审核意见框
		VertifyCommentEditText = (EditText)findViewById(R.id.audit_opinion);
		
		//按钮
		backButton = (Button)findViewById(R.id.back_button);
		mainActivityButton =(Button)findViewById(R.id.main_activity_button);
		accessButton = (Button)findViewById(R.id.access_button);
		notAccessButton = (Button)findViewById(R.id.not_access_button);
		
		//返回上一界面
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent propertyIntent = new Intent(getApplicationContext(), PropertyActivity.class);
				startActivity(propertyIntent);
				finish();
			}
		});
		
		//返回主界面
		mainActivityButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(mainIntent);
				finish();
			}
		});
		
		accessButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		notAccessButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}

	@Override
	public void initData() {

	}

}
