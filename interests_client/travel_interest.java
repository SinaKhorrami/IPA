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
public class travel_interest extends Activity {


    public static ArrayList<String> Travels = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_layout);



    }

    public void onaddbtnClicked (View view){
        EditText TravelEditText = (EditText) findViewById(R.id.editTextTravel);
        Travels.add(TravelEditText.getText().toString());
        Toast.makeText(travel_interest.this, TravelEditText.getText().toString() + " added to your favourite Travels ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("travel", TravelEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TravelEditText.setText("");
        System.out.println(Travels);
    }

    public void onShowTravelButtonClicked(View view) {
        Intent showTravelIntent = new Intent(travel_interest.this, show_travels.class);
        startActivity(showTravelIntent);

    }



}
