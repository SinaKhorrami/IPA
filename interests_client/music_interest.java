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
public class music_interest extends Activity {


    public static ArrayList<String> Musics = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_layout);



    }

    public void onaddbtnClicked (View view) {
        EditText MusicEditText = (EditText) findViewById(R.id.editTextMusic);
        Musics.add(MusicEditText.getText().toString());
        Toast.makeText(music_interest.this, MusicEditText.getText().toString() + " added to your favourite Music ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("musics", MusicEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        MusicEditText.setText("");
        System.out.println(Musics);

    }

    public void onShowMusicButtonClicked(View view) {
        Intent showMusicIntent = new Intent(music_interest.this, show_musics.class);
        startActivity(showMusicIntent);

    }



}
