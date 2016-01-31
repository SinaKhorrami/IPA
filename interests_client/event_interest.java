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
public class event_interest extends Activity{

    public static ArrayList<String> Events = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);



    }

    public void onaddbtnClicked (View view){
        EditText EventEditText = (EditText) findViewById(R.id.editTextEvent);
        Events.add(EventEditText.getText().toString());
        Toast.makeText(event_interest.this, EventEditText.getText().toString() + " added to your favourite Events ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("events", EventEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        EventEditText.setText("");
        System.out.println(Events);
    }

    public void onShowEventButtonClicked(View view) {
        Intent showEventIntent = new Intent(event_interest.this, show_events.class);
        startActivity(showEventIntent);

    }
}
