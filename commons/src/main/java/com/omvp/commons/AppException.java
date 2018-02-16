package com.omvp.commons;

/**
 * Created by Angel on 15/02/2018.
 */

public class AppException extends RuntimeException {

    private int code;

    public AppException(String detailMessage) {
        super(detailMessage);
    }

    public AppException(int code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
