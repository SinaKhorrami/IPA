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
public class movie_interest extends Activity {


    public static ArrayList<String> Movies = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_layout);



    }

    public void onaddbtnClicked (View view) {
        EditText MovieEditText = (EditText) findViewById(R.id.editTextMovie);
        Movies.add(MovieEditText.getText().toString());
        Toast.makeText(movie_interest.this, MovieEditText.getText().toString() + " added to your favourite Movies ! :)", Toast.LENGTH_LONG).show();
        httpRequestSender JSONC = new httpRequestSender("movies & tv", MovieEditText.getText().toString(), this.getApplicationContext());
        try {
            JSONC.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        MovieEditText.setText("");
        System.out.println(Movies);

    }

    public void onShowMovieButtonClicked(View view) {
        Intent showMovieIntent = new Intent(movie_interest.this, show_movies.class);
        startActivity(showMovieIntent);

    }



}
