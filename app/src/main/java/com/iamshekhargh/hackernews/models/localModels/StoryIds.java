package com.iamshekhargh.hackernews.models.localModels;

import com.iamshekhargh.hackernews.enums.StoryType;
import com.iamshekhargh.hackernews.models.Story;

import java.util.List;

/**
 * Created by <<-- iamShekharGH -->>
 * on 30 May 2017
 * at 8:07 PM.
 */

public class StoryIds {

    String[] list;
    int totalNumberOfStories;
    List<Story> storyList;
    int currentStoryCount;
    StoryType storyType;


    public StoryType getStoryType() {
        return storyType;
    }

    public void setStoryType(StoryType storyType) {
        this.storyType = storyType;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }

    public String[] getList() {
        return list;
    }

    public void setList(String[] list) {
        this.list = list;
    }

    public int getTotalNumberOfStories() {
        return totalNumberOfStories;
    }

    public StoryIds() {

    }

    public void setTotalNumberOfStories(int totalNumberOfStories) {
        this.totalNumberOfStories = totalNumberOfStories;
    }

    public int getCurrentStoryCount() {
        return currentStoryCount;
    }

    public void setCurrentStoryCount(int currentStoryCount) {
        this.currentStoryCount = currentStoryCount;
    }

    public StoryIds(String[] list, int totalNumberOfStories, int currentStoryCount) {
        this.list = list;
        this.totalNumberOfStories = totalNumberOfStories;
        this.currentStoryCount = currentStoryCount;
    }
}
