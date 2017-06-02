package com.iamshekhargh.hackernews.models;

/**
 * Created by <<-- iamShekharGH -->>
 * on 19 May 2017
 * at 12:50 PM.
 */

public class User {
    String id;
    long delay;
    long created;
    int karma;
    String[] submitted;
    //    List<String> submitted;
    String about;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public String[] getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String[] submitted) {
        this.submitted = submitted;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
