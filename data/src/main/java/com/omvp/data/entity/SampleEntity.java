package com.omvp.data.entity;

import com.omvp.commons.DontObfuscate;

import lombok.Data;

@Data
@DontObfuscate
public class SampleEntity {

    private String id;
    private String title;
    private String link;
    private long pubdate;

}
