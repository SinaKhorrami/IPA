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
public class show_musics extends Activity {

    music_interest music;
    public Button deleteButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_listview);

        deleteButton = (Button) findViewById(R.id.delete_Button);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.music_textview, R.id.label, music.Musics);
        ListView listView = (ListView) findViewById(R.id.musiclistview);
        listView.setAdapter(adapter);

    }

    public void delete(View v){

        ListView listview1 = (ListView) findViewById(R.id.musiclistview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.music_textview, R.id.label, music.Musics);


        final int position = listview1.getPositionForView((View) v.getParent());
        System.out.println("position = " + position);
        music.Musics.remove(position);
        System.out.println(music.Musics);
        adapter.notifyDataSetChanged();

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.music_textview, R.id.label, music.Musics);
        ListView listView = (ListView) findViewById(R.id.musiclistview);
        listView.setAdapter(adapter2);


    }



}
