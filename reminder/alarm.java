package com.example.shahin.test.reminder;

/**
 * Created by Shahin on 12/4/15 AD.
 */

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;

import android.view.View;
import android.widget.EditText;

import java.lang.String;

import org.json.JSONObject;

public class alarm  {

    //JSONObject arg = new JSONObject();
    private String inputString;
    private Context ctx;

    public alarm(String inputString1, Context ctx1){
        inputString = inputString1;
        ctx = ctx1;
        alarm_manager();
    }

    public void alarm_manager(){
        String[] splitted = inputString.split(" ");
        if (splitted[0].equals("set")){
            create(splitted);
        }
        else if(splitted[0].equals("show") || splitted[0].equals("edit") || splitted[0].equals("delete")){
            show_alarm();
        }
        else {}
    }

    public void show_alarm(){
        Intent j =new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        ctx.startActivity(j);
    }

    public void create(String[] splitted){
        int hour;
        int minutes;

        String time = splitted[splitted.length-1];
        String[] hour_minute;
        if(splitted[splitted.length-2].contains(":")){
            hour_minute = splitted[splitted.length-2].split(":");
            hour = Integer.parseInt(hour_minute[0]);
            minutes = Integer.parseInt(hour_minute[1]);
        }
        else
        {
            System.out.println(splitted[splitted.length-2]);
            hour = Integer.parseInt(splitted[splitted.length-2]);
            minutes = 0;
        }
        if (time.equals("pm") && hour != 12){
            hour += 12;
        }
        else if (time.equals("pm") && hour == 12){
            hour = 0;
        }

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if(intent.resolveActivity(ctx.getPackageManager()) != null){
            ctx.startActivity(intent);
        }
    }


}
