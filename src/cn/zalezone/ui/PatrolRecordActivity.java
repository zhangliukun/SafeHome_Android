package cn.zalezone.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.domian.LeaseTenantUserInfo;
import cn.zalezone.domian.PatrolRadioGroup;
import cn.zalezone.domian.PatrolRecordInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.ui.adapter.PatrolRadioGroupAdapter;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PatrolRecordActivity extends BaseActivity {

    TextView            villagename;
    TextView            leaseusername;
    TextView            leaseuserphone;
    TextView            tenantusername;
    TextView            tenantuserphone;
    TextView            arealocation;
    TextView            houseAdd;
    TextView            buildingInfo;
    TextView            floorInfo;

    EditText            recordcontent;
    Button              accessButton;
    
    //RadioButton
    RadioButton satification;
    RadioButton common;
    RadioButton nosatification;
    RadioGroup  radioGroup;
    
    ListView recordListView;
    PatrolRadioGroupAdapter patrolRadioGroupAdapter;
    
    ArrayList<PatrolRadioGroup> patrolRadioGroupLists;
    
    // actionBar
    Button              actionBarBackButton;
    TextView            actionBarTextView;
    Button              actionBarHomeButton;

    // Handler
    Handler             myHandler;

    // 传过来的实体值
    PatrolRecordInfo    patrolRecordInfo;
    LeaseTenantUserInfo leaseTenantUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置actionBar
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_patrol_record);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.layout_actionbar);

        initData();
        initHandler();
        initUI();
    }

    private void initHandler() {

        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {

                    case OperationState.GET_DATA_FINISHED:
                        try {
                            JSONObject jsonObject = new JSONObject((String) msg.obj);
                            leaseTenantUserInfo = JsonHelper.JsonToObject(
                                    jsonObject.toString(),
                                    LeaseTenantUserInfo.class);
                            leaseusername.setText(leaseTenantUserInfo.getLeaseUserName());
                            leaseuserphone.setText(leaseTenantUserInfo.getLeaseUserPhone());
                            tenantusername.setText(leaseTenantUserInfo.getTenantUserName());
                            tenantuserphone.setText(leaseTenantUserInfo.getTenantUserPhone());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void initUI() {

        villagename = (TextView) findViewById(R.id.village_name);
        leaseusername = (TextView) findViewById(R.id.lease_people);
        leaseuserphone = (TextView) findViewById(R.id.lease_telephone);
        tenantusername = (TextView) findViewById(R.id.wanted_people);
        tenantuserphone = (TextView) findViewById(R.id.wanted_telephone);
        houseAdd = (TextView) findViewById(R.id.area_location);

        buildingInfo = (TextView) findViewById(R.id.building_add_info);
        floorInfo = (TextView) findViewById(R.id.floor_info);

        recordcontent = (EditText) findViewById(R.id.record_content);
        accessButton = (Button) findViewById(R.id.access_button);
        
        recordListView = (ListView)findViewById(R.id.recordList);
        recordListView.setAdapter(patrolRadioGroupAdapter);

        // actionBar
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarTextView.setText("巡查记录");
        // 返回上一界面
        actionBarBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patrolIntent = new Intent(getApplicationContext(),
                        PatrolActivity.class);
                startActivity(patrolIntent);
                finish();
            }
        });
        // 返回主界面
        actionBarHomeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        // 用来获取求租人和承租人姓名和电话
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("leaseUserId", patrolRecordInfo.getLeaseUserId());
            jsonObject.put("tenantUserId", patrolRecordInfo.getTenantUserId());
            jsonObject.put("houseInfoId", patrolRecordInfo.getHouseInfoId());
            new PostForLeaseHouseList(myHandler,
                    RequestUrlInfo.HTTP_GET_USERINFO,
                    jsonObject.toString()).start();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        // 初始化信息
        villagename.setText(patrolRecordInfo.getVillageName());
        houseAdd.setText(patrolRecordInfo.getHouseAdd());
        buildingInfo.setText(patrolRecordInfo.getBuildingNo() + "幢"
                + patrolRecordInfo.getBuildingUnit() + "单元"
                + patrolRecordInfo.getBuildingRoom() + "室");
        floorInfo.setText(patrolRecordInfo.getFloorNum() + "/"
                + patrolRecordInfo.getFloorCnt());

        // 初始化Button
        accessButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = recordcontent.getText().toString();
                JSONObject requestJsonObject = new JSONObject();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(new Date());

                    // 传递数据到巡查记录表中
                    requestJsonObject.put("houseCode",
                            patrolRecordInfo.getHouseCode());
                    requestJsonObject.put("recorderUserId", GlobalData.userId);
                    requestJsonObject.put("serviceCenterId",
                            GlobalData.serviceCenterId);
                    requestJsonObject.put("recordTime", str);
                    requestJsonObject.put("recordContent", comment);

                    requestJsonObject.put("leaseUserId",
                            patrolRecordInfo.getLeaseUserId());
                    requestJsonObject.put("tenantUserId",
                            patrolRecordInfo.getTenantUserId());
                    requestJsonObject.put("dealInfoId",
                            patrolRecordInfo.getDealInfoId());
                    requestJsonObject.put("houseInfoId",
                            patrolRecordInfo.getHouseInfoId());
                    //用户反馈字段
                    requestJsonObject.put("leaserCondition", patrolRadioGroupLists.get(0).getValue());
                    requestJsonObject.put("leaserPeople", patrolRadioGroupLists.get(1).getValue());
                    requestJsonObject.put("tenantProple", patrolRadioGroupLists.get(2).getValue());
                    

                    new InsertVerifyComment(myHandler,
                            RequestUrlInfo.HTTP_INSERT_PARTOL_RECORD,
                            requestJsonObject.toString()).start();
                    
                    Intent PatrolActivityIntent = new Intent(getApplicationContext(), PatrolActivity.class);
                    startActivity(PatrolActivityIntent);
                    finish();
                    

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        patrolRecordInfo = (PatrolRecordInfo) intent
                .getSerializableExtra("patrolRecordInfo");
        
        patrolRadioGroupLists = new ArrayList<PatrolRadioGroup>();
        patrolRadioGroupLists.add(new PatrolRadioGroup("出租条件", "满足","不满足",0));
        patrolRadioGroupLists.add(new PatrolRadioGroup("出租人", "一致","不一致",0));
        patrolRadioGroupLists.add(new PatrolRadioGroup("承租人", "一致","不一致",0));
        
        patrolRadioGroupAdapter = new PatrolRadioGroupAdapter(patrolRadioGroupLists, this);
    }

}
