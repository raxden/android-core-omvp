package com.omvp.app.helper;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.omvp.app.R;

public class SnackBarHelper {

    private Activity mActivity;

    public SnackBarHelper(Activity activity) {
        mActivity = activity;
    }

    public void showNetworkError() {
        View view = mActivity.findViewById(R.id.coordinator_layout_view);
        if (view instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            Snackbar.make(coordinatorLayout, mActivity.getResources().getString(R.string.network_unavailable), Snackbar.LENGTH_LONG).show();
        }
    }

}
