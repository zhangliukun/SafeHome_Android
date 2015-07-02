package cn.zalezone.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.FunctionState;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.setting.TagInfo;
import cn.zalezone.ui.adapter.PropertyInfoVertifyListAdapter;
import cn.zalezone.util.JsonHelper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyActivity extends BaseActivity {

    EditText             searchHouseCode;
    Button               searchButton;
    ListView             houseInfoListView;

    Button               actionBarBackButton;
    TextView             actionBarTextView;
    Button               actionBarHomeButton;

    HttpClient           httpClient;
    HttpPost             httpPost;
    
    Handler myHandler;

    //ArrayList<HouseInfo> housePropertyInfolist;
    ArrayList<LeaseHouseInfo> LeaseHouseInfolist;
    
    PropertyInfoVertifyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_property);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_actionbar);
        
        
        // 初始化数据
        initData();
        // 初始化UI
        initUI();
        
        initHandler();

    }

    private void initHandler() {
        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                // 判断类型
                switch (msg.what) {
                   
                    case OperationState.GET_DATA_FINISHED:
                        
                        JSONArray jsonArray;
                        try {
                            jsonArray = new JSONArray((String)msg.obj);
                            //清除原来的List列表中的数据
                            LeaseHouseInfolist.clear();
                            
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                LeaseHouseInfo info = JsonHelper.JsonToObject(jsonObject.toString(), 
                                        LeaseHouseInfo.class);
                                LeaseHouseInfolist.add(info);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        listAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

        };
    }
        

    @Override
    public void initUI() {

        searchHouseCode = (EditText) findViewById(R.id.edit_search_homenumber);
        searchButton = (Button) findViewById(R.id.button_search);
        houseInfoListView = (ListView) findViewById(R.id.home_property_info_list);

        // actionBar
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        
        actionBarHomeButton.setVisibility(View.GONE);
        actionBarTextView.setText("产权审核");
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
                
                if (searchHouseCode.getText()!=null) {
                    String houseCode = searchHouseCode.getText().toString();
                    queryFromServer(houseCode, GlobalData.serviceCenterId);
                }
                
            }
        });

        // 初始化listview的adapter
        listAdapter = new PropertyInfoVertifyListAdapter(
                LeaseHouseInfolist, this,FunctionState.PROPERTY_VERIFY);
        // 绑定adapter
        houseInfoListView.setAdapter(listAdapter);
        houseInfoListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {}
        });
        
        
        
        
    }

    protected void queryFromServer(final String houseCode, final int serviceCenterId) {
        // 进行http连接的httpclient设置
        httpClient = new DefaultHttpClient();
        // 设置请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        // 设置读取超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);

        // 在线程中进行网络操作
        new Thread() {

            @Override
            public void run() {
                try {

                    JSONObject requestJsonObject = new JSONObject();
                    requestJsonObject.put("houseCode", houseCode);
                    requestJsonObject.put("serviceCenterId", serviceCenterId);

                    Log.d(TagInfo.HTTP_DATA, requestJsonObject.toString());

                    StringEntity entity = new StringEntity(requestJsonObject.toString());
                    httpPost = new HttpPost(RequestUrlInfo.HTTP_SEARCH_COMMIT_FINISH);
                    httpPost.setEntity(entity);
                    // 发送http请求
                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    Log.d("httpresponse", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {

                        // 读取返回的数据
                        // String data = EntityUtils.toString(
                        // httpResponse.getEntity(), "UTF-8");
                        // Log.d(TagInfo.HTTP_DATA, data);

                        StringBuilder builder = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader
                                (new InputStreamReader(httpResponse.getEntity().getContent()));
                        for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
                            builder.append(s);
                        }

                        Log.d(TagInfo.JSONDATA_TAG, builder.toString());
                        // 检测服务器返回的状态码
                        verifyData(builder.toString());
                    }

                } catch (Exception e) {
                    Message msg = new Message();
                    // msg.what = NET_ERROR;
                    // myHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }

        }.start();
    }

    
    //这里的list在数据量大时可能会报错，具体待测试
    protected void verifyData(String data) throws Exception {
        
        
        Message msg = new Message();
        msg.what = OperationState.GET_DATA_FINISHED;
        msg.obj = data;
        myHandler.sendMessage(msg);
        
        // List list = (List) JSONArray.toCollection(jsonArray, LeaseHouseInfo.class);
        // Iterator<LeaseHouseInfo> iterator = list.iterator();
        // while (iterator.hasNext()) {
        // LeaseHouseInfo leaseHouseInfo = iterator.next();
        // System.out.println(leaseHouseInfo.toString());
        // }
    }

    @Override
    public void initData() {
        LeaseHouseInfolist = new ArrayList<LeaseHouseInfo>();
//        housePropertyInfolist.add(new HouseInfo("188998121", "诺亚方舟", "江苏省苏州市某某区某某陆某某街"));
    }

}
