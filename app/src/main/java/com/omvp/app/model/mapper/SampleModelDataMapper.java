package com.omvp.data.entity.mapper;

import android.content.Context;

import com.omvp.commons.DataMapper;
import com.omvp.data.entity.SampleEntity;
import com.omvp.domain.SampleDomain;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SampleEntityDataMapper extends DataMapper<SampleEntity, SampleDomain> {

    @Inject
    SampleEntityDataMapper(Context context, ModelMapper modelMapper) {
        super(context, modelMapper);
    }

    @Override
    public SampleDomain transform(SampleEntity source) {
        return getModelMapper().map(source, SampleDomain.class);
    }

    @Override
    public SampleEntity inverseTransform(SampleDomain source) {
        return getModelMapper().map(source, SampleEntity.class);
    }

    @Override
    public boolean equals(SampleEntity source, SampleDomain destination) {
        return false;
    }

}
