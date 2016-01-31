package com.example.shahin.test;

import android.os.AsyncTask;

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

/**
 * Created by Shahin on 1/31/16 AD.
 */
public class http extends AsyncTask {


    private String uri = "";
    private JSONObject input;
    public String output = "";
    private int time_out = 100000;
    private int num_retries = 4;

    public http(String uri_, JSONObject params_) {
        uri = uri_;
        input = params_;
    }

    public http(String uri_, JSONObject params_, int time_out_, int num_retries_) {
        uri = uri_;
        input = params_;
        time_out = time_out_;
        num_retries = num_retries_;
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
                se = new StringEntity(input.toString(), "UTF-8");
                se.setContentType("application/json; charset=UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(se);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                output = EntityUtils.toString(resEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }
}
