package com.omvp.app.interceptor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptor;
import com.raxdenstudios.square.interceptor.commons.toolbar.ToolbarInterceptorCallback;


public class ToolbarActivityInterceptor extends ActivityInterceptor<ToolbarInterceptorCallback> implements ToolbarInterceptor {

    public ToolbarActivityInterceptor(Activity activity) {
        super(activity);
    }

    public ToolbarActivityInterceptor(Activity activity, ToolbarInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = mCallback.onCreateToolbarView(savedInstanceState);
        if (toolbar != null) {
            if (mActivity instanceof AppCompatActivity) {
                AppCompatActivity compatActivity = ((AppCompatActivity) mActivity);
                compatActivity.setSupportActionBar(toolbar);
                ActionBar actionBar = compatActivity.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }
            }
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return mActivity.onOptionsItemSelected(item);
                }
            });
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onBackPressed();
                }
            });
            mCallback.onToolbarViewCreated(toolbar);
        }
    }
}
