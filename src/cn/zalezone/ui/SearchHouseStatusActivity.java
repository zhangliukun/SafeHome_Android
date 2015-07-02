package cn.zalezone.ui;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.domian.LeaseTenantUserInfo;
import cn.zalezone.domian.LookHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.util.JsonHelper;
import cn.zalezone.web.PostForLeaseHouseList;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SearchHouseStatusActivity extends BaseActivity {
    TextView            villageName;
    TextView            houseType;
    TextView            propertionM3;
    TextView            rentIntention;
    TextView            paymentWay;
    TextView            usingProperties;
    TextView            leaseType;
    TextView            buildingType;
    TextView            orientation;
    TextView            decoration;
    TextView            rentalNum;
    TextView            houseAdd;
    TextView            buildingInfo;
    TextView            floorInfo;

    TextView            propertyPerson;
    TextView            propertyCertNumber;
    TextView            identificationCard;
    TextView            telephone;
    TextView            leaserPerson;                      // 出租人
    TextView            leaserPersonTelephone;
    TextView            tenantPerson;                      // 承租人
    TextView            tenantPersonTelephone;
    TextView            startDate;                         // 承租开始日
    TextView            endDate;                           // 承租结束日

    CheckBox            haveBed;
    CheckBox            haveKitchenBath;
    CheckBox            haveNetwork;
    CheckBox            haveTv;
    CheckBox            haveRefrigerator;
    CheckBox            haveAc;
    CheckBox            haveWashingMachine;
    CheckBox            haveWaterHeater;

    // actionBar
    Button              actionBarBackButton;
    TextView            actionBarTextView;
    Button              actionBarHomeButton;

    LeaseHouseInfo      searchHouseInfo;                   // 获取传过来的信息
    LeaseTenantUserInfo leaseTenantUserInfo;               // 出租承租人信息

    Handler             mHandler;

    SimpleDateFormat    sdf = new SimpleDateFormat(
                                    "yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_house_search);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.layout_actionbar);
        initHandler();
        initData();
        initUI();

    }

    private void initHandler() {
        // TODO Auto-generated method stub
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case OperationState.GET_DATA_FINISHED:
                        try {
                            JSONObject jsonObject = new JSONObject((String) msg.obj);
                            leaseTenantUserInfo = JsonHelper.JsonToObject(
                                    jsonObject.toString(),
                                    LeaseTenantUserInfo.class);

                            leaserPerson.setText(leaseTenantUserInfo.getLeaseUserName().toString());
                            leaserPersonTelephone.setText(leaseTenantUserInfo.getLeaseUserPhone()
                                    .toString());
                            tenantPerson
                                    .setText(leaseTenantUserInfo.getTenantUserName().toString());
                            tenantPersonTelephone.setText(leaseTenantUserInfo.getTenantUserPhone()
                                    .toString());
                            startDate.setText(sdf.format(leaseTenantUserInfo.getStartDate()));
                            endDate.setText(sdf.format(leaseTenantUserInfo.getEndDate()));
                            
                            
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
        // TODO Auto-generated method stub
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

        propertyPerson = (TextView) findViewById(R.id.property_person);
        propertyCertNumber = (TextView) findViewById(R.id.property_cert_number);
        identificationCard = (TextView) findViewById(R.id.identification_card);
        telephone = (TextView) findViewById(R.id.telephone);
        leaserPerson = (TextView) findViewById(R.id.leaser_person);
        leaserPersonTelephone = (TextView) findViewById(R.id.leaser_person_telephone);
        tenantPerson = (TextView) findViewById(R.id.tenant_person);
        tenantPersonTelephone = (TextView) findViewById(R.id.tenant_person_telephone);
        startDate = (TextView) findViewById(R.id.start_date);
        endDate = (TextView) findViewById(R.id.end_date);

        // actionBar
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarTextView.setText("房屋查询");
        // 返回上一界面
        actionBarBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent houseIntent = new Intent(getApplicationContext(),
                        SearchActivity.class);
                startActivity(houseIntent);
                finish();
            }
        });
        // 返回主界面
        actionBarHomeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent mainIntent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        // 开始初始化数据
        villageName.setText(searchHouseInfo.getVillageName());
        houseType.setText(searchHouseInfo.getHouseType());
        if (searchHouseInfo.getProportionM3() != null) {
            propertionM3.setText(searchHouseInfo.getProportionM3().toString());
        }
        if (searchHouseInfo.getRentIntention() != null) {
            rentIntention
                    .setText(searchHouseInfo.getRentIntention().toString());
        }
        paymentWay.setText(searchHouseInfo.getPaymentWay());
        usingProperties.setText(searchHouseInfo.getUsingProperties());
        leaseType.setText(searchHouseInfo.getLeaseType());
        buildingType.setText(searchHouseInfo.getBuildingType());
        orientation.setText(searchHouseInfo.getOrientation());
        decoration.setText(searchHouseInfo.getDecoration());
        // Integer类型的不能直接给TextView，除非多个字符串相加可自动转型
        if (searchHouseInfo.getRentalNum() != null) {
            rentalNum.setText(searchHouseInfo.getRentalNum().toString());
        }
        houseAdd.setText(searchHouseInfo.getHouseAdd());
        buildingInfo.setText(searchHouseInfo.getBuildingNo() + "幢"
                + searchHouseInfo.getBuildingUnit() + "单元"
                + searchHouseInfo.getBuildingRoom() + "室");
        floorInfo.setText(searchHouseInfo.getFloorNum() + "/"
                + searchHouseInfo.getFloorCnt());

        // 初始化checkbox
        if (searchHouseInfo.getHaveBed().equals("1")) {
            haveBed.setChecked(true);
        }
        if (searchHouseInfo.getHaveKitchenBath().equals("1")) {
            haveKitchenBath.setChecked(true);
        }
        if (searchHouseInfo.getHaveNetwork().equals("1")) {
            haveNetwork.setChecked(true);
        }
        if (searchHouseInfo.getHaveTv().equals("1")) {
            haveTv.setChecked(true);
        }
        if (searchHouseInfo.getHaveRefrigerator().equals("1")) {
            haveRefrigerator.setChecked(true);
        }
        if (searchHouseInfo.getHaveAc().equals("1")) {
            haveAc.setChecked(true);
        }
        if (searchHouseInfo.getHaveWashingMachine().equals("1")) {
            haveWashingMachine.setChecked(true);
        }
        if (searchHouseInfo.getHaveWaterHeater().equals("1")) {
            haveWaterHeater.setChecked(true);
        }

        propertyPerson.setText(searchHouseInfo.getPropertyOwner().toString());
        if (searchHouseInfo.getPropertyNo() != null) {
            propertyCertNumber.setText(searchHouseInfo.getPropertyNo()
                    .toString());
        }
        if (searchHouseInfo.getPoidCardNo() != null) {
            identificationCard.setText(searchHouseInfo.getPoidCardNo()
                    .toString());
        }
        if (searchHouseInfo.getPophone() != null) {
            telephone.setText(searchHouseInfo.getPophone().toString());
        }

    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        Intent intent = getIntent();
        searchHouseInfo = (LeaseHouseInfo) intent
                .getSerializableExtra("searchHouseInfo");
        JSONObject jsonObject = new JSONObject();
        // JSONObject getDateJsonObject = new JSONObject();
        try {
            jsonObject.put("leaseUserId", searchHouseInfo.getLeaserUserId());
            jsonObject.put("tenantUserId", searchHouseInfo.getTenantUserId());
            jsonObject.put("houseInfoId", searchHouseInfo.getHouseInfoId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new PostForLeaseHouseList(mHandler, RequestUrlInfo.HTTP_GET_USERINFO,
                jsonObject.toString()).start();
        // new PostForLeaseHouseList(mHandler, RequestUrlInfo.HTTP_GET_LEASE_DATE,
        // getDateJsonObject.toString()).start();
    }
}
