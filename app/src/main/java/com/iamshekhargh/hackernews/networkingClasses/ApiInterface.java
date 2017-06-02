package com.iamshekhargh.hackernews.networkingClasses;

import com.iamshekhargh.hackernews.models.Front_page;
import com.iamshekhargh.hackernews.models.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by <<-- iamShekharGH -->>
 * on 27 April 2017
 * at 3:05 PM.
 */

public interface ApiInterface {

    @GET("v0/item/{id}.json")
    Call<Story> getStories(@Path("id") String id);

    //https://hacker-news.firebaseio.com/v0/topstories.json
    @GET("v0/topstories.json")
    Call<List<String>> getTopStories();

    @GET("v0/topstories.json")
    Call<String[]> getTopStoriesArray();

    @GET("v0/newstories.json")
    Call<String[]> getNewStories();

    @GET("v0/beststories.json")
    Call<String[]> getBestStories();

    @GET("v0/askstories.json")
    Call<String[]> getAskStories();

    @GET("v0/showstories.json")
    Call<String[]> getShowStories();

    @GET("v0/jobstories.json")
    Call<String[]> getJobStories();


    @GET("/api/v1/search")
    Call<Front_page> getTagStories(@Query("tags") String tags);

}
