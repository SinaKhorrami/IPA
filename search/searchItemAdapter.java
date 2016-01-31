package com.example.shahin.test.search;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shahin.test.R;
import com.example.shahin.test.search.searchItem;
import com.google.android.gms.games.Player;

import java.util.List;

/**
 * Created by sina on 11/19/2015.
 */
public class searchItemAdapter extends ArrayAdapter<searchItem> {

    private LayoutInflater inflater;
    private List<searchItem> searchItems;

    public searchItemAdapter(Context context, int resource, List<searchItem> objects, Activity activity) {
        super(context, resource, objects);
        this.searchItems = objects;
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        v = inflater.inflate(R.layout.search_textview, null);

        searchItem SearchItem = this.searchItems.get(position);
        TextView titleTextview = (TextView) v.findViewById(R.id.searchTextView);
        TextView urlTextview = (TextView) v.findViewById(R.id.searchTextView2);
        TextView descriptionTextview = (TextView) v.findViewById(R.id.searchTextView3);

        titleTextview.setText(SearchItem.getTitle());
        urlTextview.setText(SearchItem.getURL());
        descriptionTextview.setText(SearchItem.getDescription());

        return v;
    }
}
