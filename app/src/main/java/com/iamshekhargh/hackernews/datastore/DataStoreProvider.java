package com.iamshekhargh.hackernews.datastore;

import android.app.Activity;

import com.iamshekhargh.hackernews.datastore.interfaces.GetStories;

/**
 * Created by <<-- iamShekharGH -->>
 * on 16 May 2017
 * at 11:59 AM.
 */

public class DataStoreProvider {

    public DataStoreProvider() {
    }

    public static GetStories getStoryList(int pageNo, Activity activity, GetStories.Interactor interactor) {
        return new GetStoriesDatastore(activity, interactor);
    }

    public static GetStoryIdsDatastore getStoryListIds(GetStoryIdsDatastore.Interactor interactor) {
        return new GetStoryIdsDatastore(interactor);

    }


}
