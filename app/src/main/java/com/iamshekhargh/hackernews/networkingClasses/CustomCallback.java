package com.iamshekhargh.hackernews.networkingClasses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by <<-- iamShekharGH -->>
 * on 28 April 2017
 * at 12:29 PM.
 */

public abstract class CustomCallback<T> implements Callback<T> {

    public abstract void onDataArrived(T t);

    public abstract void onError();

    public CustomCallback() {
        //Initialise whatever you'll need later.

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        switch (response.code()) {
            case 200:
            case 201:
                onDataArrived(response.body());
                break;
            case 401:
            case 412:
                onError();
                break;
            default:
                donnoWTFhappened(response);

        }
    }

    public abstract void donnoWTFhappened(Response<T> response);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError();

    }
}
