package com.iamshekhargh.hackernews.topStories;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iamshekhargh.hackernews.R;
import com.iamshekhargh.hackernews.customElements.CustomTextView;
import com.iamshekhargh.hackernews.models.Story;
import com.iamshekhargh.hackernews.utilitys.L;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StoryInformationPage extends BottomSheetDialogFragment {

    private static final String TAG = "StoryInformationPage";
    @BindView(R.id.story_heading)
    CustomTextView storyHeading;
    @BindView(R.id.story_text)
    CustomTextView storyText;
    Unbinder unbinder;
    @BindView(R.id.story_by)
    CustomTextView storyBy;
    @BindView(R.id.story_time)
    CustomTextView storyTime;
    @BindView(R.id.story_date)
    CustomTextView storyDate;
    private OnFragmentInteractionListener mListener;
    Story story;


    public StoryInformationPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_information_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.story = mListener.getStory();

        setUpView();

    }

    private void setUpView() {
        if (story.getTitle() != null)
            storyHeading.setText(story.getTitle());
        if (story.getText() != null)
            storyText.setText(Html.fromHtml(story.getText()));
        else storyText.setText("No info provided.");

        if (story.getBy() != null) {
            storyBy.setText("- " + story.getBy());
        }


        long time = story.getTime();
        L.i(TAG, "Time in millis\t:" + story.getTime());
        Date date = new Date(time);
        SimpleDateFormat storyDateSDF = new SimpleDateFormat(" dd, MMM yyyy");
        SimpleDateFormat storyTimeSDF = new SimpleDateFormat(" hh:mm aa");
        String dateString = storyDateSDF.format(date);
        String timeString = storyTimeSDF.format(date);

        storyDate.setText(dateString);
        storyTime.setText(timeString);
//        storyTime.setVisibility(View.GONE);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        Story getStory();
    }
}


/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link ItemListDialogFragment.Listener}.</p>
 * <p>
 * public class ItemListDialogFragment extends BottomSheetDialogFragment {
 * <p>
 * // TODO: Customize parameter argument names
 * private static final String ARG_ITEM_COUNT = "item_count";
 * private Listener mListener;
 * <p>
 * // TODO: Customize parameters
 * public static ItemListDialogFragment newInstance(int itemCount) {
 * final ItemListDialogFragment fragment = new ItemListDialogFragment();
 * final Bundle args = new Bundle();
 * args.putInt(ARG_ITEM_COUNT, itemCount);
 * fragment.setArguments(args);
 * return fragment;
 * }
 *
 * @Nullable
 * @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
 * @Nullable Bundle savedInstanceState) {
 * return inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
 * }
 * @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
 * //        final RecyclerView recyclerView = (RecyclerView) view;
 * //        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
 * //        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT)));
 * }
 * @Override public void onAttach(Context context) {
 * super.onAttach(context);
 * final Fragment parent = getParentFragment();
 * if (parent != null) {
 * mListener = (Listener) parent;
 * } else {
 * mListener = (Listener) context;
 * }
 * }
 * @Override public void onDetach() {
 * mListener = null;
 * super.onDetach();
 * }
 * <p>
 * public interface Listener {
 * void onItemClicked(int position);
 * }
 * <p>
 * private class ViewHolder extends RecyclerView.ViewHolder {
 * <p>
 * final TextView text;
 * <p>
 * ViewHolder(LayoutInflater inflater, ViewGroup parent) {
 * // TODO: Customize the item layout
 * super(inflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false));
 * text = (TextView) itemView.findViewById(R.id.text);
 * text.setOnClickListener(new View.OnClickListener() {
 * @Override public void onClick(View v) {
 * if (mListener != null) {
 * mListener.onItemClicked(getAdapterPosition());
 * dismiss();
 * }
 * }
 * });
 * }
 * <p>
 * }
 * <p>
 * private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
 * <p>
 * private final int mItemCount;
 * <p>
 * ItemAdapter(int itemCount) {
 * mItemCount = itemCount;
 * }
 * @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
 * return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
 * }
 * @Override public void onBindViewHolder(ViewHolder holder, int position) {
 * holder.text.setText(String.valueOf(position));
 * }
 * @Override public int getItemCount() {
 * return mItemCount;
 * }
 * <p>
 * }
 * <p>
 * }
 */