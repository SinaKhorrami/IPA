package com.example.shahin.test.interests;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.shahin.test.R;

/**
 * Created by Shahin on 1/28/16 AD.
 */
public class show_news extends Activity {

    news_interest news;
    public Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_listview);

        deleteButton = (Button) findViewById(R.id.delete_Button);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.news_textview, R.id.label, news.News);
        ListView listView = (ListView) findViewById(R.id.newslistview);
        listView.setAdapter(adapter);

    }

    public void delete(View v){

        ListView listview1 = (ListView) findViewById(R.id.newslistview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.news_textview, R.id.label, news.News);


        final int position = listview1.getPositionForView((View) v.getParent());
        System.out.println("position = " + position);
        news.News.remove(position);
        System.out.println(news.News);
        adapter.notifyDataSetChanged();

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.news_textview, R.id.label, news.News);
        ListView listView = (ListView) findViewById(R.id.newslistview);
        listView.setAdapter(adapter2);


    }



}
