package com.omvp.app.interceptor.takePicture;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.omvp.app.base.reactivex.BaseDisposableSingleObserver;
import com.omvp.app.util.ImagePickerUtil;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Remember to add to the AndroidManifest.xml the FileProvider info into Application tag
 *   <application ...>
 *
 *       <provider
 *           android:name="android.support.v4.content.FileProvider"
 *           android:authorities="${applicationId}.fileprovider"
 *           android:exported="false"
 *           android:grantUriPermissions="true">
 *           <meta-data
 *               android:name="android.support.FILE_PROVIDER_PATHS"
 *               android:resource="@xml/provider_paths" />
 *       </provider>
 *
 *   </application>
 *
 *  And remember to add the provider_paths.xml into a xml file and stored into res/xml
 *
 *       <?xml version="1.0" encoding="utf-8"?>
 *       <paths xmlns:android="http://schemas.android.com/apk/res/android">
 *              <external-path name="external_files" path="." />
 *       </paths>
 *
*/

public class TakePictureActivityInterceptor extends ActivityInterceptor<TakePictureInterceptorCallback> implements TakePictureInterceptor {

    private static final int REQUEST_IMAGE_CAPTURE = 10023;

    private CompositeDisposable mCompositeDisposable;

    public TakePictureActivityInterceptor(Activity activity, TakePictureInterceptorCallback callback) {
        super(activity, callback);
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

    public void takePicture(final String chooserTitle) {
        Disposable d = processTakePictureIntent(chooserTitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseDisposableSingleObserver<Intent>(mActivity) {
                    @Override
                    protected void onError(int code, String title, String description) {
                        workingPictureProgress(false);
                    }

                    @Override
                    protected void onStart() {
                        workingPictureProgress(true);
                    }

                    @Override
                    public void onSuccess(@NonNull Intent intent) {
                        launchActivityForResult(intent);
                    }
                });
        mCompositeDisposable.add(d);
    }

    private void retrieveImageFromResult(final int requestCode, final int resultCode, final Intent data) {
        Disposable d = processImageFromResult(requestCode, resultCode, data)
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
                });
        mCompositeDisposable.add(d);
    }

    private void launchActivityForResult(Intent intent) {
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            ((Activity) mContext).startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void pictureRetrieved(Uri uri) {
        if (mCallback != null) {
            mCallback.onPictureRetrieved(uri);
        }
    }

    private void workingPictureProgress(boolean workingProgress) {
        if (mCallback != null) {
            mCallback.onWorkingPictureProgress(workingProgress);
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
