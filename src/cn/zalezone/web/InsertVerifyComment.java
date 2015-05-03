package cn.zalezone.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.setting.OperationState;
import cn.zalezone.setting.TagInfo;
import cn.zalezone.util.JsonHelper;

public class InsertVerifyComment  extends Thread{

    HttpClient           httpClient;
    HttpPost             httpPost;
    
    String requestUrl;
    String jsonString;
    
    Handler myHandler;
    
    public InsertVerifyComment(Handler myHandler,String requestUrl,String jsonString) {
        this.myHandler = myHandler;
        this.requestUrl = requestUrl;
        this.jsonString = jsonString;
    }
    
    @Override
    public void run() {

        try {

         // 进行http连接的httpclient设置
            httpClient = new DefaultHttpClient();
            // 设置请求超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
            // 设置读取超时
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
            
            StringEntity entity = new StringEntity(jsonString,"UTF-8");
            httpPost = new HttpPost(requestUrl);
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

                Log.d(TagInfo.JSONDATA_TAG, builder.toString());
                verifyData(builder.toString());
            }

        } catch (Exception e) {
            Message msg = new Message();
            msg.what = OperationState.NET_ERROR;
            myHandler.sendMessage(msg);
            e.printStackTrace();
        }
        
        
    }


    private void verifyData(String data) throws Exception {

        
    }
    
    
    
}