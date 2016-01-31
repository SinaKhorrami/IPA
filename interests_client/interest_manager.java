package com.example.shahin.test.interests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shahin.test.R;

/**
 * Created by Shahin on 1/28/16 AD.
 */
public class interest_manager extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interests);
    }

    public void onFoodAndDrinksClicked (View view) {
        Intent foodIntent = new Intent(interest_manager.this, food_interest.class);
        startActivity(foodIntent);
    }

    public void onEventsClicked (View view) {
        Intent foodIntent = new Intent(interest_manager.this, event_interest.class);
        startActivity(foodIntent);
    }

    public void onPlacesClicked (View view) {
        Intent placeIntent = new Intent(interest_manager.this, place_interest.class);
        startActivity(placeIntent);
    }

    public void onMoviesClicked (View view) {
        Intent movieIntent = new Intent(interest_manager.this, movie_interest.class);
        startActivity(movieIntent);
    }

    public void onMusicClicked (View view) {
        Intent musicIntent = new Intent(interest_manager.this, music_interest.class);
        startActivity(musicIntent);
    }

    public void onNewsClicked (View view) {
        Intent newsIntent = new Intent(interest_manager.this, news_interest.class);
        startActivity(newsIntent);
    }

    public void onSportsClicked (View view) {
        Intent sportIntent = new Intent(interest_manager.this, sport_interest.class);
        startActivity(sportIntent);
    }

    public void onTravelClicked (View view) {
        Intent travelIntent = new Intent(interest_manager.this, travel_interest.class);
        startActivity(travelIntent);
    }


}
