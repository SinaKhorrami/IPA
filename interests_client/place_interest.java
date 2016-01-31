package com.example.shahin.test.interests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shahin.test.R;
import com.example.shahin.test.reminder.Event;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Shahin on 1/29/16 AD.
 */
public class place_interest extends Activity{

    public static ArrayList<String> Places = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_layout);



    }

    public void onaddbtnClicked (View view){
        EditText PlaceEditText = (EditText) findViewById(R.id.editTextPlace);
        Places.add(PlaceEditText.getText().toString());
        Toast.makeText(place_interest.this, PlaceEditText.getText().toString() + " added to your favourite Places ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("places", PlaceEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        PlaceEditText.setText("");
        System.out.println(Places);
    }

    public void onShowPlaceButtonClicked(View view) {
        Intent showPlaceIntent = new Intent(place_interest.this, show_places.class);
        startActivity(showPlaceIntent);

    }
}
