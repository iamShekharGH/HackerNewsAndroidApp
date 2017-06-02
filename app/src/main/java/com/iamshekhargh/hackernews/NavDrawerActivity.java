package com.iamshekhargh.hackernews;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iamshekhargh.hackernews.datastore.DataStoreProvider;
import com.iamshekhargh.hackernews.datastore.interfaces.GetStories;
import com.iamshekhargh.hackernews.datastore.interfaces.GetStoryIds;
import com.iamshekhargh.hackernews.enums.StoryType;
import com.iamshekhargh.hackernews.models.Story;
import com.iamshekhargh.hackernews.models.localModels.StoryIds;
import com.iamshekhargh.hackernews.topStories.Fragment_StoryList;
import com.iamshekhargh.hackernews.topStories.StoryInformationPage;
import com.iamshekhargh.hackernews.utilitys.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Fragment_StoryList.Helper,
        StoryInformationPage.OnFragmentInteractionListener,
        GetStories.Interactor, GetStoryIds.Interactor {

    private static final String TAG = "NavDrawerActivity";

    StoryIds topStoryIds;
    StoryIds bestStoryIds;
    StoryIds showStoryIds;
    StoryIds jobStoryIds;
    StoryIds newStoryIds;
    StoryIds askStoryIds;

    String url;
    GetStories getStories;
    StoryType storyType;

    GetStoryIds getStoryIdsBest;
    GetStoryIds getStoryIdsShow;
    GetStoryIds getStoryIdsJob;
    GetStoryIds getStoryIdsNew;
    GetStoryIds getStoryIdsAsk;

    Story openStoryPage;

    Fragment_StoryList fragmentStoryList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container)
    ConstraintLayout fragmentContainer;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    public static void startNavDrawerActivity(Activity activity) {
        Intent intent = new Intent(activity, NavDrawerActivity.class);
        activity.startActivity(intent);
    }

    public static void startNavDrawerActivity(Activity activity, String[] strings) {
        Intent intent = new Intent(activity, NavDrawerActivity.class);
        intent.putExtra("StoryIds", strings);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setBackgroundResource(R.drawable.toolbar_background);
        toolbar.setTitle("Hacker News");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleText));
        setSupportActionBar(toolbar);

        initialiseElements();

    }

    private void initialiseElements() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        storyType = StoryType.TOP;

        initialiseStoryIdArray();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        topStoryIds.setStoryList(new ArrayList<Story>());
        bestStoryIds.setStoryList(new ArrayList<Story>());
        showStoryIds.setStoryList(new ArrayList<Story>());
        jobStoryIds.setStoryList(new ArrayList<Story>());
        newStoryIds.setStoryList(new ArrayList<Story>());
        askStoryIds.setStoryList(new ArrayList<Story>());

        //TODO chk for null later.
        topStoryIds.setList(getIntent().getExtras().getStringArray("StoryIds"));
        L.i("StoryIdSize", topStoryIds.getList().length);

        getStories = DataStoreProvider.getStoryList(0, this, this);

        getAskStoryIds();
        getShowStoryIds();
        getJobStoryIds();
        getNewStoryIds();
        getBestStoryIds();

        fragmentStoryList = new Fragment_StoryList();


