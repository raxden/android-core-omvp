package com.omvp.domain;

import android.net.Uri;

import com.omvp.commons.DontObfuscate;

import org.parceler.Parcel;
import org.threeten.bp.LocalDateTime;

import lombok.Data;

@Parcel
@Data
@DontObfuscate
public class SampleDomain {

    Long id;
    String title;
    Uri link;
    LocalDateTime pubdate;

    public SampleDomain() {

    }

}
