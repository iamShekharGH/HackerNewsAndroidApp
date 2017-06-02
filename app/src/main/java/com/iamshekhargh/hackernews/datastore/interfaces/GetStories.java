package com.iamshekhargh.hackernews.datastore.interfaces;

import com.iamshekhargh.hackernews.models.Story;

/**
 * Created by <<-- iamShekharGH -->>
 * on 16 May 2017
 * at 11:47 AM.
 */

public interface GetStories {
    void getStories(String id);

    interface Interactor {
        void onDataArrived(Story story);

        void totalCallsMade();

        void errorReceiveingData();

    }
}
