package com.iamshekhargh.hackernews.environment;

/**
 * Created by <<-- iamShekharGH -->>
 * on 13 May 2017
 * at 10:53 PM.
 */

public class AlgoliaEnvironment implements Environment {
    String url = "http://hn.algolia.com";
    @Override
    public String getURL() {
        return url;
    }
}
