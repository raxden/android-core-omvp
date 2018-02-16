package com.omvp.data.network.filter;

import android.content.Context;

import com.omvp.commons.AppException;
import com.omvp.data.R;

import org.json.JSONObject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

public class JSONObjectErrorFilter implements Predicate<JSONObject> {

    private final Context context;

    public JSONObjectErrorFilter(Context context) {
        this.context = context;
    }

    @Override
    public boolean test(@NonNull JSONObject jsonObject) throws Exception {
        if (jsonObject == null) {
            throw new AppException(context.getResources().getString(R.string.unespected_error_message));
        }
        return true;
    }

}
