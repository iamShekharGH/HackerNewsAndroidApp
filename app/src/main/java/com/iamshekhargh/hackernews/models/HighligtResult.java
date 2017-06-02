package com.iamshekhargh.hackernews.models;

/**
 * Created by <<-- iamShekharGH -->>
 * on 03 May 2017
 * at 12:45 PM.
 */

public class HighligtResult {
    StoryItemSubObj title;
    StoryItemSubObj url;
    StoryItemSubObj author;

    public StoryItemSubObj getTitle() {
        return title;
    }

    public void setTitle(StoryItemSubObj title) {
        this.title = title;
    }

    public StoryItemSubObj getUrl() {
        return url;
    }

    public void setUrl(StoryItemSubObj url) {
        this.url = url;
    }

    public StoryItemSubObj getAuthor() {
        return author;
    }

    public void setAuthor(StoryItemSubObj author) {
        this.author = author;
    }
}
