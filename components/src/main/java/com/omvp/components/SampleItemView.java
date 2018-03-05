package com.omvp.components;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class SampleItemView extends BaseComponentView {

    private AppCompatTextView mSampleTextView;
    private AppCompatImageView mSampleImageView;

    public SampleItemView(Context context) {
        super(context);
    }

    public SampleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SampleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void loadAttributes(Context context, AttributeSet attrs) {

    }

    @Override
    protected void bindViews() {
        mSampleTextView = findViewById(R.id.text);
        mSampleImageView = findViewById(R.id.image);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.sample_item_layout;
    }

    public void setSampleText(String text) {
        mSampleTextView.setText(text);
    }

    public void setSampleImage(String imageUrl){

    }
}
