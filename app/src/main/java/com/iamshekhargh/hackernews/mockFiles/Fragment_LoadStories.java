package com.iamshekhargh.hackernews.mockFiles;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iamshekhargh.hackernews.R;
import com.iamshekhargh.hackernews.customElements.CustomProgressDialog;
import com.iamshekhargh.hackernews.datastore.interfaces.GetStories;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_LoadStories extends Fragment implements View.OnClickListener {


    GetStories getStories;

    @BindView(R.id.fetchInfo)
    Button fetchInfo;
    @BindView(R.id.show_Result_here)
    TextView showResultHere;
    Unbinder unbinder;
    CustomProgressDialog customProgressDialog;


    private OnFragmentInteractionListener mListener;

    public Fragment_LoadStories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load_stories, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        fetchInfo.setOnClickListener(this);
        customProgressDialog = new CustomProgressDialog(getContext());
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fetchInfo) {
            showProgressBar();
            mListener.onButtonClicked();
        }
    }

    public void UpdateText(String text) {
        hideProgressBar();
        showResultHere.setText(text);
    }

    public void showProgressBar() {
        if (customProgressDialog != null) {
            customProgressDialog.show();
        }
    }

    public void hideProgressBar() {
        if (customProgressDialog != null) {
            customProgressDialog.dismiss();
        }
    }


    public interface OnFragmentInteractionListener {
        void onButtonClicked();
    }
}
