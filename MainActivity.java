package com.example.shahin.test;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


import android.widget.Toast;


import android.content.Intent;

import com.example.shahin.test.text_processor.Decision_maker;
import com.example.shahin.test.text_processor.FlyOutContainer;

import com.example.shahin.test.interests.interest_manager;

import com.example.shahin.test.search.searchItem_activity;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {


    private TextToSpeech tts;
    private TextView txt1;
    private ImageButton micro_button;
    private TextView txtSpeechInput;


    private static final int REQ_CODE_SPEECH_INPUT = 100 ;


    FlyOutContainer root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = (TextView) findViewById(R.id.Opening_message);
        micro_button = (ImageButton) findViewById(R.id.imageButton);


        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);



        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                    String toSpeak = txt1.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                }
            }
        });


        this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_main, null);

        this.setContentView(root);

        micro_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //promptSpeechInput();
            }
        });





    }


    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }



    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
            }

        super.onPause();

        }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && !data.equals(null)) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    Integer i = result.size();
                    if ( result.size() == 0 )
                    {
                        Toast.makeText(MainActivity.this , " result is empty " , Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this , " result is not empty " + i.toString(), Toast.LENGTH_LONG).show();

                        txtSpeechInput.setText(result.get(0).toString());
                        txt1.setVisibility(View.INVISIBLE);
                        String input = txtSpeechInput.getText().toString();
                        new Decision_maker(input,MainActivity.this).dispatch();
                    }
                }

                break;
            }

        }
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void toggleMenu(View v) {
        this.root.toggleMenu();
    }

    public void OnMicClicked(View view) {
        promptSpeechInput();
    }

    public void showManageInterest( View v) {
        Intent interestsIntent = new Intent(MainActivity.this, interest_manager.class);
        startActivity(interestsIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
