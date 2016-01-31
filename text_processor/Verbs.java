package com.example.shahin.test.text_processor;

import java.util.ArrayList;
import java.util.List;

public class Verbs {

    private List<String> verbs = new ArrayList<String>();

    public Verbs() {
        this.verbs.add("search");
        this.verbs.add("what");
        this.verbs.add("when");
        this.verbs.add("how");

        this.verbs.add("alarm");

        this.verbs.add("event");

        this.verbs.add("send");
        this.verbs.add("text");
        this.verbs.add("message");

        this.verbs.add("call");
        this.verbs.add("dial");
        this.verbs.add("tell");

        this.verbs.add("where");
        this.verbs.add("find");

        this.verbs.add("telegram");

        this.verbs.add("insta");

    }

    public List<String> getVerbs() {
        return this.verbs;
    }
}
