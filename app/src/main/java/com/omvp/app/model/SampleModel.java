package com.omvp.app.model;

import com.omvp.commons.DontObfuscate;

import org.parceler.Parcel;

import lombok.Data;

@Data
@Parcel
@DontObfuscate
public class SampleModel {

    String title;
    String link;
    String pubdate;

    public SampleModel() {

    }

}
