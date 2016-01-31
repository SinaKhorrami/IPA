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
public class show_places extends Activity {

    place_interest place;
    public Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_listview);

        deleteButton = (Button) findViewById(R.id.delete_Button);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.place_textview, R.id.label, place.Places);
        ListView listView = (ListView) findViewById(R.id.placelistview);
        listView.setAdapter(adapter);

    }

    public void delete(View v){

        ListView listview1 = (ListView) findViewById(R.id.placelistview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.place_textview, R.id.label, place.Places);


        final int position = listview1.getPositionForView((View) v.getParent());
        System.out.println("position = " + position);
        place.Places.remove(position);
        System.out.println(place.Places);
        adapter.notifyDataSetChanged();

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.place_textview, R.id.label, place.Places);
        ListView listView = (ListView) findViewById(R.id.placelistview);
        listView.setAdapter(adapter2);


    }



}
