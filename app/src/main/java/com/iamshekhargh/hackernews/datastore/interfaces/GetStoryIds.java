package com.iamshekhargh.hackernews.datastore.interfaces;

/**
 * Created by <<-- iamShekharGH -->>
 * on 24 May 2017
 * at 12:59 PM.
 */

public interface GetStoryIds {

    void getStoryIDs();

    void getNewStoriesIDs();

    void getBestStoriesIDs();

    void getAskStorieIDs();

    void getShowStorieIDs();

    void getJobStorieIDs();

    interface Interactor {
        void onDataArrived(String[] list);

        void onError();

    }

}
