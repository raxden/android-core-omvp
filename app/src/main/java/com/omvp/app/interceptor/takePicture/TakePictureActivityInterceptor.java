package com.omvp.app.interceptor.takePicture;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;

import com.omvp.app.R;
import com.omvp.app.base.reactivex.BaseDisposableSingleObserver;
import com.omvp.app.util.ImagePickerUtil;
import com.raxdenstudios.square.interceptor.ActivitySimpleInterceptor;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Remember to add to the AndroidManifest.xml the FileProvider info into Application tag
 * <application ...>
 * <p>
 * <provider
 * android:name="android.support.v4.content.FileProvider"
 * android:authorities="${applicationId}.fileprovider"
 * android:exported="false"
 * android:grantUriPermissions="true">
 * <meta-data
 * android:name="android.support.FILE_PROVIDER_PATHS"
 * android:resource="@xml/provider_paths" />
 * </provider>
 * <p>
 * </application>
 * <p>
 * And remember to add the provider_paths.xml into a xml file and stored into res/xml
 * <p>
 * <?xml version="1.0" encoding="utf-8"?>
 * <paths xmlns:android="http://schemas.android.com/apk/res/android">
 * <external-path name="external_files" path="." />
 * </paths>
 */

public class TakePictureActivityInterceptor extends ActivitySimpleInterceptor implements TakePictureInterceptor {

    private static final int REQUEST_IMAGE_CAPTURE = 10023;

    private RxPermissions mRxPermissions;
    private TakePictureListener mListener;
    private CompositeDisposable mCompositeDisposable;

    public TakePictureActivityInterceptor(Activity activity) {
        super(activity);
        mRxPermissions = new RxPermissions(activity);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            retrieveImageFromResult(requestCode, resultCode, data);
        } else {
            workingPictureProgress(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void takePicture(final String chooserTitle, final TakePictureListener listener) {
        mCompositeDisposable.add(mRxPermissions.requestEach(Manifest.permission.CAMERA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Permission>() {
                    @Override
                    protected void onStart() {
                        mListener = listener;
                        workingPictureProgress(true);
                    }

                    @Override
                    public void onNext(Permission permission) {
                        if (permission.granted) {
                            mCompositeDisposable.add(processTakePictureIntent(chooserTitle)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeWith(new DisposableSingleObserver<Intent>() {
                                        @Override
                                        public void onSuccess(Intent intent) {
                                            workingPictureProgress(false);
                                            launchActivityForResult(intent);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            workingPictureProgress(false);
                                            Timber.e(e);
                                        }
                                    }));
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            new AlertDialog.Builder(mActivity)
                                    .setTitle(R.string.camera_permission_title)
                                    .setMessage(R.string.camera_permission_description)
                                    .setPositiveButton(R.string.camera_permission_positive_button, null)
                                    .create()
                                    .show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        workingPictureProgress(false);
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {}

                }));
    }

    private void retrieveImageFromResult(final int requestCode, final int resultCode, final Intent data) {
        mCompositeDisposable.add(processImageFromResult(requestCode, resultCode, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseDisposableSingleObserver<Uri>(mActivity) {
                    @Override
                    protected void onError(int code, String title, String description) {
                        workingPictureProgress(false);
                    }

                    @Override
                    public void onSuccess(@NonNull Uri uri) {
                        workingPictureProgress(false);
                        pictureRetrieved(uri);
                    }
                }));
    }

    private void launchActivityForResult(Intent intent) {
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            ((Activity) mContext).startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void pictureRetrieved(Uri uri) {
        if (mListener != null) {
            mListener.onPictureRetrieved(uri);
        }
    }

    private void workingPictureProgress(boolean workingProgress) {
        if (mListener != null) {
            mListener.onWorkingPictureProgress(workingProgress);
        }
    }

    private Single<Uri> processImageFromResult(final int requestCode, final int resultCode, final Intent data) {
        return Single.create(new SingleOnSubscribe<Uri>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Uri> emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        Uri uri = ImagePickerUtil.getUriFromResult(mContext, resultCode, data);
                        emitter.onSuccess(uri);
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    private Single<Intent> processTakePictureIntent(final String chooserTitle) {
        return Single.create(new SingleOnSubscribe<Intent>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Intent> emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        Intent intent = ImagePickerUtil.getPickImageIntent(mContext, chooserTitle);
                        emitter.onSuccess(intent);
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }


}
