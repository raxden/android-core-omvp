package com.omvp.app.model.mapper;

import android.content.Context;

import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.model.SampleModel;
import com.omvp.commons.DataMapper;
import com.omvp.domain.SampleDomain;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

@PerFragment
public class SampleModelDataMapper extends DataMapper<SampleDomain, SampleModel> {

    @Inject
    SampleModelDataMapper(Context context, ModelMapper modelMapper) {
        super(context, modelMapper);
    }

    @Override
    public SampleModel transform(SampleDomain source) {
        return getModelMapper().map(source, SampleModel.class);
    }

    @Override
    public SampleDomain inverseTransform(SampleModel source) {
        return getModelMapper().map(source, SampleDomain.class);
    }

    @Override
    public boolean equals(SampleDomain source, SampleModel destination) {
        return false;
    }

}

