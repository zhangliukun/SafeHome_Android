package cn.zalezone.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.domian.LeaseSatisfactionResult;
import cn.zalezone.domian.LookHouseInfo;
import cn.zalezone.domian.SysSatisfactionResultSet;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.ui.adapter.LookHouseCodeNameAdapter;
import cn.zalezone.util.JsonHelper;
import cn.zalezone.web.InsertVerifyComment;
import cn.zalezone.web.PostForLeaseHouseList;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LookHouseStatusActivity extends BaseActivity {

    EditText vertifyCommentEditText;

    Button   actionBarBackButton;
    Button   actionBarHomeButton;
    TextView actionBarTitle;

    Button   accessButton;
    Button   notAccessButton;
    
    ListView satisfactionListView;
    
    //满意度回馈表的list
    ArrayList<LeaseSatisfactionResult> arrayList;

    //自定义的保存变量的实体
    LookHouseInfo lookHouseInfo;
    
    //满意度回馈表代码listAdapter
    LookHouseCodeNameAdapter lookHouseCodeNameAdapter;
    
    Handler  myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置actionBar
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_lookhouse_status);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_actionbar);

        
        
        initData();
        initHandler();
        initUI();
    }

    private void initHandler() {
        
        myHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case OperationState.GET_DATA_FINISHED:

                        // 清除原来的List列表中的数据
                        arrayList.clear();

                        try {
                            JSONArray jsonArray = new JSONArray((String) msg.obj);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                SysSatisfactionResultSet info = JsonHelper.JsonToObject(jsonObject.toString(),
                                        SysSatisfactionResultSet.class);
                                LeaseSatisfactionResult leaseSatisfactionResult = new LeaseSatisfactionResult();
                                leaseSatisfactionResult.setIntentionAppointId(lookHouseInfo.getIntentionAppointId());
                                leaseSatisfactionResult.setIntentionAppointCd(lookHouseInfo.getIntentionAppointCd());
                                leaseSatisfactionResult.setSatisfactionResultCd(info.getSatisfactionResultCd());
                                leaseSatisfactionResult.setSatisfactionResultNm(info.getSatisfactionResultNm());
                                leaseSatisfactionResult.setSatisfactionFlag("00");
                                arrayList.add(leaseSatisfactionResult);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        lookHouseCodeNameAdapter.notifyDataSetChanged();// 通知listview更新
                        break;

                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void initUI() {

        satisfactionListView = (ListView)findViewById(R.id.codeList);
        
        vertifyCommentEditText = (EditText) findViewById(R.id.edit_opinion);

        accessButton = (Button) findViewById(R.id.access_button);
        notAccessButton = (Button) findViewById(R.id.not_access_button);

        // actionBar
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarTitle = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarTitle.setText("看房登记");
        
        //绑定listview
        satisfactionListView.setAdapter(lookHouseCodeNameAdapter);
        
        
        
        JSONObject uselessInfo = new JSONObject();
        try {
            uselessInfo.put("uselessInfo", "uselessInfo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        //开始向服务端请求满意度回馈表的代码信息
        new PostForLeaseHouseList(myHandler, RequestUrlInfo.HTTP_GET_SATIFICATION_NAME,uselessInfo.toString()).start();
        
        
        // 返回上一界面
        actionBarBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LookHouseIntent = new Intent(getApplicationContext(), LookHouseActivity.class);
                startActivity(LookHouseIntent);
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
        
      //初始化Button
        accessButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = vertifyCommentEditText.getText().toString();
                JSONObject requestJsonObject = new JSONObject();
                try {
                    SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(new Date());
                    
                    String satisfactionListString = JsonHelper.ObjectToJson(arrayList);
                    
                    //看房预约编号和房屋编号
                    requestJsonObject.put("intentionAppointCd", lookHouseInfo.getIntentionAppointCd());
                    requestJsonObject.put("houseCode", lookHouseInfo.getHouseCode());
                    
                    //满意度jsonList列表
                    requestJsonObject.put("satisfactionList", satisfactionListString);
                    //看房登记时间,与实际看房时间同
                    requestJsonObject.put("sRRTime", str);
                    //发给服务器让之判断是否成交
                    requestJsonObject.put("leaseIsDeal", "1");
                    //看房登记备注
                    requestJsonObject.put("memo", comment);
                    //所属站点ID
                    requestJsonObject.put("serviceCenterId", GlobalData.serviceCenterId);
                    //登记人Id
                    requestJsonObject.put("userId", GlobalData.userId);
                    requestJsonObject.put("messageContent","房屋编号为"+lookHouseInfo.getHouseCode()+"的看房信息等待租赁处理。");
                    requestJsonObject.put("isRead", 0);
                    
                    new InsertVerifyComment(myHandler,RequestUrlInfo.HTTP_LOOKHOUSE_DEAL, requestJsonObject.toString()).start();
                   
                    Intent LookHouseIntent = new Intent(getApplicationContext(), LookHouseActivity.class);
                    startActivity(LookHouseIntent);
                    finish();
                    
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
                        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String str = sdf.format(new Date());
                        
                      //看房预约编号和房屋编号
                        requestJsonObject.put("intentionAppointCd", lookHouseInfo.getIntentionAppointCd());
                        requestJsonObject.put("houseCode", lookHouseInfo.getHouseCode());
                        
                        //满意度jsonList列表
                        String satisfactionListString = JsonHelper.ObjectToJson(arrayList);
                        requestJsonObject.put("satisfactionList", satisfactionListString);
                        //看房登记时间,与实际看房时间同
                        requestJsonObject.put("sRRTime", str);
                        //发给服务器让之判断是否成交
                        requestJsonObject.put("leaseIsDeal", "2");
                        //看房登记备注
                        requestJsonObject.put("memo", comment);
                        //所属站点ID
                        requestJsonObject.put("serviceCenterId", GlobalData.serviceCenterId);
                        //登记人Id
                        requestJsonObject.put("userId", GlobalData.userId);
                        requestJsonObject.put("messageContent","房屋编号为"+lookHouseInfo.getHouseCode()+"的看房信息等待租赁处理。");
                        requestJsonObject.put("isRead", 0);
                        
                        
                        new InsertVerifyComment(myHandler,RequestUrlInfo.HTTP_LOOKHOUSE_DEAL, requestJsonObject.toString()).start();
                       
                        Intent LookHouseIntent = new Intent(getApplicationContext(), LookHouseActivity.class);
                        startActivity(LookHouseIntent);
                        finish();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "请输入不成交的意见", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initData() {
        
        //获取传过来的LookHouseInfo的值
        Intent intent = getIntent();
        lookHouseInfo = (LookHouseInfo) intent.getSerializableExtra("lookHouseInfo");
        
        arrayList = new ArrayList<LeaseSatisfactionResult>();
        
        //绑定adapter
        lookHouseCodeNameAdapter = new LookHouseCodeNameAdapter(arrayList,this);
        
        

    }

}
