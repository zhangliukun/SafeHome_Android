package cn.zalezone.ui;

import java.util.ArrayList;

import cn.zalezone.domian.PropertyHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.ui.adapter.PropertyInfoListAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class PropertyActivity extends BaseActivity {

	EditText searchHouseNumber;
	Button backButton;
	Button searchButton;
	ListView houseInfoListView;

	ArrayList<PropertyHouseInfo> housePropertyInfolist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_property);
		// 初始化数据
		initData();
		// 初始化UI
		initUI();

	}

	@Override
	public void initUI() {
		
		
		
		searchHouseNumber = (EditText) findViewById(R.id.edit_search_homenumber);
		searchButton = (Button) findViewById(R.id.button_search);
		backButton = (Button)findViewById(R.id.back_button);
		houseInfoListView = (ListView) findViewById(R.id.home_property_info_list);

		
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(mainIntent);
				finish();
				
			}
		});
		
		//初始化listview的adapter
		PropertyInfoListAdapter listAdapter = new PropertyInfoListAdapter(
				housePropertyInfolist,this);
		//绑定adapter
		houseInfoListView.setAdapter(listAdapter);
		houseInfoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});

	}

	@Override
	public void initData() {
		housePropertyInfolist = new ArrayList<PropertyHouseInfo>();
		housePropertyInfolist.add(new PropertyHouseInfo("188998121", "诺亚方舟", "江苏省苏州市某某区某某陆某某街"));
	}

}
