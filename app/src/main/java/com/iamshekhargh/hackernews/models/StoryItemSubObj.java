package com.iamshekhargh.hackernews.models;

import java.util.List;

/**
 * Created by <<-- iamShekharGH -->>
 * on 03 May 2017
 * at 12:31 PM.
 */

public class StoryItemSubObj {

    String value;
    String matchLevel;
    List<String> matchedWords;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<String> matchedWords) {
        this.matchedWords = matchedWords;
    }
}
