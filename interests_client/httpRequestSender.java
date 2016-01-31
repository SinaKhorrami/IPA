package com.example.shahin.test.interests;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;


/**
 * Created by Shahin on 1/30/16 AD.
 */
public class httpRequestSender extends AsyncTask {

    private String category;
    private String value;
    private Context context;

    private String uri = "http://52.48.35.134:5000/setInterest";
    public String response_string = "";
    private int time_out = 100000;
    private int num_retries = 4;

    public httpRequestSender(String cat, String val, Context ctc){
        this.category = cat;
        this.value = val;
        this.context = ctc;
    }



    private JSONObject getResult(){

        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        HashMap mapParams = new HashMap();
        mapParams.put(new String("id"), android_id);
        mapParams.put(new String("cat"), this.category);
        mapParams.put(new String("val"), this.value);
        JSONObject params = new JSONObject(mapParams);

        return params;

    }

    @Override
    protected Void doInBackground(Object[] p) {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(httpParams, time_out);
        HttpClient httpclient = new DefaultHttpClient(httpParams);
        ((AbstractHttpClient)httpclient).setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(num_retries, true));

        try {
            StringEntity se = null;
            try {
                se = new StringEntity(this.getResult().toString(), "UTF-8");
                se.setContentType("application/json; charset=UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(se);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                response_string = EntityUtils.toString(resEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }



}
