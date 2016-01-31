package com.example.shahin.test.text_processor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class POSTagger {

    private JSONObject obj = new JSONObject();
    private List<String> splittedSentence;
    private List<String> availableVerbs;

    public POSTagger(List<String> splittedData) {
        this.splittedSentence = splittedData;
        this.availableVerbs = new Verbs().getVerbs();
    }

    public JSONObject getTagged() {
        for(int i=0; i<this.availableVerbs.size(); i++) {
            if (this.splittedSentence.contains(this.availableVerbs.get(i))) {
                try {
                    this.obj.put("verb", this.availableVerbs.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return this.obj;
    }

}
