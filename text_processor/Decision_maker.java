package com.example.shahin.test.text_processor;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shahin.test.R;
import com.example.shahin.test.call_maker.call;
import com.example.shahin.test.http;
import com.example.shahin.test.message_sender.sms;
import com.example.shahin.test.reminder.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import com.example.shahin.test.reminder.alarm;

import com.example.shahin.test.search.searchItem;
import com.example.shahin.test.search.searchItem_activity;

import junit.framework.Test;

import com.example.shahin.test.myLocationFinder;

public class Decision_maker {

    private String sentence = "";
    private List<String> splitted_sentence;
    private JSONObject tagged_sentence;

    private Context ctx;

    private TextToSpeech ttsFail;



    public Decision_maker(String msg, Context ctx1) {
        this.sentence = msg;
        ctx = ctx1;
    }

    private void set_spilitted_sentence() {
        this.splitted_sentence = new ArrayList<String>(Arrays.asList(this.sentence.split(" ")));
    }

    private void set_tagged_sentence() {
        POSTagger posTagger = new POSTagger(splitted_sentence);
        this.tagged_sentence = posTagger.getTagged();
    }

    public void dispatch() {
        this.set_spilitted_sentence();
        this.set_tagged_sentence();


        try {
            String verb = this.tagged_sentence.getString("verb");

            String destinationComponent = new Destination().get_destination_list().get(verb);

            if (destinationComponent.equals("search")) {
                System.out.println("************");
                System.out.println(sentence);
                Intent searchIntent = new Intent(ctx, searchItem_activity.class);
                searchIntent.putExtra("sentence", sentence);
                ctx.startActivity(searchIntent);


            } else if (destinationComponent.equals("alarm")) {
                alarm alarm_object = new alarm(sentence, ctx);

            } else if (destinationComponent.equals("event")) {
                Event event_object = new Event(ctx, sentence);

            } else if (destinationComponent.equals("message")) {
                sms sms_object = new sms(sentence, ctx);

            } else if (destinationComponent.equals("call")) {
                call call_object = new call(sentence, ctx);

            } else if (destinationComponent.equals("telegram")) {
                Intent telegram = new Intent(android.content.Intent.ACTION_VIEW);
                telegram.setData(Uri.parse("https://telegram.me/"));
                telegram.setPackage("org.telegram.messenger");
                ctx.startActivity(telegram);


            } else if (destinationComponent.equals("insta")) {
                Uri uri = Uri.parse("http://instagram.com/_u/shaaaahiiin");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    ctx.startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/shaaaahiiin")));
                }

            } else if (destinationComponent.equals("location")){
                if (sentence.contains(" where is my location")){
                    Intent myLocationIntent = new Intent(ctx, myLocationFinder.class);
                    ctx.startActivity(myLocationIntent);
                }
                else {
                    JSONObject json;
                    String android_id = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
                    HashMap mapParams = new HashMap();
                    mapParams.put(new String("device_id"), android_id);
                    mapParams.put(new String("component"), "location");
                    mapParams.put(new String("message"), sentence);
                    JSONObject params = new JSONObject(mapParams);
                    http httpObject = new http("http://52.48.35.134:5000", params);
                    try {
                        httpObject.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    try {
                        json = new JSONObject(httpObject.output);
                        Double lat = json.getDouble("lat");
                        Double lon = json.getDouble("long");
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lon);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        ctx.startActivity(intent);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            } else {
                // sorry i couldnt understand your message %s(this.sentence)
                ttsFail = new TextToSpeech(ctx.getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            ttsFail.setLanguage(Locale.UK);
                            String toSpeak = "Sry, could'nt understand what you said.";
                            Toast.makeText(ctx.getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                            ttsFail.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
