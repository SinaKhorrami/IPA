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
public class show_travels extends Activity {

    travel_interest travel;
    public Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_listview);

        deleteButton = (Button) findViewById(R.id.delete_Button);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.travel_textview, R.id.label, travel.Travels);
        ListView listView = (ListView) findViewById(R.id.travellistview);
        listView.setAdapter(adapter);

    }

    public void delete(View v){

        ListView listview1 = (ListView) findViewById(R.id.travellistview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.travel_textview, R.id.label, travel.Travels);


        final int position = listview1.getPositionForView((View) v.getParent());
        System.out.println("position = " + position);
        travel.Travels.remove(position);
        System.out.println(travel.Travels);
        adapter.notifyDataSetChanged();

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.travel_textview, R.id.label, travel.Travels);
        ListView listView = (ListView) findViewById(R.id.travellistview);
        listView.setAdapter(adapter2);


    }



}
