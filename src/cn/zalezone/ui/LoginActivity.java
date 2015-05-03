package cn.zalezone.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cn.zalezone.domian.BaseUser;
import cn.zalezone.domian.GlobalData;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.RequestUrlInfo;
import cn.zalezone.setting.TagInfo;
import cn.zalezone.util.JsonHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.R.integer;
import android.app.Activity;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 登录页面
 * 
 * @author zlk
 *
 */

public class LoginActivity extends BaseActivity {

    EditText                 editName;
    EditText                 editPassword;
    Button                   loginButton;
    CheckBox                 saveInfoCheckBox;
    Button                   actionBarBackButton;
    TextView                 actionBarTextView;
    Button                   actionBarHomeButton;

    SharedPreferences        sharedPreferences;
    SharedPreferences.Editor editor;

    Handler                  myHandler;

    // httpClient
    HttpClient               httpClient;
    HttpPost                 httpPost;
    

    private int              isCheckBoxChecked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_login);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_actionbar);
        // 初始化UI组件
        initData();
        initUI();

    }

    @Override
    public void initUI() {
        editName = (EditText) findViewById(R.id.edit_Name);
        editPassword = (EditText) findViewById(R.id.edit_password);
        loginButton = (Button) findViewById(R.id.login);
        saveInfoCheckBox = (CheckBox) findViewById(R.id.saveInfoCheckBox);
        actionBarBackButton = (Button) findViewById(R.id.actionbar_back_button);
        actionBarTextView = (TextView) findViewById(R.id.actionbar_title_textview);
        actionBarHomeButton = (Button) findViewById(R.id.actionbar_home_button);
        actionBarBackButton.setVisibility(View.GONE);
        actionBarTextView.setText("登录");
        actionBarHomeButton.setVisibility(View.GONE);

        // 判断文本框是否保存了值
        if (isCheckBoxChecked == 1) {
            editName.setText(sharedPreferences.getString("userName", ""));
            editPassword.setText(sharedPreferences.getString("userPassword", ""));
            saveInfoCheckBox.setChecked(true);
        }

        saveInfoCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isCheckBoxChecked = 1;
                    editor.putInt("isChecked", 1);
                    editor.putString("userName", editName.getText().toString());
                    editor.putString("userPassword", editPassword.getText().toString());
                    editor.commit();
                }
                else {
                    isCheckBoxChecked = 0;
                    editor.putInt("isChecked", 0);
                    editor.putString("userName", "");
                    editor.putString("userPassword", "");
                    editor.commit();
                }
            }
        });

        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                loginButton.setText("登录中...");

                String name = editName.getText().toString();
                String password = editPassword.getText().toString();
                
                //保存变量值
                GlobalData.loginId =name;
                GlobalData.loginPwd=password;
                
                //检验用户名密码
                checkLoginInfoFromServer(name, password);

                // Intent mainIntent = new Intent(getApplicationContext(),
                // MainActivity.class);
                // startActivity(mainIntent);
                // finish();
            }
        });

        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // 将按钮恢复原样
                loginButton.setText("登录");

                // 判断类型
                switch (msg.what) {
                    case OperationState.LOGIN_SUCCESS:
                        Intent mainIntent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(mainIntent);
                        // finish();
                        break;
                    case OperationState.LOGIN_FAILED:
                        Toast.makeText(getApplicationContext(), "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                        break;
                    case OperationState.NOACCESS_LOGIN:
                        Toast.makeText(getApplicationContext(), "你的账号没有登录权限", Toast.LENGTH_SHORT).show();
                        break;
                    case OperationState.NET_ERROR:
                        Toast.makeText(getApplicationContext(), "服务器或者网络故障", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
            }

        };
    }

    protected void checkLoginInfoFromServer(String name, String password) {
        // 创建JSON数据
        final JSONObject LoginInfo = new JSONObject();
        try {
            LoginInfo.put("loginId", name);
            LoginInfo.put("loginPwd", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                    Log.d(TagInfo.JSONDATA_TAG, LoginInfo.toString());

                    StringEntity entity = new StringEntity(LoginInfo.toString());
                    httpPost = new HttpPost(RequestUrlInfo.HTTP_LOGIN);
                    httpPost.setEntity(entity);

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    Log.d("httpresponse", String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {

                        // 读取返回的数据
                        String data = EntityUtils.toString(
                                httpResponse.getEntity(), "UTF-8");
                        Log.d(TagInfo.HTTP_DATA, data);

                        // 检测服务器返回的状态码
                        verifyData(data);
                    }

                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = OperationState.NET_ERROR;
                    myHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }

        }.start();
    }

    protected void verifyData(String data) {
        Message msg = new Message();
//        if (data.equals("access")) {
//            Log.d(TagInfo.HTTP_DATA, "login successfully");
//            msg.what = LOGIN_SUCCESS;
//        }
        if (data.equals("incorrect"))  {
            Log.d(TagInfo.HTTP_DATA, "login failed");
            msg.what = OperationState.LOGIN_FAILED;
        }
        else if(data.equals("no_access")){
            Log.d(TagInfo.HTTP_DATA, "no access");
            msg.what = OperationState.NOACCESS_LOGIN;
        }
        else if (data.equals("no_result")) {
            Log.d(TagInfo.HTTP_DATA, "the server error");
        }
        else {
            Log.d(TagInfo.HTTP_DATA, "login successfully");
            BaseUser tempBaseUser;
            try {
                tempBaseUser = JsonHelper.JsonToObject(data, BaseUser.class);
                GlobalData.serviceCenterId = tempBaseUser.getServiceCenterId();
                GlobalData.userId = tempBaseUser.getUserId();
                System.out.println(GlobalData.userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            msg.what = OperationState.LOGIN_SUCCESS;
        }
        myHandler.sendMessage(msg);
    }

    @Override
    public void initData() {
        sharedPreferences = getSharedPreferences("SafeHome", MODE_WORLD_READABLE);
        isCheckBoxChecked = sharedPreferences.getInt("isChecked", 0);
        editor = sharedPreferences.edit();
    }

}
