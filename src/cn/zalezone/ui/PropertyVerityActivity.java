package cn.zalezone.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.setting.TagInfo;
import cn.zalezone.util.JsonHelper;
import cn.zalezone.web.PostForLeaseHouseList;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyVerityActivity extends BaseActivity {

    TextView       propertyPersonTextView;
    TextView       propertyCertNumberTextView;
    TextView       certificationNumberTextView;
    TextView       telephoneNumberTextView;
    TextView       purposeUseTextView;
    TextView       areaTextView;
    TextView       locationTextView;

    EditText       vertifyCommentEditText;

    Button         actionBarBackButton;
    Button         actionBarHomeButton;
    TextView       actionBarTitle;

    Button         accessButton;
    Button         notAccessButton;

    Handler        myHandler;
    
    HttpClient           httpClient;
    HttpPost             httpPost;

    // 产权审核的房屋信息
    LeaseHouseInfo houseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化标题actionbar
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_house_property_vertify);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_actionbar);

        initHandler();

        initData();
        initUI();
    }

    private void initHandler() {
        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                // 判断类型
                switch (msg.what) {
                    case OperationState.INSERT_DATA_SUCCESS:
                        Toast.makeText(getApplicationContext(), "插入成功", Toast.LENGTH_SHORT).show();;
                    default:
                        break;
                }
            }

        };
    }

    @Override
    public void initUI() {
        // 初始化textView
        propertyPersonTextView = (TextView) findViewById(R.id.property_person);
        propertyCertNumberTextView = (TextView) findViewById(R.id.property_cert_number);
        certificationNumberTextView = (TextView) findViewById(R.id.identification_card);
        telephoneNumberTextView = (TextView) findViewById(R.id.telephone);
        purposeUseTextView = (TextView) findViewById(R.id.purpose_use);
        areaTextView = (TextView) findViewById(R.id.area_size);
        locationTextView = (TextView) findViewById(R.id.area_location);

        // 设置textview的值
        if (houseInfo.getPropertyOwner() != null) {
            propertyPersonTextView.setText(houseInfo.getPropertyOwner());
        }
        if (houseInfo.getPropertyNo() != null) {
            propertyCertNumberTextView.setText(houseInfo.getPropertyNo());
        }
        if (houseInfo.getPoidCardNo() != null) {
            certificationNumberTextView.setText(houseInfo.getPoidCardNo());
        }
        if (houseInfo.getPophone() != null) {
            telephoneNumberTextView.setText(houseInfo.getPophone());
        }
        if (houseInfo.getUsingProperties() != null) {
            purposeUseTextView.setText(houseInfo.getUsingProperties());
        }
        if (houseInfo.getProportionM3() != null) {
            areaTextView.setText(houseInfo.getProportionM3().toString());
        }
        if (houseInfo.getHouseAdd() != null) {
            locationTextView.setText(houseInfo.getHouseAdd());
        }

        // 审核意见框
        vertifyCommentEditText = (EditText) findViewById(R.id.audit_opinion);

        // ActionBar
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarTitle = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarTitle.setText("产权审核");

        // 按钮
        accessButton = (Button) findViewById(R.id.access_button);
        notAccessButton = (Button) findViewById(R.id.not_access_button);

        // 返回上一界面
        actionBarBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent propertyIntent = new Intent(getApplicationContext(), PropertyActivity.class);
                startActivity(propertyIntent);
                finish();
            }
        });

        // 返回主界面
        actionBarHomeButton.setOnClickListener(new OnClickListener() {
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
                 
                
                String comment = vertifyCommentEditText.getText().toString();
                JSONObject requestJsonObject = new JSONObject();
                try {
                    SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(new Date());
                    requestJsonObject.put("houseCode", houseInfo.getHouseCode());
                    requestJsonObject.put("userId", GlobalData.userId);
                    requestJsonObject.put("propertyCheckTime", str);
                    requestJsonObject.put("propertyCheckResult", 1);
                    requestJsonObject.put("propertyCheckOpinion", comment);
                    //发给服务器让之判断是否通过
                    requestJsonObject.put("isAccess", "access");
                    
                  //插入系统信息表中的数据
                    requestJsonObject.put("receiveUserId", houseInfo.getLeaserUserId());
                    if (houseInfo.getTenantUserId()==null) {
                        requestJsonObject.put("sendUserId", -1);
                    }
                    else {
                        requestJsonObject.put("sendUserId", houseInfo.getTenantUserId());
                    }
                    requestJsonObject.put("messageContent","房屋编号为"+houseInfo.getHouseCode()+"的房屋产权信息审核通过。");
                    requestJsonObject.put("isRead", 0);
                    
                    queryFromServer(requestJsonObject.toString());
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        notAccessButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = vertifyCommentEditText.getText().toString();
                if (!comment.equals("")) {
                    
                    JSONObject requestJsonObject = new JSONObject();
                    try {
                        
                        //插入待租信息表中的数据
                        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(new Date());
                        requestJsonObject.put("houseCode", houseInfo.getHouseCode());
                        requestJsonObject.put("userId", GlobalData.userId);
                        requestJsonObject.put("propertyCheckTime", str);
                        requestJsonObject.put("propertyCheckResult", 2);
                        requestJsonObject.put("propertyCheckOpinion", comment);
                      //发给服务器让之判断是否通过
                        requestJsonObject.put("isAccess", "no_access");
                        
                        //插入系统信息表中的数据
                        requestJsonObject.put("receiveUserId", houseInfo.getLeaserUserId());
                        requestJsonObject.put("sendUserId", houseInfo.getTenantUserId());
                        requestJsonObject.put("messageContent","房屋编号为"+houseInfo.getHouseCode()+"的房屋产权信息审核未通过。原因为:"+comment);
                        requestJsonObject.put("isRead", 0);
                        
                        queryFromServer(requestJsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "请输入不通过的意见", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    
    protected void queryFromServer(final String jsonString) {
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

                    Log.d(TagInfo.HTTP_DATA, jsonString);
                    StringEntity entity = new StringEntity(jsonString,"UTF-8");
                    httpPost = new HttpPost(RequestUrlInfo.HTTP_INSERT_PROPERTY_VERIFY_COMMENT);
                    httpPost.setEntity(entity);
                    // 发送http请求
                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    Log.d("httpresponse", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {

                        StringBuilder builder = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader
                                (new InputStreamReader(httpResponse.getEntity().getContent()));
                        for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
                            builder.append(s);
                        }

                        // 检测服务器返回的状态码
                       // verifyData(builder.toString());
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

    

    @Override
    public void initData() {
        Intent intent = getIntent();
        houseInfo = (LeaseHouseInfo) intent.getSerializableExtra("leaseHouseInfo");
    }

}
