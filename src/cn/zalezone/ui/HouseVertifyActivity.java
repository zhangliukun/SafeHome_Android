package cn.zalezone.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.GlobalData;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.web.InsertVerifyComment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 房屋审核详细页面
 * 
 * @author zlk
 *
 */
public class HouseVertifyActivity extends BaseActivity {

    TextView       villageName;
    TextView       houseType;
    TextView       propertionM3;
    TextView       rentIntention;
    TextView       paymentWay;
    TextView       usingProperties;
    TextView       leaseType;
    TextView       buildingType;
    TextView       orientation;
    TextView       decoration;
    TextView       rentalNum;
    TextView       houseAdd;
    TextView       buildingInfo;
    TextView       floorInfo;

    CheckBox       haveBed;
    CheckBox       haveKitchenBath;
    CheckBox       haveNetwork;
    CheckBox       haveTv;
    CheckBox       haveRefrigerator;
    CheckBox       haveAc;
    CheckBox       haveWashingMachine;
    CheckBox       haveWaterHeater;
    
    Button accessButton;
    Button notAccessButton;
    EditText vertifyCommentEditText;
    // actionBar
    Button         actionBarBackButton;
    TextView       actionBarTextView;
    Button         actionBarHomeButton;
    
    //Handler
    Handler myHandler;

