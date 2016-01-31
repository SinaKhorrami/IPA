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
public class show_movies extends Activity {

    movie_interest movie;
    public Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_listview);

        deleteButton = (Button) findViewById(R.id.delete_Button);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.movie_textview, R.id.label, movie.Movies);
        ListView listView = (ListView) findViewById(R.id.movielistview);
        listView.setAdapter(adapter);

    }

    public void delete(View v){

        ListView listview1 = (ListView) findViewById(R.id.movielistview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.movie_textview, R.id.label, movie.Movies);


        final int position = listview1.getPositionForView((View) v.getParent());
        System.out.println("position = " + position);
        movie.Movies.remove(position);
        System.out.println(movie.Movies);
        adapter.notifyDataSetChanged();

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.movie_textview, R.id.label, movie.Movies);
        ListView listView = (ListView) findViewById(R.id.movielistview);
        listView.setAdapter(adapter2);


    }



}
