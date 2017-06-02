package com.iamshekhargh.hackernews.topStories;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iamshekhargh.hackernews.R;
import com.iamshekhargh.hackernews.enums.StoryType;
import com.iamshekhargh.hackernews.models.Story;
import com.iamshekhargh.hackernews.utilitys.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by <<-- iamShekharGH -->>
 * on 30 April 2017
 * at 3:15 PM.
 */

public class Fragment_StoryList extends Fragment implements StoryAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    Helper helper;

    //Adapters.
    StoryAdapter topStoryAdapter;
    StoryAdapter bestStoryAdapter;
    StoryAdapter askStoryAdapter;
    StoryAdapter showStoryAdapter;
    StoryAdapter jobStoryAdapter;
    StoryAdapter newStoryAdapter;

    //Story Lists.
    List<Story> topStoryList;
    List<Story> bestStoryList;
    List<Story> askStoryList;
    List<Story> showStoryList;
    List<Story> jobStoryList;
    List<Story> newStoryList;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.title)
    TextView title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initialiseStuff();
    }

    private void initialiseStuff() {

        topStoryList = new ArrayList<>();
        bestStoryList = new ArrayList<>();
        askStoryList = new ArrayList<>();
        showStoryList = new ArrayList<>();
        jobStoryList = new ArrayList<>();
        newStoryList = new ArrayList<>();
//        notifyList();
        if (topStoryList != null)
            L.i("Fragment_StoryList size\t:", topStoryList.size());
        swiperefresh.setEnabled(true);
        swiperefresh.setOnRefreshListener(this);

        showSwipeRefreshing();

        topStoryAdapter = new StoryAdapter(topStoryList, getContext(), this);
        bestStoryAdapter = new StoryAdapter(bestStoryList, getContext(), this);
        askStoryAdapter = new StoryAdapter(askStoryList, getContext(), this);
        showStoryAdapter = new StoryAdapter(showStoryList, getContext(), this);
        jobStoryAdapter = new StoryAdapter(jobStoryList, getContext(), this);
        newStoryAdapter = new StoryAdapter(newStoryList, getContext(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(topStoryAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.addOnItemTouchListener(news RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Helper) {
            helper = (Helper) context;
        } else throw new RuntimeException("Must implement Fragment_StoryList.Helper");
    }

    // Adapter Interface functions.

    @Override
    public void openLinkInChrome(String temp) {
        helper.openThisUrl(temp);

    }

    @Override
    public void launchInfoPage(Story story) {
        helper.openStoryInfoPage(story);

    }

    @Override
    public void fetchMoreAndShow() {
        helper.lazyLoad();
    }

    @Override
    public List<Story> getList() {
        return topStoryList;
    }

    @Override
    public void onRefresh() {
        helper.refreshData();
        showSwipeRefreshing();
    }

    // Class helper functions

    public void changeAdapter(StoryType storyType) {
        switch (storyType) {
            case TOP:
                recyclerView.setAdapter(topStoryAdapter);
                break;
            case BEST:
                recyclerView.setAdapter(bestStoryAdapter);
                break;
            case NEW:
                recyclerView.setAdapter(newStoryAdapter);
                break;
            case SHOW:
                recyclerView.setAdapter(showStoryAdapter);
                break;
            case ASK:
                recyclerView.setAdapter(askStoryAdapter);
                break;
            case JOB:
                recyclerView.setAdapter(jobStoryAdapter);
                break;
            default:
                break;
        }
        onRefresh();
    }

    public void changeListTitle(String text) {
        title.setText(text);
    }

    public void loadInfoIntoAdapter(List<Story> list) {
        hideSwipeRefreshing();
//        topStoryAdapter.notifyDataSetChanged();
        topStoryAdapter.replaceAllData(list);
    }

    public void insertMoreInfoAdapter(List<Story> list, StoryType storyType) {
        hideSwipeRefreshing();
        switch (storyType) {
            case TOP:
                topStoryAdapter.addData(list);
                break;
            case BEST:
                bestStoryAdapter.addData(list);
                break;
            case NEW:
                newStoryAdapter.addData(list);
                break;
            case SHOW:
                showStoryAdapter.addData(list);
                break;
            case JOB:
                jobStoryAdapter.addData(list);
                break;
            case ASK:
                askStoryAdapter.addData(list);
                break;
        }

    }

    public void notifyList() {
        topStoryList = helper.getInfo();
        topStoryAdapter.notifyDataSetChanged();
    }

    private void showSwipeRefreshing() {
        if (swiperefresh != null) {
            swiperefresh.setRefreshing(true);
        }
    }

    public void hideSwipeRefreshing() {
        if (swiperefresh != null) {
            swiperefresh.setRefreshing(false);
        }
    }


    public interface Helper {
//        Front_page getInformation();

        List<Story> getInfo();

        void openThisUrl(String url);

        void openStoryInfoPage(Story story);

        void refreshData();

        void lazyLoad();
    }
}
