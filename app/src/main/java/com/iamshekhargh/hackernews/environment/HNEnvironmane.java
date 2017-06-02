package com.iamshekhargh.hackernews.environment;

/**
 * Created by <<-- iamShekharGH -->>
 * on 13 May 2017
 * at 10:54 PM.
 */

public class HNEnvironmane implements Environment {
    private static final String url = "https://hacker-news.firebaseio.com";

    @Override
    public String getURL() {
        return url;
    }
}
