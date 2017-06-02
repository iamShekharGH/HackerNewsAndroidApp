package com.iamshekhargh.hackernews.topStories;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iamshekhargh.hackernews.R;
import com.iamshekhargh.hackernews.models.Story;
import com.iamshekhargh.hackernews.utilitys.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <<-- iamShekharGH -->>
 * on 02 May 2017
 * at 2:11 PM.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {

    List<Story> storyItem;
    Context context;
    Listener listener;
    String TAG = "StoryAdapter";


    public StoryAdapter(List<Story> storyItem, Context context, Fragment_StoryList fragment_storyList) {
        this.storyItem = storyItem;
        this.context = context;
        L.i(TAG, "StorySize\t:" + storyItem.size());
        listener = (Listener) fragment_storyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_story, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (storyItem == null) {
            return;
        }

        // This is for lazy loading. 17 might seem out of the blue but that is something that works nicely and hence using that.
        if (position % 17 == 0 || (storyItem.size() - 2 == position) || storyItem.size() == position || storyItem.size() < 1) {
            fetchMoreItems();
        }

        final Story item = storyItem.get(position);

        if (item != null) {

            if (item.getTitle() != null)
                holder.storyCardTitle.setText(item.getTitle());
            else holder.storyCardTitle.setText("");

            if (item.getBy() != null)
                holder.storyCardBy.setText(item.getBy());
            else holder.storyCardBy.setText("");

            if (item.getScore() != null) {
                int temp = item.getScore();
                holder.storyCardScore.setText(temp + " points.");
            } else holder.storyCardScore.setText("");

            if (item.getText() != null)
                holder.storyCardText.setText(item.getText());
            else holder.storyCardText.setVisibility(View.GONE);


            holder.storyCardUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openFragment(item);
                }
            });


            holder.storyCardOpenInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.getUrl() != null)
                        openLink(item);
                    else showToast("Link not present.");
                }
            });

        }
    }

    @Override
    public int getItemCount() {
//        if (storyItem == null || storyItem.size() == 0) {
//            return 1;
//        }
        return storyItem.size();
    }


    private void fetchMoreItems() {
        L.i(TAG, "Fetch more items.");
        listener.fetchMoreAndShow();
    }

    public void addData(List<Story> list) {
        if (list != null) {
            int temp = storyItem.size();
            storyItem.addAll(list);
            notifyItemInserted(temp);
        }
    }

    public void replaceAllData(List<Story> list) {
        if (list != null) {
            storyItem = new ArrayList<>();
            storyItem.addAll(list);
            notifyDataSetChanged();
        }
    }

    private void openLink(Story item) {
        showToast("" + item.getUrl());
        listener.openLinkInChrome(item.getUrl());
    }

    private void openFragment(Story item) {
        listener.launchInfoPage(item);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.storyCard_title)
        TextView storyCardTitle;
        @BindView(R.id.storyCard_by)
        TextView storyCardBy;
        @BindView(R.id.storyCard_score)
        TextView storyCardScore;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.storyCard_url)
        ConstraintLayout storyCardUrl;
        @BindView(R.id.storyCard_text)
        TextView storyCardText;
        @BindView(R.id.storyCard_openInfo)
        ConstraintLayout storyCardOpenInfo;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Listener {
        void openLinkInChrome(String temp);

        void launchInfoPage(Story story);

        void fetchMoreAndShow();

        List<Story> getList();
    }


}
