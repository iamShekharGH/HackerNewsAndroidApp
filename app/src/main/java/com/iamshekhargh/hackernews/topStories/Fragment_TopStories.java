package com.iamshekhargh.hackernews.topStories;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iamshekhargh.hackernews.NavDrawerActivity;
import com.iamshekhargh.hackernews.R;
import com.iamshekhargh.hackernews.models.Front_page;
import com.iamshekhargh.hackernews.models.Story;
import com.iamshekhargh.hackernews.networkingClasses.ApiInterface;
import com.iamshekhargh.hackernews.networkingClasses.CustomCallback;
import com.iamshekhargh.hackernews.networkingClasses.RetrofitClient;
import com.iamshekhargh.hackernews.utilitys.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by <<-- iamShekharGH -->>
 * on 28 April 2017
 * at 12:11 PM.
 */

public class Fragment_TopStories extends Fragment implements View.OnClickListener {


    ProgressDialog progressDialog;
    String[] idList;
    List<Story> storyList;
    int postCount;
    int i = 0;
    boolean ready = false;
    Front_page front_pagee;
    Helper helper;

    @BindView(R.id.topstories_response)
    TextView topstoriesResponse;
    @BindView(R.id.topstories_fetch_data)
    Button topstoriesFetchData;
    @BindView(R.id.button2)
    Button button2;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Helper) {
            helper = (Helper) context;
        } else throw new RuntimeException("Must implement Fragment_TopStories.Helper ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topstories, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        topstoriesFetchData.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topstories_fetch_data:
                showProgressBar();
                fetchFrontPage();
                break;
            case R.id.button2:
                NavDrawerActivity.startNavDrawerActivity(getActivity());
                break;
        }
    }

    private void fetchButtonClick() {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<String[]> call = apiInterface.getTopStoriesArray();
        call.enqueue(new CustomCallback<String[]>() {
            @Override
            public void onDataArrived(String[] strings) {
                idList = strings;
                postCount = strings.length;
//                dismissProgressDialog();
                setUpTextView(Arrays.toString(strings));
//                secondAPICall();
                fetchAllTopStories();

            }

            @Override
            public void onError() {
//                idList = strings;
                dismissProgressDialog();
//                setUpTextView(Arrays.toString(strings));

            }

            @Override
            public void donnoWTFhappened(Response<String[]> response) {
                dismissProgressDialog();
                setUpTextView(response.toString());
            }
        });
    }

    private void secondAPICall() {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        L.i(idList[1]);

        Call<Story> call = apiInterface.getStories(idList[1]);
        call.enqueue(new CustomCallback<Story>() {
            @Override
            public void onDataArrived(Story story) {
                dismissProgressDialog();
                setUpTextView(new Gson().toJson(story));

            }

            @Override
            public void onError() {
                dismissProgressDialog();
//                setUpTextView(new Gson().toJson(story));
            }

            @Override
            public void donnoWTFhappened(Response<Story> response) {
                dismissProgressDialog();
                setUpTextView(response.toString());
            }
        });

    }

    private void fetchAllTopStories() {
        storyList = new ArrayList<>();
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        List<String> storyList1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            storyList1.add(idList[i]);
        }
        for (String s : storyList1) {
            Call<Story> call = apiInterface.getStories(s);
            call.enqueue(new CustomCallback<Story>() {
                @Override
                public void onDataArrived(Story story) {
                    storyList.add(story);
                    setUpTextView("[" + i + "]\t::" + convertStringListToString(storyList));
                }

                @Override
                public void onError() {

                }

                @Override
                public void donnoWTFhappened(Response<Story> response) {
                    L.i(new Gson().toJson(response.body()));
                }
            });
        }

//        while (i < postCount) {
//            Call<Story> call = apiInterface.getStories(idList[i]);
//            call.enqueue(new CustomCallback<Story>() {
//                @Override
//                public void onDataArrived(Story story) {
//                    i++;
//                    topStoryList.add(story);
//
//                }
//
//                @Override
//                public void onError(Story story) {
//                    i++;
//                }
//
//                @Override
//                public void donnoWTFhappened(Response<Story> response) {
//                    L.i(new Gson().toJson(response.body()));
//                }
//            });
////            if (i == --postCount) {
////                ready = true;
//                setUpTextView("["+i+"]\t::" +convertStringListToString(topStoryList));
////            }
//        }
    }

    private void fetchFrontPage() {
        storyList = new ArrayList<>();
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<Front_page> call = apiInterface.getTagStories("front_page");
        call.enqueue(new CustomCallback<Front_page>() {
            @Override
            public void onDataArrived(Front_page front_page) {
                dismissProgressDialog();
                doSomethingWithData(front_page);
                helper.onDataReceived(front_page);
            }


            @Override
            public void onError() {
                dismissProgressDialog();
                doSomethingWithData(null);
            }

            @Override
            public void donnoWTFhappened(Response<Front_page> response) {
                dismissProgressDialog();
                L.i("someShit happened\t", "" + response.message());
            }
        });
    }

    private void doSomethingWithData(Front_page front_page) {
        if (front_page != null) {
            setUpTextView(new GsonBuilder().setPrettyPrinting().create().toJson(front_page));
        }
    }

    private void showProgressBar() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    private void showProgressBar(String m) {
        if (progressDialog != null) {
            progressDialog.setTitle(m);
            progressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private String convertStringListToString(List<Story> sL) {
        String finalTest = "";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (Story s : sL) {
            finalTest = finalTest + gson.toJson(s) + "\n";
        }
        return finalTest;
    }

    private void setupTextViewListString(List<String> list) {
        if (topstoriesResponse != null && list != null) {
            String temp = "";
            for (String s : list) {
                temp = temp + s + "\t,";
            }
            setUpTextView(temp);
        } else {
            setUpTextView("No Response.");
        }
    }

    private void setUpTextView(String text) {
        if (topstoriesResponse != null) {
            topstoriesResponse.setText(text);
        }
    }

    public interface Helper {
        void onDataReceived(Front_page front_page);
    }

}
