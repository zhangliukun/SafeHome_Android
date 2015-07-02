package cn.zalezone.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.domian.PatrolRecordInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.FunctionState;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.ui.adapter.PatrolInfoRecordListAdapter;
import cn.zalezone.util.JsonHelper;
import cn.zalezone.web.PostForLeaseHouseList;

public class PatrolActivity extends BaseActivity implements OnScrollListener {

    EditText                    searchHouseNumber;
    Button                      searchButton;
    ListView                    houseInfoListView;

    // 加载更多
    View                        loadMoreView;
    Button                      loadMoreButton;

    Button                      actionBarBackButton;
    TextView                    actionBarTextView;
    Button                      actionBarHomeButton;

    PatrolInfoRecordListAdapter listAdapter;

    Handler                     myHandler;
    ArrayList<PatrolRecordInfo> patrolRecordInfolist;
    ArrayList<PatrolRecordInfo> tempList;

    private int                 visibleLastIndex = 0; // 最后的可视项索引
    private int                 visibleItemCount;    // 当前窗口可见项总数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_property);
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

        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case OperationState.GET_DATA_FINISHED:

                        // 清除原来的List列表中的数据
                        patrolRecordInfolist.clear();
                        tempList.clear();
                        
                        //采用临时list来存储全部，加载更多时再放入界面中去
                        try {
                            JSONArray jsonArray = new JSONArray((String) msg.obj);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                PatrolRecordInfo info = JsonHelper.JsonToObject(
                                        jsonObject.toString(),
                                        PatrolRecordInfo.class);
                                tempList.add(info);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        
                        loadData();
                        
                        listAdapter.notifyDataSetChanged();// 通知listview更新
                        loadMoreButton.setVisibility(View.VISIBLE);
                        break;

                    default:
                        break;
                }
            }
        };
    }
    
    /**
     * load data
     */
    
    public void loadData(){
        for (int i = 0; i < 1; i++) {
            if (tempList.size()==0) {
                break;
            }else {
                patrolRecordInfolist.add(tempList.get(0));
                tempList.remove(0);
            }
        }
        if (tempList.size()==0) {
            loadMoreButton.setText("没有更多数据了"); // 设置按钮文字loading
        }
        else{
            loadMoreButton.setText("加载更多"); // 设置按钮文字loading
        }
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
        actionBarTextView.setText("巡查记录");

        loadMoreView = getLayoutInflater().inflate(R.layout.loadmorebutton, null);
        loadMoreButton = (Button) loadMoreView.findViewById(R.id.loadMoreButton);
        loadMoreButton.setVisibility(View.GONE);
        loadMoreButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                loadMoreButton.setText("正在加载..."); // 设置按钮文字loading

                
                loadData();
                
                listAdapter.notifyDataSetChanged();// 通知listview更新
            }
        });

        houseInfoListView.addFooterView(loadMoreView);// 设置底部视图
        
        houseInfoListView.setOnScrollListener(this);     //添加滑动监听    

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
                if (searchHouseNumber.getText() != null) {
                    houseCode = searchHouseNumber.getText().toString();
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(new Date());
                    // 传递时间，房屋编号，用户ID
                    jsonObject.put("houseCode", houseCode);
                    jsonObject.put("serviceCenterId",
                            GlobalData.serviceCenterId);
                    jsonObject.put("currentDate", str);

                    new PostForLeaseHouseList(myHandler,
                            RequestUrlInfo.HTTP_SEARCH_PATROL_RECORD,
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
        patrolRecordInfolist = new ArrayList<PatrolRecordInfo>();
        tempList = new ArrayList<PatrolRecordInfo>();//临时性数组
        // 初始化listview的adapter
        listAdapter = new PatrolInfoRecordListAdapter(patrolRecordInfolist,
                this, FunctionState.CHECK_RECORD);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = listAdapter.getCount() - 1;    //数据集最后一项的索引    
        int lastIndex = itemsLastIndex + 1;             //加上底部的loadMoreView项    
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {    
            //如果是自动加载,可以在这里放置异步加载数据的代码    
            Log.e("LOADMORE", "loading...");
            //主动去调用加载方法
            loadMoreButton.performClick();
        }    

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;    
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;    
    }

}
