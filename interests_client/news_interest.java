package com.example.shahin.test.interests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shahin.test.MainActivity;
import com.example.shahin.test.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * Created by Shahin on 1/28/16 AD.
 */
public class news_interest extends Activity {


    public static ArrayList<String> News = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);



    }

    public void onaddbtnClicked (View view){
        EditText NewsEditText = (EditText) findViewById(R.id.editTextNews);
        News.add(NewsEditText.getText().toString());
        Toast.makeText(news_interest.this, NewsEditText.getText().toString() + " added to your favourite News ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("news", NewsEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        NewsEditText.setText("");
        System.out.println(News);
    }

    public void onShowNewsButtonClicked(View view) {
        Intent showNewsIntent = new Intent(news_interest.this, show_news.class);
        startActivity(showNewsIntent);

    }



}
