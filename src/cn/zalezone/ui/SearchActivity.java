package cn.zalezone.ui;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.ui.adapter.LookHouseRegisterListAdapter;
import cn.zalezone.ui.adapter.SearchHouseRegisterListAdapter;
import cn.zalezone.util.JsonHelper;
import cn.zalezone.web.PostForLeaseHouseList;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity extends BaseActivity {

    Button                         actionBarBackButton;
    TextView                       actionBarTextView;
    Button                         actionBarHomeButton;

    Button                         searchButton;
    ListView                       houseInfoListView;
    EditText                       searchVillageName;
    EditText                       searchAddressInfo;
    EditText                       searchHouseNumber;

    Handler                        myHandler;
    SearchHouseRegisterListAdapter listAdapter;

    ArrayList<LeaseHouseInfo>      searchHouseInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_search);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.layout_actionbar);

        // 初始化数据
        initData();

        // 初始化handler
        initHandler();

        // 初始化UI
        initUI();

    }

    private void initHandler() {
        // TODO Auto-generated method stub
        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case OperationState.GET_DATA_FINISHED:

                        // 清除原来的List列表中的数据
                        searchHouseInfos.clear();

                        try {
                            JSONArray jsonArray = new JSONArray((String) msg.obj);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                LeaseHouseInfo info = JsonHelper.JsonToObject(
                                        jsonObject.toString(), LeaseHouseInfo.class);
                                searchHouseInfos.add(info);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        listAdapter.notifyDataSetChanged();// 通知listview更新
                        break;

                    default:
                        break;
                }
            }

        };
    }

    @Override
    public void initUI() {
        // TODO Auto-generated method stub
        searchVillageName = (EditText) findViewById(R.id.edit_search_villagename);
        searchHouseNumber = (EditText) findViewById(R.id.edit_search_homenumber);
        searchAddressInfo = (EditText) findViewById(R.id.edit_search_addressinfo);

        searchButton = (Button) findViewById(R.id.button_search);
        houseInfoListView = (ListView) findViewById(R.id.home_property_info_list);

        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarHomeButton.setVisibility(View.GONE);
        actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarTextView.setText("房屋查询");

        actionBarBackButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mainIntent);
                finish();

            }
        });
        searchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String houseCode = null;
                String villageName = null;
                String houseAdd = null;
                if (searchHouseNumber.getText() != null) {
                    houseCode = searchHouseNumber.getText().toString();
                }
                if (searchVillageName.getText() != null) {
                    villageName = searchVillageName.getText().toString();
                }
                if (searchAddressInfo.getText() != null) {
                    houseAdd = searchAddressInfo.getText().toString();
                }
                // 此处需要后台的请求类型做修改
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("houseCode", houseCode);
                    jsonObject.put("villageName", villageName);
                    jsonObject.put("houseAdd", houseAdd);
                    jsonObject.put("serviceCenterId",
                            GlobalData.serviceCenterId);
                    new PostForLeaseHouseList(myHandler,
                            RequestUrlInfo.HTTP_SEARCH_HOUSE,
                            jsonObject.toString()).start();

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
        // TODO Auto-generated method stub
        searchHouseInfos = new ArrayList<LeaseHouseInfo>();
        // 初始化listview的adapter
        listAdapter = new SearchHouseRegisterListAdapter(searchHouseInfos,
                this);
    }

}
