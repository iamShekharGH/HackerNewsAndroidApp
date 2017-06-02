package com.iamshekhargh.hackernews.models;

/**
 * Created by <<-- iamShekharGH -->>
 * on 03 May 2017
 * at 12:21 PM.
 */


import java.util.List;

/**
 * {
 * "created_at":"2017-05-02T18:00:31.000Z",
 * "title":"CRISPR eliminates HIV-1 infection in live animals",
 * "url":"http://www.sciencedirect.com/science/article/pii/S1525001617301107",
 * "author":"shannietron",
 * "points":943,
 * "story_text":null,
 * "comment_text":null,
 * "num_comments":175,
 * "story_id":null,
 * "story_title":null,
 * "story_url":null,
 * "parent_id":null,
 * "created_at_i":1493748031,
 * "_tags":[  ],
 * "objectID":"14248827",
 * "_highlightResult":{
 * "title":{  },
 * "url":{  },
 * "author":{  }
 * }
 * }
 */


public class StoryItem {

    String created_at;
    String title;
    String url;
    String author;
    Integer points;
    String story_text;
    String comment_text;
    int num_comments;
    String story_id;
    String story_title;
    String story_url;
    String parent_id;
    String created_at_i;
    List<String> _tags;
    int objectID;
    HighligtResult _highlightResult;


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getStory_text() {
        return story_text;
    }

    public void setStory_text(String story_text) {
        this.story_text = story_text;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getStory_url() {
        return story_url;
    }

    public void setStory_url(String story_url) {
        this.story_url = story_url;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getCreated_at_i() {
        return created_at_i;
    }

    public void setCreated_at_i(String created_at_i) {
        this.created_at_i = created_at_i;
    }

    public List<String> get_tags() {
        return _tags;
    }

    public void set_tags(List<String> _tags) {
        this._tags = _tags;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public HighligtResult get_highlightResult() {
        return _highlightResult;
    }

    public void set_highlightResult(HighligtResult _highlightResult) {
        this._highlightResult = _highlightResult;
    }
}
