package com.example.shahin.test.reminder;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;



/**
 * Created by Hamid on 11/27/2015.
 */
public class CalendarContentResolver {
    public static ArrayList<String> nameOfEvent = new ArrayList<String>();
    public static ArrayList<String> startDates = new ArrayList<String>();
    public static ArrayList<String> startDatesMili = new ArrayList<String>();
    public static ArrayList<String> endDates = new ArrayList<String>();
    public static ArrayList<String> endDatesMili = new ArrayList<String>();
    public static ArrayList<String> descriptions = new ArrayList<String>();
    public static ArrayList<String> eventLocation = new ArrayList<String>();
    public static ArrayList<String> id = new ArrayList<String>();

    public static ArrayList<ArrayList<String>> readCalendarEvent(Context context) {
        ArrayList result = new ArrayList();
        Cursor cursor = context.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        new String[] { "calendar_id", "title", "description",
                                "dtstart", "dtend", "eventLocation", "_id" }, null,
                        null, null);
        cursor.moveToFirst();
        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        // fetching calendars id
        nameOfEvent.clear();
        startDates.clear();
        startDatesMili.clear();
        endDates.clear();
        endDatesMili.clear();
        descriptions.clear();
        eventLocation.clear();
        id.clear();

        for (int i = 0; i < CNames.length; i++) {

            nameOfEvent.add(cursor.getString(1));
            startDates.add(getDate(Long.parseLong(cursor.getString(3))));
            startDatesMili.add(cursor.getString(3));
            endDates.add(getDate(Long.parseLong(cursor.getString(4))));
            endDatesMili.add(cursor.getString(4));
            descriptions.add(cursor.getString(2));
            eventLocation.add(cursor.getString(5));
            id.add(cursor.getString(6));
            CNames[i] = cursor.getString(1);
            cursor.moveToNext();

        }
        result.add(nameOfEvent);
        result.add(eventLocation);
        result.add(startDatesMili);
        result.add(startDates);
        result.add(endDatesMili);
        result.add(endDates);
        result.add(descriptions);
        result.add(id);

        //return startDates;
        return result;
    }


    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MMM dd,yyyy HH:mm");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}