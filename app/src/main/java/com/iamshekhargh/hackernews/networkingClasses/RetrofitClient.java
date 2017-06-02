package com.iamshekhargh.hackernews.networkingClasses;

import com.iamshekhargh.hackernews.environment.EnvironmentProvider;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by <<-- iamShekharGH -->>
 * on 27 April 2017
 * at 2:55 PM.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;
    //    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    //    public static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/item/";
//    public static final String BASE_URL = "https://hacker-news.firebaseio.com";
//    public static final String BASE_URL = "http://hn.algolia.com/api/v1/search?tags=front_page";
//    public static final String BASE_URL = "http://hn.algolia.com";

    public static Retrofit getClient() {
        //  Using it to print log as of now.
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getUrl())
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static String getUrl() {
        return new EnvironmentProvider().getEnvironment().getURL();
    }

}
