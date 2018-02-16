package com.omvp.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseComponentView extends FrameLayout {

    public BaseComponentView(Context context) {
        super(context);
        init(context, null);
    }

    public BaseComponentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseComponentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            loadView(context);
            loadAttributes(context, attrs);
            bindViews();
            loadData();
        }
    }

    protected void loadView(Context context) {
        View.inflate(context, getLayoutId(), this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            loadView(getContext());
        }
    }

    protected abstract void loadAttributes(Context context, AttributeSet attrs);

    protected abstract void bindViews();

    protected abstract void loadData();

    protected abstract int getLayoutId();

}
