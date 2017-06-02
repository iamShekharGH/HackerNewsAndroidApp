package com.iamshekhargh.hackernews.datastore;

import android.app.Activity;

import com.iamshekhargh.hackernews.datastore.interfaces.GetStories;
import com.iamshekhargh.hackernews.models.Story;
import com.iamshekhargh.hackernews.networkingClasses.ApiInterface;
import com.iamshekhargh.hackernews.networkingClasses.CustomCallback;
import com.iamshekhargh.hackernews.networkingClasses.RetrofitClient;
import com.iamshekhargh.hackernews.utilitys.L;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by <<-- iamShekharGH -->>
 * on 16 May 2017
 * at 11:47 AM.
 */

public class GetStoriesDatastore implements GetStories {

    //TODO remove this if not necessary
    Activity activity;

    GetStories.Interactor interactor;
    Call<Story> call;
    String TAG = "GetStoriesDatastore";


    public GetStoriesDatastore(Activity activity, Interactor interactor) {
        this.activity = activity;
        this.interactor = interactor;
    }

    @Override
    public void getStories(String id) {

        call = RetrofitClient.getClient().create(ApiInterface.class).getStories(id);
        call.enqueue(new CustomGetStories());
    }


    private class CustomGetStories extends CustomCallback<Story> {

        @Override
        public void onDataArrived(Story story) {
            interactor.onDataArrived(story);
            interactor.totalCallsMade();
//            L.i(TAG,"Stories received :\t");

        }

        @Override
        public void onError() {
//            interactor.onDataArrived(story);
            interactor.errorReceiveingData();

        }

        @Override
        public void donnoWTFhappened(Response<Story> response) {
            interactor.totalCallsMade();
//            interactor.onDataArrived(response.body());
            L.i(TAG, "error receiveing -> " + response.body().getId());
            interactor.errorReceiveingData();

        }
    }
}
