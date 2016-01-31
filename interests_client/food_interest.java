package com.example.shahin.test.interests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shahin.test.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * Created by Shahin on 1/28/16 AD.
 */
public class food_interest extends Activity {


    public static ArrayList<String> Foods = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_layout);



    }

    public void onaddbtnClicked (View view){
        EditText FoodEditText = (EditText) findViewById(R.id.editTextFood);
        Foods.add(FoodEditText.getText().toString());
        Toast.makeText(food_interest.this, FoodEditText.getText().toString() + " added to your favourite foods ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("foods & drinks", FoodEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        FoodEditText.setText("");
        System.out.println(Foods);
    }

    public void onShowFoodButtonClicked(View view) {
        Intent showFoodIntent = new Intent(food_interest.this, show_foods.class);
        startActivity(showFoodIntent);

    }



}
