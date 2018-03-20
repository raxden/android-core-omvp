package com.omvp.app.helper;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;

import timber.log.Timber;

public class DialogHelper {

    private final Resources mResources;
    private final FragmentManager mFragmentManager;
    private final Bundle mExtras;

    public DialogHelper(Activity activity, FragmentManager fragmentManager) {
        mResources = activity.getResources();
        mFragmentManager = fragmentManager;
        mExtras = activity.getIntent().getExtras();
    }

    public DialogHelper(Fragment fragment, FragmentManager fragmentManager) {
        mResources = fragment.getResources();
        mFragmentManager = fragmentManager;
        mExtras = fragment.getArguments();
    }

//    public NoticeDialogFragment showLocationPermission() {
//        final NoticeDialogFragment dialog = NoticeDialogFragment.newInstance(mExtras);
//        dialog.setTitle(mResources.getString(R.string.permission_location_title));
//        dialog.setDescription(mResources.getString(R.string.permission_location_description));
//        dialog.setAcceptButton(mResources.getString(R.string.understood_with_mark), new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        showDialog(dialog, "showLocationPermission");
//        return dialog;
//    }
//
//    public NoticeDialogFragment showMessage(String title, String message) {
//        // TODO: 17/06/2017
//        return null;
//    }
//
//    public NoticeDialogFragment showError(String title, String message) {
//        return showError(title, message, null);
//    }
//
//    public NoticeDialogFragment showError(String title, String message, final View.OnClickListener onAcceptClickListener) {
//        final NoticeDialogFragment dialog = NoticeDialogFragment.newInstance(mExtras);
//        dialog.setTitle(title);
//        dialog.setDescription(message);
//        dialog.setAcceptButton(mResources.getString(android.R.string.ok), new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onAcceptClickListener != null) {
//                    onAcceptClickListener.onClick(v);
//                }
//                dialog.dismiss();
//            }
//        });
//        showDialog(dialog, "showError");
//        return dialog;
//    }

    private void showDialog(final DialogFragment dialog, final String tag) {
        try {
            dialog.show(mFragmentManager, tag);
        } catch (IllegalStateException e) {
            Timber.e(e.getMessage(), e);
        }
    }

}
