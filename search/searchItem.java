package com.example.shahin.test.search;

/**
 * Created by Shahin on 1/30/16 AD.
 */
public class searchItem {

    private String Title;
    private String URL;
    private String Description;

    public searchItem(String title, String url, String description){

        this.Title = title;
        this.URL = url;
        this.Description = description;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getURL(){
        return this.URL;
    }

    public String getDescription(){
        return this.Description;
    }
}
