package com.omvp.domain;

import com.omvp.commons.DontObfuscate;

import org.parceler.Parcel;

import lombok.Data;

/**
 * Created by Angel on 15/02/2018.
 */
@Parcel
@Data
@DontObfuscate
public class Credentials {

    String accessToken;

    String refreshToken;

    public Credentials() {
    }

}