    // 传过来的实体值
    LeaseHouseInfo leaseHouseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置actionBar
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_house_vertify);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_actionbar);

        initData();
        initHandler();
        initUI();
    }

    private void initHandler() {
        myHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                
            }
            
        };
    }

    @Override
    public void initUI() {

        villageName = (TextView) findViewById(R.id.village_name);
        houseType = (TextView) findViewById(R.id.houseType);
        propertionM3 = (TextView) findViewById(R.id.propertionM3);
        rentIntention = (TextView) findViewById(R.id.rentIntention);
        paymentWay = (TextView) findViewById(R.id.paymentWay);
        usingProperties = (TextView) findViewById(R.id.using_properties);
        leaseType = (TextView) findViewById(R.id.lease_type);
        buildingType = (TextView) findViewById(R.id.building_type);
        orientation = (TextView) findViewById(R.id.orientation);
        decoration = (TextView) findViewById(R.id.decoration);
        rentalNum = (TextView) findViewById(R.id.rental_num);
        houseAdd = (TextView) findViewById(R.id.house_add);
        buildingInfo = (TextView) findViewById(R.id.building_add_info);
        floorInfo = (TextView) findViewById(R.id.floor_info);

        haveBed = (CheckBox) findViewById(R.id.have_bed);
        haveKitchenBath = (CheckBox) findViewById(R.id.have_kitchen_bath);
        haveNetwork = (CheckBox) findViewById(R.id.have_network);
        haveTv = (CheckBox) findViewById(R.id.have_tv);
        haveRefrigerator = (CheckBox) findViewById(R.id.have_refrigerator);
        haveAc = (CheckBox) findViewById(R.id.have_ac);
        haveWashingMachine = (CheckBox) findViewById(R.id.have_washing_machine);
        haveWaterHeater = (CheckBox) findViewById(R.id.have_water_heater);
        
        vertifyCommentEditText = (EditText)findViewById(R.id.edit_opinion);
        
        accessButton = (Button)findViewById(R.id.access_button);
        notAccessButton = (Button)findViewById(R.id.not_access_button);

        // actionBar
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarTextView.setText("房屋审核");
        // 返回上一界面
        actionBarBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent houseIntent = new Intent(getApplicationContext(), HouseActivity.class);
                startActivity(houseIntent);
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

        // 开始初始化数据
        villageName.setText(leaseHouseInfo.getVillageName());
        houseType.setText(leaseHouseInfo.getHouseType());
        if (leaseHouseInfo.getProportionM3() != null) {
            propertionM3.setText(leaseHouseInfo.getProportionM3().toString());
        }
        if (leaseHouseInfo.getRentIntention() != null) {
            rentIntention.setText(leaseHouseInfo.getRentIntention().toString());
        }
        paymentWay.setText(leaseHouseInfo.getPaymentWay());
        usingProperties.setText(leaseHouseInfo.getUsingProperties());
        leaseType.setText(leaseHouseInfo.getLeaseType());
        buildingType.setText(leaseHouseInfo.getBuildingType());
        orientation.setText(leaseHouseInfo.getOrientation());
        decoration.setText(leaseHouseInfo.getDecoration());
        // Integer类型的不能直接给TextView，除非多个字符串相加可自动转型
        if (leaseHouseInfo.getRentalNum() != null) {
            rentalNum.setText(leaseHouseInfo.getRentalNum().toString());
        }
        houseAdd.setText(leaseHouseInfo.getHouseAdd());
        buildingInfo.setText(leaseHouseInfo.getBuildingNo() + "幢"
                + leaseHouseInfo.getBuildingUnit() + "单元" + leaseHouseInfo.getBuildingRoom() + "室");
        floorInfo.setText(leaseHouseInfo.getFloorNum() + "/" + leaseHouseInfo.getFloorCnt());
        
        //初始化checkbox
        if(leaseHouseInfo.getHaveBed().equals("1"))
        {
            haveBed.setChecked(true);
        }
        if(leaseHouseInfo.getHaveKitchenBath().equals("1"))
        {
            haveKitchenBath.setChecked(true);
        }
        if(leaseHouseInfo.getHaveNetwork().equals("1"))
        {
            haveNetwork.setChecked(true);
        }
        if(leaseHouseInfo.getHaveTv().equals("1"))
        {
            haveTv.setChecked(true);
        }
        if(leaseHouseInfo.getHaveRefrigerator().equals("1"))
        {
            haveRefrigerator.setChecked(true);
        }
        if(leaseHouseInfo.getHaveAc().equals("1"))
        {
            haveAc.setChecked(true);
        }
        if(leaseHouseInfo.getHaveWashingMachine().equals("1"))
        {
            haveWashingMachine.setChecked(true);
        }
        if(leaseHouseInfo.getHaveWaterHeater().equals("1"))
        {
            haveWaterHeater.setChecked(true);
        }
        
        //初始化Button
        accessButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                 
                
                String comment = vertifyCommentEditText.getText().toString();
                JSONObject requestJsonObject = new JSONObject();
                try {
                    SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(new Date());
                    requestJsonObject.put("houseCode", leaseHouseInfo.getHouseCode());
                    requestJsonObject.put("userId", GlobalData.userId);
                    requestJsonObject.put("checkTime", str);
                    requestJsonObject.put("checkResult", 1);
                    requestJsonObject.put("checkOpinion", comment);
                    //发给服务器让之判断是否通过
                    requestJsonObject.put("isAccess", "access");
                    
                  //插入系统信息表中的数据
                    requestJsonObject.put("receiveUserId", leaseHouseInfo.getLeaserUserId());
                    //这里防止没有人的信息而导致json数据项没有sendUserId这个key
                    if (leaseHouseInfo.getTenantUserId()==null) {
                        requestJsonObject.put("sendUserId", -1);
                    }
                    else {
                        requestJsonObject.put("sendUserId", leaseHouseInfo.getTenantUserId());
                    }
                    requestJsonObject.put("messageContent","房屋编号为"+leaseHouseInfo.getHouseCode()+"的房屋信息审核通过。");
                    requestJsonObject.put("isRead", 0);
                    
                    new InsertVerifyComment(myHandler,RequestUrlInfo.HTTP_INSERT_HOUSE_VERIFY_COMMENT, requestJsonObject.toString()).start();
                   
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
                        requestJsonObject.put("houseCode", leaseHouseInfo.getHouseCode());
                        requestJsonObject.put("userId", GlobalData.userId);
                        requestJsonObject.put("checkTime", str);
                        requestJsonObject.put("checkResult", 2);
                        requestJsonObject.put("checkOpinion", comment);
                      //发给服务器让之判断是否通过
                        requestJsonObject.put("isAccess", "no_access");
                        
                        //插入系统信息表中的数据
                        requestJsonObject.put("receiveUserId", leaseHouseInfo.getLeaserUserId());
                        requestJsonObject.put("sendUserId", leaseHouseInfo.getTenantUserId());
                        requestJsonObject.put("messageContent","房屋编号为"+leaseHouseInfo.getHouseCode()+"的房屋信息审核未通过。原因为:"+comment);
                        requestJsonObject.put("isRead", 0);
                        
                        new InsertVerifyComment(myHandler,RequestUrlInfo.HTTP_INSERT_HOUSE_VERIFY_COMMENT, requestJsonObject.toString()).start();
                    
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

    @Override
    public void initData() {
        Intent intent = getIntent();
        leaseHouseInfo = (LeaseHouseInfo) intent.getSerializableExtra("leaseHouseInfo");
    }

}
