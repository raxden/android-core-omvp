package com.omvp.app.model;

import org.parceler.Parcel;

import lombok.Data;

@Data
@Parcel
public class SampleModel {

    String title;
    String link;
    String pubdate;

    public SampleModel() {

    }

}
