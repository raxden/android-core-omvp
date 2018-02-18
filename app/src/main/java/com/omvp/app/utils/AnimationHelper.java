package com.omvp.app.utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimationHelper {

    private Activity mActivity;

    public AnimationHelper(Activity activity) {
        mActivity = activity;
    }

    public void fadeIn(View view) {
        if (view != null && view.getVisibility() != View.VISIBLE) {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(mActivity, android.R.anim.fade_in);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(fadeInAnimation);
        }
    }

    public void fadeIn(View view, int visibility) {
        if (view != null && view.getVisibility() != visibility) {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(mActivity, android.R.anim.fade_in);
            view.setVisibility(visibility);
            view.startAnimation(fadeInAnimation);
        }
    }

    public void fadeOut(View view) {
        if (view != null && view.getVisibility() != View.GONE) {
            Animation fadeOutAnimation = AnimationUtils.loadAnimation(mActivity, android.R.anim.fade_out);
            view.setVisibility(View.GONE);
            view.startAnimation(fadeOutAnimation);
        }
    }

    public void fadeOut(View view, int visibility) {
        if (view != null && view.getVisibility() != visibility) {
            Animation fadeOutAnimation = AnimationUtils.loadAnimation(mActivity, android.R.anim.fade_out);
            view.setVisibility(visibility);
            view.startAnimation(fadeOutAnimation);
        }
    }

    public void clearAnimation(View view) {
        if (view != null) {
            view.clearAnimation();
        }
    }

}
