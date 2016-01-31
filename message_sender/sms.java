package com.example.shahin.test.message_sender;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by Shahin on 11/27/15 AD.
 */
public class sms  {

    private Context ctx;
    private String inputString;

    public sms(String inputString1, Context ctx1){
        ctx = ctx1;
        inputString = inputString1;
        sendSMSMessage();

    }

    private void sendSMSMessage() {
        Log.i("Send SMS", "");


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



        String message = "";

        for (int i=2; i < splitedString.length; i++){
            message = message + splitedString[i] + " ";

        }




        String phoneNo = number;


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(ctx.getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(ctx.getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
