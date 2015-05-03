package cn.zalezone.ui;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.FunctionState;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.ui.adapter.PropertyInfoVertifyListAdapter;
import cn.zalezone.util.JsonHelper;
import cn.zalezone.web.PostForLeaseHouseList;

/**
 * 房屋审核搜索页面
 * 
 * @author zlk
 *
 */

public class HouseActivity extends BaseActivity {

    EditText                       searchHouseNumber;
    Button                         searchButton;
    ListView                       houseInfoListView;

    Button                         actionBarBackButton;
    TextView                       actionBarTextView;
    Button                         actionBarHomeButton;

    PropertyInfoVertifyListAdapter listAdapter;

    Handler                        myHandler;
    ArrayList<LeaseHouseInfo>      housePropertyInfolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_property);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_actionbar);

        // 初始化数据
        initData();

        // 初始化handler
        initHandler();

        // 初始化UI
        initUI();

    }

    private void initHandler() {

        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case OperationState.GET_DATA_FINISHED:
                        
                      //清除原来的List列表中的数据
                        housePropertyInfolist.clear();
                        
                        try {
                            JSONArray jsonArray = new JSONArray((String)msg.obj);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                LeaseHouseInfo info = JsonHelper.JsonToObject(jsonObject.toString(), 
                                      LeaseHouseInfo.class);
                                housePropertyInfolist.add(info);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        listAdapter.notifyDataSetChanged();//通知listview更新
                        break;

                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void initUI() {

        searchHouseNumber = (EditText) findViewById(R.id.edit_search_homenumber);
        searchButton = (Button) findViewById(R.id.button_search);
        houseInfoListView = (ListView) findViewById(R.id.home_property_info_list);
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarHomeButton.setVisibility(View.GONE);
        actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarTextView.setText("房屋审核");

        actionBarBackButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        });

        searchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String houseCode = null;
                if (searchHouseNumber.getText() != null) {
                    houseCode = searchHouseNumber.getText().toString();
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("houseCode", houseCode);
                    jsonObject.put("serviceCenterId", GlobalData.serviceCenterId);
                    new PostForLeaseHouseList(myHandler,
                            RequestUrlInfo.HTTP_SEARCH_PROPERTY_ACCESS, jsonObject.toString()).start();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        // 绑定adapter
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
        housePropertyInfolist = new ArrayList<LeaseHouseInfo>();
        // 初始化listview的adapter
        listAdapter = new PropertyInfoVertifyListAdapter(
                housePropertyInfolist, this, FunctionState.HOUSE_VERIFY);
    }

}
