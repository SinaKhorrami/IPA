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
public class show_events extends Activity {

    event_interest event;
    public Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_listview);

        deleteButton = (Button) findViewById(R.id.delete_Button);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.event_textview, R.id.label, event.Events);
        ListView listView = (ListView) findViewById(R.id.eventlistview);
        listView.setAdapter(adapter);

    }

    public void delete(View v){

        ListView listview1 = (ListView) findViewById(R.id.eventlistview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.event_textview, R.id.label, event.Events);


        final int position = listview1.getPositionForView((View) v.getParent());
        System.out.println("position = " + position);
        event.Events.remove(position);
        System.out.println(event.Events);
        adapter.notifyDataSetChanged();

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.event_textview, R.id.label, event.Events);
        ListView listView = (ListView) findViewById(R.id.eventlistview);
        listView.setAdapter(adapter2);


    }



}
