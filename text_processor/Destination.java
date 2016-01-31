package com.example.shahin.test.text_processor;

import java.util.HashMap;
import java.util.Map;

public class Destination {

    private Map<String, String> destination_list = new HashMap<String, String>();

    public Destination() {
        destination_list.put("search", "search");
        destination_list.put("what", "search");
        destination_list.put("when", "search");
        destination_list.put("how", "search");

        destination_list.put("alarm", "alarm");

        destination_list.put("event", "event");

        destination_list.put("send", "message");
        destination_list.put("text", "message");
        destination_list.put("message", "message");
        destination_list.put("tell", "message");

        destination_list.put("call", "call");
        destination_list.put("dial", "call");

        destination_list.put("where", "location");
        destination_list.put("find", "location");

        destination_list.put("telegram", "telegram");

        destination_list.put("insta", "insta");

    }

    public Map<String, String> get_destination_list() {
        return this.destination_list;
    }

}
