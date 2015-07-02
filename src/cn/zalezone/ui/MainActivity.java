package cn.zalezone.ui;

import java.util.ArrayList;

import cn.zalezone.domian.BaseUser;
import cn.zalezone.domian.GlobalData;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.TagInfo;
import cn.zalezone.ui.adapter.MainActivityListAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	ListView mainListView;

	Button actionBarBackButton;
	TextView actionBarTextView;
	Button actionBarHomeButton;

	// mainactivity中的模块选项名称
	ArrayList<String> mainListItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.layout_actionbar);
		initData();
		initUI();

	}

	@Override
	public void initUI() {

		// 初始化组件
		mainListView = (ListView) findViewById(R.id.mainactivity_listview);

		// actionBar
		actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
		actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
		actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);

		actionBarBackButton.setVisibility(View.GONE);
		actionBarTextView.setText("首页");
		actionBarHomeButton.setBackgroundResource(R.drawable.main_back_icon);

		// 初始化样式adapter
		MainActivityListAdapter mainAdapter = new MainActivityListAdapter(
				mainListItem, this);
		mainListView.setAdapter(mainAdapter);

		// 增加监听器
		// 回到登录界面的按钮
		actionBarHomeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent loginIntent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(loginIntent);
				GlobalData.isAutoStart = 0;
				finish();
			}
		});

		// 对listview的监听
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					Intent propertyIntent = new Intent(getApplicationContext(),
							PropertyActivity.class);
					startActivity(propertyIntent);
					break;
				case 1:
					Intent houseIntent = new Intent(getApplicationContext(),
							HouseActivity.class);
					startActivity(houseIntent);
					break;
				case 2:
					Intent lookHouseIntent = new Intent(
							getApplicationContext(), LookHouseActivity.class);
					startActivity(lookHouseIntent);
					break;
				case 3:
					Intent patrolRecordIntent = new Intent(
							getApplicationContext(), PatrolActivity.class);
					startActivity(patrolRecordIntent);
					break;
				case 4:
					Intent searchHouseIntent = new Intent(
							getApplicationContext(), SearchActivity.class);
					startActivity(searchHouseIntent);
					break;
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
		mainListItem.add("巡查记录");
		mainListItem.add("房屋查询");
	}

}