//        addfragment(fragmentLoadStories);
        makeAllTheCalls();
        addfragment(fragmentStoryList);
    }

    private void initialiseStoryIdArray() {

        topStoryIds = new StoryIds();
        topStoryIds.setStoryType(StoryType.TOP);
        topStoryIds.setCurrentStoryCount(0);

        bestStoryIds = new StoryIds();
        bestStoryIds.setStoryType(StoryType.BEST);
        bestStoryIds.setCurrentStoryCount(0);

        showStoryIds = new StoryIds();
        showStoryIds.setStoryType(StoryType.SHOW);
        showStoryIds.setCurrentStoryCount(0);

        jobStoryIds = new StoryIds();
        jobStoryIds.setStoryType(StoryType.JOB);
        jobStoryIds.setCurrentStoryCount(0);

        newStoryIds = new StoryIds();
        newStoryIds.setStoryType(StoryType.NEW);
        newStoryIds.setCurrentStoryCount(0);

        askStoryIds = new StoryIds();
        askStoryIds.setStoryType(StoryType.ASK);
        askStoryIds.setCurrentStoryCount(0);

    }


    private void getBestStoryIds() {
        getStoryIdsBest = DataStoreProvider.getStoryListIds(new GetStoryIds.Interactor() {
            @Override
            public void onDataArrived(String[] list) {
                bestStoryIds.setList(list);
                bestStoryIds.setTotalNumberOfStories(list.length);
                L.i(TAG + " BestStoryIds", new Gson().toJson(bestStoryIds.getList()));
            }

            @Override
            public void onError() {

            }
        });
        getStoryIdsBest.getBestStoriesIDs();
    }

    private void getShowStoryIds() {
        getStoryIdsShow = DataStoreProvider.getStoryListIds(new GetStoryIds.Interactor() {
            @Override
            public void onDataArrived(String[] list) {
                showStoryIds.setList(list);
                showStoryIds.setTotalNumberOfStories(list.length);
                L.i(TAG + " ShowStoriesIds", new Gson().toJson(showStoryIds));

            }

            @Override
            public void onError() {

            }
        });
        getStoryIdsShow.getShowStorieIDs();
    }

    private void getJobStoryIds() {
        getStoryIdsJob = DataStoreProvider.getStoryListIds(new GetStoryIds.Interactor() {
            @Override
            public void onDataArrived(String[] list) {
                jobStoryIds.setList(list);
                jobStoryIds.setTotalNumberOfStories(list.length);
                L.i(TAG + " JobStoryIds", new Gson().toJson(jobStoryIds.getList()));
            }

            @Override
            public void onError() {

            }
        });
        getStoryIdsJob.getJobStorieIDs();
    }

    private void getNewStoryIds() {
        getStoryIdsNew = DataStoreProvider.getStoryListIds(new GetStoryIds.Interactor() {
            @Override
            public void onDataArrived(String[] list) {
                newStoryIds.setList(list);
                newStoryIds.setTotalNumberOfStories(list.length);
                L.i(TAG + " NewStoryIds", new Gson().toJson(newStoryIds.getList()));
            }

            @Override
            public void onError() {

            }
        });
        getStoryIdsNew.getNewStoriesIDs();
    }

    private void getAskStoryIds() {

        getStoryIdsAsk = DataStoreProvider.getStoryListIds(new GetStoryIds.Interactor() {
            @Override
            public void onDataArrived(String[] list) {
                askStoryIds.setList(list);
                askStoryIds.setTotalNumberOfStories(list.length);
                L.i(TAG + " AskStoryIds", new Gson().toJson(askStoryIds.getList()));
            }

            @Override
            public void onError() {

            }
        });
        getStoryIdsAsk.getAskStorieIDs();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() < 2) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSettings() {
        Toast.makeText(getApplicationContext(), "Create a Settings Page.", Toast.LENGTH_SHORT).show();
    }

    private void addfragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_top_stories) {
            showTopStories();
        } else if (id == R.id.nav_new) {
            showNewStories();
        } else if (id == R.id.nav_best) {
            showBestStories();
        } else if (id == R.id.nav_show) {
            showShow();
        } else if (id == R.id.nav_job) {
            showJobs();
        } else if (id == R.id.nav_ask) {
            showAskStories();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//  NavDrawer Functions.
//  ---------------------------------------------------->


    private void showTopStories() {

        showToast("Showing Top Stories.");
        changeStoryType(StoryType.TOP);
        L.i(TAG, "FragmentBackStack Count :\t" + getSupportFragmentManager().getBackStackEntryCount());

    }

    private void showAskStories() {
        showToast("Showing Ask Stories.");
        changeStoryType(StoryType.ASK);
    }

    private void showJobs() {
        showToast("Showing Jobs here");
        changeStoryType(StoryType.JOB);
    }

    private void showShow() {
        showToast("Showing Show.. haha");
        changeStoryType(StoryType.SHOW);
    }

    private void showBestStories() {
        showToast("Showing Best Stories.");
        changeStoryType(StoryType.BEST);
    }

    private void showNewStories() {
        showToast("Showing news Stories.");
        changeStoryType(StoryType.NEW);
    }

//  <----------------------------------------------------


//  Fragment_StoryList.Helper interface functions
//  ---------------------------------------------------->

    @Override
    public List<Story> getInfo() {
//        return storyListTopStories;
        switch (storyType) {
            case TOP:
                return topStoryIds.getStoryList();

            case BEST:
                return bestStoryIds.getStoryList();

            case ASK:
                return askStoryIds.getStoryList();

            case NEW:
                return newStoryIds.getStoryList();

            case SHOW:
                return showStoryIds.getStoryList();

            case JOB:
                return jobStoryIds.getStoryList();

            default:
                return topStoryIds.getStoryList();

        }
//        return topStoryIds.getStoryList();
    }

    @Override
    public void openThisUrl(String url) {
        this.url = url;
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
//        addfragment(news Fragment_Webview());
    }

    @Override
    public void openStoryInfoPage(Story story) {
        openStoryPage = story;
        new StoryInformationPage().show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void refreshData() {
//        callsMade = 0;
//        receivedCount = 0;
        makeAllTheCalls();

        //TODO Stop refreshing swipe refresh


    }

    @Override
    public void lazyLoad() {
        makeAllTheCalls();
    }

//  <----------------------------------------------------


//  Class Helper Functions
//  ---------------------------------------------------->

    public void changeStoryType(StoryType storyType) {

        this.storyType = storyType;
        fragmentStoryList.changeAdapter(storyType);
        toolbar.setTitle(storyType.name() + " STORIES");
//        fragmentStoryList.changeListTitle(storyType.name() + " STORIES");


    }

    private void makeAllTheCalls() {

        if (storyType == StoryType.TOP) {
            makeCalls(topStoryIds);

        } else if (storyType == StoryType.NEW) {
            makeCalls(newStoryIds);

        } else if (storyType == StoryType.BEST) {
            makeCalls(bestStoryIds);

        } else if (storyType == StoryType.SHOW) {
            makeCalls(showStoryIds);

        } else if (storyType == StoryType.ASK) {
            makeCalls(askStoryIds);

        } else if (storyType == StoryType.JOB) {
            makeCalls(jobStoryIds);

        } else {
            storyType = StoryType.TOP;
            makeCalls(topStoryIds);
        }

    }

    private void makeCalls(StoryIds storyIds) {

        L.i(TAG, "makeCallsFn storyType\t:" + storyType);

        for (int i = 0; i < 10; i++) {
            if (storyIds.getCurrentStoryCount() < storyIds.getList().length) {
                getStories.getStories(storyIds.getList()[storyIds.getCurrentStoryCount()]);
                storyIds.setCurrentStoryCount(storyIds.getCurrentStoryCount() + 1);
            } else {
                fragmentStoryList.hideSwipeRefreshing();
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//  <----------------------------------------------------


//    GetStories.Interactor methods
//  ---------------------------------------------------->

    @Override
    public void onDataArrived(Story story) {
        List<Story> tempList = new ArrayList<>();
        tempList.add(story);
//        storyListTopStories.add(story);
        topStoryIds.getStoryList().add(story);

//        news ArrayList<>().add(story);
//        L.i(TAG, news Gson().toJson(story));


//        resultString = resultString + "Call No:\t" + receivedCount + "\n" + news Gson().toJson(story) + "\n" + "-----------------------------" + "\n";
//        if (receivedCount % 19 == 0) {
//            fragmentLoadStories.hideProgressBar();
//            fragmentLoadStories.UpdateText(resultString);
//            fragmentStoryList.loadInfoIntoAdapter(storyListTopStories);
//            fragmentStoryList.insertMoreInfoAdapter(tempList);
//        }

        fragmentStoryList.insertMoreInfoAdapter(tempList, storyType);
    }

    @Override
    public void totalCallsMade() {


//        receivedCount++;
    }

    @Override
    public void errorReceiveingData() {

    }

//  <----------------------------------------------------


//  GetStorieIds.Interactor methods
//  ---------------------------------------------------->

    @Override
    public void onDataArrived(String[] list) {
        L.i(TAG + "arrivedInfo", new Gson().toJson(list));

    }

    @Override
    public void onError() {

    }

    @Override
    public Story getStory() {
        return openStoryPage;
    }

//  <----------------------------------------------------

}
