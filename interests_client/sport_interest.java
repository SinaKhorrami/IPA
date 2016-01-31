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
public class sport_interest extends Activity {


    public static ArrayList<String> Sports = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_layout);



    }

    public void onaddbtnClicked (View view){
        EditText SportEditText = (EditText) findViewById(R.id.editTextSport);
        Sports.add(SportEditText.getText().toString());
        Toast.makeText(sport_interest.this, SportEditText.getText().toString() + " added to your favourite Sports ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("sports", SportEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        SportEditText.setText("");
        System.out.println(Sports);
    }

    public void onShowSportButtonClicked(View view) {
        Intent showSportIntent = new Intent(sport_interest.this, show_sports.class);
        startActivity(showSportIntent);

    }





}
