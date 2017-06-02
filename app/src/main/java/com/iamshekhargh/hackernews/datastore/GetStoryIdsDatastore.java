package com.iamshekhargh.hackernews.datastore;

import com.iamshekhargh.hackernews.datastore.interfaces.GetStoryIds;
import com.iamshekhargh.hackernews.networkingClasses.ApiInterface;
import com.iamshekhargh.hackernews.networkingClasses.CustomCallback;
import com.iamshekhargh.hackernews.networkingClasses.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by <<-- iamShekharGH -->>
 * on 24 May 2017
 * at 1:04 PM.
 */

public class GetStoryIdsDatastore implements GetStoryIds {

    Call<String[]> call;
    String TAG = "GetStoryIdsDatastore";
    GetStoryIds.Interactor interactor;

    public GetStoryIdsDatastore(Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void getStoryIDs() {
        call = RetrofitClient.getClient().create(ApiInterface.class).getTopStoriesArray();
        call.enqueue(new CustomGetStoryIds());
    }

    @Override
    public void getNewStoriesIDs() {
        call = RetrofitClient.getClient().create(ApiInterface.class).getNewStories();
        call.enqueue(new CustomGetStoryIds());

    }

    @Override
    public void getBestStoriesIDs() {
        call = RetrofitClient.getClient().create(ApiInterface.class).getBestStories();
        call.enqueue(new CustomGetStoryIds());
    }

    @Override
    public void getAskStorieIDs() {
        call = RetrofitClient.getClient().create(ApiInterface.class).getAskStories();
        call.enqueue(new CustomGetStoryIds());
    }

    @Override
    public void getShowStorieIDs() {
        call = RetrofitClient.getClient().create(ApiInterface.class).getShowStories();
        call.enqueue(new CustomGetStoryIds());
    }

    @Override
    public void getJobStorieIDs() {
        call = RetrofitClient.getClient().create(ApiInterface.class).getJobStories();
        call.enqueue(new CustomGetStoryIds());
    }

    private class CustomGetStoryIds extends CustomCallback<String[]> {

        @Override
        public void onDataArrived(String[] strings) {
            interactor.onDataArrived(strings);

        }

        @Override
        public void onError() {
            interactor.onError();

        }

        @Override
        public void donnoWTFhappened(Response<String[]> response) {
            interactor.onError();

        }
    }
}
