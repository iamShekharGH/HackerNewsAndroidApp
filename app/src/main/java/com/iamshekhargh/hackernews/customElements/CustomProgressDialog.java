package com.iamshekhargh.hackernews.customElements;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.iamshekhargh.hackernews.R;

/**
 * Created by <<-- iamShekharGH -->>
 * on 11 May 2017
 * at 2:40 PM.
 */

public class CustomProgressDialog extends ProgressDialog {
    //    @BindView(R.id.loadingIcon)
    ImageView loadingIcon;
    //    @BindView(R.id.progressBar_text)
    CustomTextView progressBarText;

    public CustomProgressDialog(Context context) {
        super(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);
        loadingIcon = (ImageView) findViewById(R.id.loadingIcon);
        progressBarText = (CustomTextView) findViewById(R.id.progressBar_text);
//        ButterKnife.bind(this);
    }

    public void setTitleText(String title) {
        if (progressBarText != null)
            progressBarText.setText(title);
    }

    public void setImage(int resource) {

        if (loadingIcon != null)
            loadingIcon.setBackgroundResource(resource);
    }
}
