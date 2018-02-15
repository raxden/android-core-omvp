package com.raxdenstudios.data.network.filter;

import android.content.Context;

import com.raxdenstudios.commons.AppException;
import com.raxdenstudios.data.R;

import org.json.JSONArray;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

public class JSONArrayErrorFilter implements Predicate<JSONArray> {

    private final Context context;

    public JSONArrayErrorFilter(Context context) {
        this.context = context;
    }

    @Override
    public boolean test(@NonNull JSONArray jsonArray) throws Exception {
        if (jsonArray == null) {
            throw new AppException(context.getResources().getString(R.string.unespected_error_message));
        }
        return true;
    }

}
