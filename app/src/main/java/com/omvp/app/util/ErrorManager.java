package com.omvp.app.util;

import android.content.res.Resources;
import android.text.TextUtils;

import com.omvp.app.R;
import com.omvp.commons.AppException;
import com.omvp.data.network.gateway.retrofit.exception.RetrofitException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.exceptions.CompositeException;
import lombok.Data;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Angel on 23/11/2017.
 */
@Data
public class ErrorManager {

    private final Resources resources;

    private int code;
    private String title;
    private String message;

    public ErrorManager(Resources resources) {
        this.resources = resources;
        code = 0;
        title = resources.getString(R.string.unespected_error_title);
        message = resources.getString(R.string.unespected_error_message);
    }

    public void parseError(Throwable throwable) {
        if (throwable instanceof CompositeException) {
            CompositeException compositeException = (CompositeException) throwable;
            if (compositeException.getExceptions() != null && !compositeException.getExceptions().isEmpty()) {
                for (Throwable childThrowable : compositeException.getExceptions()) {
                    parseError(childThrowable);
                }
            }
        } else if (throwable instanceof RetrofitException) {
            parseError((RetrofitException) throwable);
        } else if (throwable instanceof AppException) {
            parseError((AppException) throwable);
        }
    }

    public void parseError(RetrofitException exception) {
        final String url = exception.getUrl();
        final Response response = exception.getResponse();
        final Throwable cause = exception.getCause();
        code = response != null ? response.code() : 0;
        title = resources.getString(R.string.unespected_error_title);
        message = resources.getString(R.string.unespected_error_message);
        if (cause instanceof ConnectException) {
            message = resources.getString(R.string.unespected_timeout_message);
        } else if (cause instanceof SocketTimeoutException) {
            message = resources.getString(R.string.unespected_timeout_message);
        } else if (cause instanceof UnknownHostException) {
            message = resources.getString(R.string.unespected_timeout_message);
        } else {
            try {
                String errorMessage = exception.getErrorBodyAs(String.class);
                if (!TextUtils.isEmpty(errorMessage)) {
                    JSONObject jsonError = new JSONObject(errorMessage);
                    try {
                        if (jsonError.has("code")) {
                            code = jsonError.getInt("code");
                            if (jsonError.has("message") && !TextUtils.isEmpty(jsonError.getString("message"))) {
                                message = jsonError.getString("message");
                            } else if (jsonError.has("raw") && !TextUtils.isEmpty(jsonError.getString("raw"))) {
                                message = jsonError.getString("raw");
                            }
                        }
                    } catch (JSONException e) {
                        Timber.e(e);
                    }
                }
            } catch (JSONException e) {
                Timber.e(e);
            } catch (IOException e) {
                Timber.e(e);
            }
        }
        Timber.e(url + " -  " + code + " " + title + " " + message);
    }

    public void parseError(AppException exception) {
        code = exception.getCode();
        title = resources.getString(R.string.unespected_error_title);
        message = exception.getMessage();
    }

}
