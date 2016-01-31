package com.example.shahin.test.search;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shahin.test.R;
import com.example.shahin.test.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by Shahin on 1/30/16 AD.
 */
public class searchItem_activity extends Activity {


    private String sentence;
    JSONObject json;
    String httpResults = "";


    final List<searchItem> results = new ArrayList<searchItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_listview);
        final Intent searchresult = getIntent();
        sentence = searchresult.getStringExtra("sentence");


        final List<searchItem> results = new ArrayList<searchItem>();


        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        HashMap mapParams = new HashMap();
        mapParams.put(new String("device_id"), android_id);
        mapParams.put(new String("component"), "search");
        mapParams.put(new String("message"), sentence);
        JSONObject params = new JSONObject(mapParams);
        http httpObject = new http("http://52.48.35.134:5000", params);
        try {
            httpObject.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            json = new JSONObject(httpObject.output);
            JSONArray jsonArray = json.getJSONArray("results");

            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                String snippet = c.getString("snippet");
                String title = c.getString("title");
                String url = c.getString("url");
                results.add(new searchItem(title, url, snippet));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        final ListView resultListview = (ListView) findViewById(R.id.searchlistview);

        searchItemAdapter adapter = new searchItemAdapter(getApplicationContext(), R.layout.search_listview, results, searchItem_activity.this);
        resultListview.setAdapter(adapter);

        resultListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchItem SearchItem = results.get(i);
                String searchURL = SearchItem.getURL();
                Intent searchURLIntent = new Intent(Intent.ACTION_VIEW);
                searchURLIntent.setData(Uri.parse("http://" + searchURL));
                startActivity(searchURLIntent);
            }
        });
    }


}
