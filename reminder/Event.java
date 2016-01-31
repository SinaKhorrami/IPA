package com.example.shahin.test.reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;

import android.content.Intent;
import android.provider.AlarmClock;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shahin.test.R;

import java.lang.String;
import java.util.Set;
import java.util.TimeZone;


public class Event  {

    private Context ctx;
    private String inputString;

    public Event(Context ctx1, String inputString1){
        ctx = ctx1;
        inputString = inputString1;
        event_manager();
    }

    private void event_manager(){
        String[] splitted = inputString.split(" ");
        if(splitted[0].equals("create") || splitted[0].equals("add") || splitted[0].equals("set")){
            addEvent(splitted);
        }
        else if (splitted[0].equals("edit")){
            edit_event_action(splitted);
        }
        else if(splitted[0].equals("show")){
            see_upcoming();
        }
        else if(splitted[0].equals("delete")){
            del_event_action(splitted);
        }
        else {}
    }

    public void addEvent(String[] split) {


        String title = split[2];
        String location = split[5];

        int begin_month = 0;
        if (split[8].equals("January")) {
            begin_month = 0;

        } else if (split[8].equals("February")) {
            begin_month = 1;

        } else if (split[8].equals("March")) {
            begin_month = 2;

        } else if (split[8].equals("April")) {
            begin_month = 3;

        } else if (split[8].equals("May")) {
            begin_month = 4;

        } else if (split[8].equals("June")) {
            begin_month = 5;

        } else if (split[8].equals("July")) {
            begin_month = 6;

        } else if (split[8].equals("August")) {
            begin_month = 7;

        } else if (split[8].equals("September")) {
            begin_month = 8;

        } else if (split[8].equals("October")) {
            begin_month = 9;

        } else if (split[8].equals("November")) {
            begin_month = 10;

        } else if (split[8].equals("December")) {
            begin_month = 11;

        } else {
        }

        int end_month = 0;
        if (split[split.length - 2].equals("January")) {
            end_month = 0;

        } else if (split[split.length - 2].equals("February")) {
            end_month = 1;

        } else if (split[split.length - 2].equals("March")) {
            end_month = 2;

        } else if (split[split.length - 2].equals("April")) {
            end_month = 3;

        } else if (split[split.length - 2].equals("May")) {
            end_month = 4;

        } else if (split[split.length - 2].equals("June")) {
            end_month = 5;

        } else if (split[split.length - 2].equals("July")) {
            end_month = 6;

        } else if (split[split.length - 2].equals("August")) {
            end_month = 7;

        } else if (split[split.length - 2].equals("September")) {
            end_month = 8;

        } else if (split[split.length - 2].equals("October")) {
            end_month = 9;

        } else if (split[split.length - 2].equals("November")) {
            end_month = 10;

        } else if (split[split.length - 2].equals("December")) {
            end_month = 11;

        } else {
        }


        Calendar begin = new GregorianCalendar();
        begin.set(Calendar.YEAR, Integer.parseInt(split[7]));
        begin.set(Calendar.MONTH, begin_month);
        begin.set(Calendar.DAY_OF_MONTH, Integer.parseInt(split[9]));

        Calendar end = new GregorianCalendar();
        end.set(Calendar.YEAR, Integer.parseInt(split[split.length -3]));
        end.set(Calendar.MONTH, end_month);
        end.set(Calendar.DAY_OF_MONTH, Integer.parseInt(split[split.length - 1]));


        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setType("vnd.android.cursor.item/event")
                .setData(Events.CONTENT_URI)
                .putExtra(Events.TITLE, title)
                .putExtra(Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis())
                .putExtra("allDay", true);
        if (intent.resolveActivity(ctx.getPackageManager()) != null) {
            ctx.startActivity(intent);
        }
    }


    public void del_event_action(String[] splitted) {


        String event_title = splitted[2];


        CalendarContentResolver list = new CalendarContentResolver();

        ArrayList<String> selected = new ArrayList<String>();
        for(int i=0; i<list.readCalendarEvent(ctx).get(7).size(); i++)
        {
            if(list.readCalendarEvent(ctx).get(0).get(i).equals(event_title))
            {
                selected.add(list.readCalendarEvent(ctx).get(7).get(i));
            }
        }


//        Intent calendar = new Intent();
//
//        ComponentName cn = new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");
//        calendar.setComponent(cn);
//        startActivity(calendar);





        //text.setText(""+selected.get(0));


//        int count=0;
//
//        for(int j=0; j<selected.size(); j++) {
//            count ++;
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            //Android 2.2+
//            intent.setData(Uri.parse("content://com.android.calendar/events/" + selected.get(j)));
//            //Android 2.1 and below.
//            //intent.setData(Uri.parse("content://calendar/events/" + String.valueOf(calendarEventID)));
//            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//        text.setText(count+"");

        for(int j=0; j<selected.size(); j++) {
            int iNumRowsDeleted = 0;

            Uri eventsUri = Uri.parse("content://com.android.calendar/events");
            Uri eventUri = ContentUris.withAppendedId(eventsUri, Long.parseLong(selected.get(j)));
            iNumRowsDeleted = ctx.getContentResolver().delete(eventUri, null, null);
        }


}


    public void see_upcoming()
    {

        CalendarContentResolver list = new CalendarContentResolver();


        long time= System.currentTimeMillis();


        long diff= Long.parseLong(list.readCalendarEvent(ctx).get(2).get(list.readCalendarEvent(ctx).get(2).size()-1)) - Long.parseLong(list.readCalendarEvent(ctx).get(2).get(0));

        int upcoming=0;

        int counter = 0;
        for(int i=list.readCalendarEvent(ctx).get(2).size()-1; i>0; i--)
        {

            if( Long.parseLong(list.readCalendarEvent(ctx).get(2).get(i)) > time)
            {
                counter++;
                if(Long.parseLong(list.readCalendarEvent(ctx).get(2).get(i)) - time < diff) {
                    diff = Long.parseLong(list.readCalendarEvent(ctx).get(2).get(i)) - time;
                    upcoming = i;
                }
            }
            else {
                break;
            }
        }


        Intent intent = new Intent(Intent.ACTION_VIEW);
        //Android 2.2+
        intent.setData(Uri.parse("content://com.android.calendar/events/" + (list.readCalendarEvent(ctx).get(7).get(upcoming))));
        //Android 2.1 and below.
        //intent.setData(Uri.parse("content://calendar/events/" + String.valueOf(calendarEventID)));
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    public void edit_event_action(String[] splitted)
    {

        String event_title = splitted[2];

        CalendarContentResolver list = new CalendarContentResolver();

        ArrayList<String> selected = new ArrayList<String>();
        for(int i=0; i<list.readCalendarEvent(ctx).get(7).size(); i++)
        {
            if(list.readCalendarEvent(ctx).get(0).get(i).equals(event_title))
            {
                selected.add(list.readCalendarEvent(ctx).get(7).get(i));
            }
        }



        for(int j=0; j<selected.size(); j++) {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            //Android 2.2+
            intent.setData(Uri.parse("content://com.android.calendar/events/" + selected.get(j)));
            //Android 2.1 and below.
            //intent.setData(Uri.parse("content://calendar/events/" + String.valueOf(calendarEventID)));
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
        }
    }



}
