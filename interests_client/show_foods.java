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
public class show_foods extends Activity {

    food_interest food;
    public Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_listview);

        deleteButton = (Button) findViewById(R.id.delete_Button);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.food_textview, R.id.label, food.Foods);
        ListView listView = (ListView) findViewById(R.id.foodlistview);
        listView.setAdapter(adapter);

    }

    public void delete(View v){

        ListView listview1 = (ListView) findViewById(R.id.foodlistview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.food_textview, R.id.label, food.Foods);


        final int position = listview1.getPositionForView((View) v.getParent());
        System.out.println("position = " + position);
        food.Foods.remove(position);
        System.out.println(food.Foods);
        adapter.notifyDataSetChanged();

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.food_textview, R.id.label, food.Foods);
        ListView listView = (ListView) findViewById(R.id.foodlistview);
        listView.setAdapter(adapter2);



    }



}
