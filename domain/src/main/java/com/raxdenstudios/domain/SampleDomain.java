package com.raxdenstudios.domain;

import android.net.Uri;

import org.parceler.Parcel;

import lombok.Data;

@Parcel
@Data
public class SampleDomain {

    Long id;
    String title;
    String subtitle;
    String descripcion;
    Uri link;
    Uri thumbnail;
    Uri image;
    String section;
    int order;

    public SampleDomain() {
        super();
    }

}
