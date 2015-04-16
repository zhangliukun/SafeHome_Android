package cn.zalezone.ui;

import java.util.ArrayList;

import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.TagInfo;
import cn.zalezone.ui.adapter.MainActivityListAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends BaseActivity{

	Button exitButton;
	ListView mainListView;
	
	//mainactivity中的模块选项名称
	ArrayList<String> mainListItem;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initData();
		initUI(); 
		
	}

	@Override
	public void initUI() {
		
		//初始化组件
		exitButton = (Button)findViewById(R.id.exit_button);
		mainListView = (ListView)findViewById(R.id.mainactivity_listview);
		
		//初始化样式adapter
		MainActivityListAdapter mainAdapter = new MainActivityListAdapter(mainListItem, this);
		mainListView.setAdapter(mainAdapter);
		
		//增加监听器
			//回到登录界面的按钮
		exitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(loginIntent);
				finish();
			}
		});
		
			//对listview的监听
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				switch (position) {
				case 0:
					Intent propertyIntent = new Intent(getApplicationContext(),PropertyActivity.class);
					startActivity(propertyIntent);
					break;
					
				case 1:
					Intent houseIntent = new Intent(getApplicationContext(), HouseActivity.class); 
					startActivity(houseIntent);
				case 2:
					Intent lookHouseIntent = new Intent(getApplicationContext(), LookHouseActivity.class); 
					startActivity(lookHouseIntent);
				default:
					break;
				}
				
			}
		});
		
		
	}

	@Override
	public void initData() {
		mainListItem = new ArrayList<String>();
		mainListItem.add("产权审核");
		mainListItem.add("房屋审核");
		mainListItem.add("看房登记");
	}

}
