package com.omvp.app.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class ImageHelper {

    public enum ScaleType {NONE, CENTER_CROP, FIT_CENTER, CIRCLE_CROP}

    public static void loadImageUser(Context context, Uri imageUser, ImageView imageView, View progressView) {
        RequestBuilder requestBuilder = Glide.with(context)
                .load(imageUser)
                .transition(DrawableTransitionOptions.withCrossFade());
        loadTransformations(context, requestBuilder, 0, 0, 0, ScaleType.CENTER_CROP, 0, null, 0);
        loadProgressView(requestBuilder, progressView);
        requestBuilder.into(imageView);
    }

    public static void loadImageUser(Context context, String imageUser, ImageView imageView, View progressView) {
        RequestBuilder requestBuilder = Glide.with(context)
                .load(imageUser)
                .transition(DrawableTransitionOptions.withCrossFade());
        loadTransformations(context, requestBuilder, 0, 0, 0, ScaleType.CIRCLE_CROP, 0, null, 0);
        loadProgressView(requestBuilder, progressView);
        requestBuilder.into(imageView);
    }

    public static void loadImageUser(Context context, Bitmap imageUser, ImageView imageView, View progressView) {
        RequestBuilder requestBuilder = Glide.with(context)
                .load(imageUser)
                .transition(DrawableTransitionOptions.withCrossFade());
        loadTransformations(context, requestBuilder, 0, 0, 0, ScaleType.CIRCLE_CROP, 0, null, 0);
        loadProgressView(requestBuilder, progressView);
        requestBuilder.into(imageView);
    }

    private static void loadTransformations(final Context context, RequestBuilder<Drawable> requestBuilder, int width, int height, int holderId, ScaleType scaleType, final int cornerRadius, final RoundedCornersTransformation.CornerType cornerType, final int blurRadius) {
        MultiTransformation multiTransformation = null;
        switch (scaleType) {
            case CENTER_CROP:
                if (cornerRadius > 0) {
                    multiTransformation = new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCornersTransformation(cornerType, cornerRadius));
                } else {
                    multiTransformation = new MultiTransformation<Bitmap>(new CenterCrop());
                }
                break;
            case FIT_CENTER:
                if (cornerRadius > 0) {
                    multiTransformation = new MultiTransformation<Bitmap>(new FitCenter(), new RoundedCornersTransformation(cornerType, cornerRadius));
                } else {
                    multiTransformation = new MultiTransformation<Bitmap>(new FitCenter());
                }
                break;
            case CIRCLE_CROP:
                if (cornerRadius > 0) {
                    multiTransformation = new MultiTransformation<Bitmap>(new CircleCrop(), new RoundedCornersTransformation(cornerType, cornerRadius));
                } else {
                    multiTransformation = new MultiTransformation<Bitmap>(new CircleCrop());
                }
                break;
            case NONE:
                if (cornerRadius > 0) {
                    multiTransformation = new MultiTransformation<Bitmap>(new RoundedCornersTransformation(cornerType, cornerRadius));
                }
        }
        RequestOptions requestOptions = new RequestOptions();
        if (width > 0 && height > 0) {
            requestOptions.override(width, height);
        }
        requestOptions.priority(Priority.IMMEDIATE);
        if (holderId != 0) {
            requestOptions.placeholder(holderId);
        }
        if (multiTransformation != null) {
            requestOptions.transform(multiTransformation);
        }
        requestBuilder.apply(requestOptions);
    }

    private static void loadProgressView(RequestBuilder<Drawable> requestBuilder, final View progressView) {
        requestBuilder.listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (progressView != null) {
                    progressView.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (progressView != null) {
                    progressView.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

}
