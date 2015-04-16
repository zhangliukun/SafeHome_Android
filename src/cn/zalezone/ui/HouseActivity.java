package cn.zalezone.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import cn.zalezone.domian.HouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.ui.adapter.PropertyInfoVertifyListAdapter;


/**
 * 房屋审核搜索页面
 * @author zlk
 *
 */

public class HouseActivity extends BaseActivity {

	EditText searchHouseNumber;
	Button backButton;
	Button searchButton;
	ListView houseInfoListView;

	ArrayList<HouseInfo> housePropertyInfolist;

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
		PropertyInfoVertifyListAdapter listAdapter = new PropertyInfoVertifyListAdapter(
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
		housePropertyInfolist = new ArrayList<HouseInfo>();
		housePropertyInfolist.add(new HouseInfo("188998121", "诺亚方舟", "江苏省苏州市某某区某某陆某某街"));
	}

}
