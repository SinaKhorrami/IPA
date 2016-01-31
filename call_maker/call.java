package com.example.shahin.test.call_maker;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shahin on 11/27/15 AD.
 */
public class call  {

    private Context ctx;
    private String inputString;


    public call(String inputString1, Context ctx1) {
        ctx = ctx1;
        inputString = inputString1;
        make_call();
    }

    private void make_call() {


        // Define the fields that the query will return
        String[] PROJECTION = new String[]{

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        ContentResolver cr = ctx.getContentResolver();


        // Execute the query and receive the cursor with the results

        String[] splitedString = inputString.split(" ");

        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ?",
                new String[]{splitedString[1]},
                null);
        String number = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // First discover the index of the desired field (Number)
                final int indexNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                number = cursor.getString(indexNumber);
            }
        }


        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        ctx.startActivity(callIntent);


        Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("000000"));
        try {
            ctx.startActivity(in);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ctx.getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
        }
    }


}
