package com.omvp.commons;

import android.content.Context;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public abstract class DataMapper<E, T> {

    private Context context;
    private ModelMapper modelMapper;

    public DataMapper(Context context, ModelMapper modelMapper) {
        this.context = context;
        this.modelMapper = modelMapper;
    }

    /**
     * Transform a {@link E} into an {@link T}.
     *
     * @param source Object to be transformed.
     * @return {@link T} if valid {@link E} otherwise null.
     */
    public abstract T transform(E source);

    /**
     * Transform a List of {@link E} into a List of {@link T}.
     *
     * @param source Object List to be transformed.
     * @return {@link T} if valid {@link E} otherwise null.
     */
    public List<T> transform(List<E> source) {
        List<T> destination = new ArrayList<>();
        T out;
        for (E in : source) {
            out = transform(in);
            if (out != null) {
                destination.add(out);
            }
        }
        return destination;
    }

    /**
     * Transform a {@link T} into an {@link E}.
     *
     * @param source Object to be transformed.
     * @return {@link E} if valid {@link T} otherwise null.
     */
    public abstract E inverseTransform(T source);

    /**
     * Transform a List of {@link T} into a List of {@link E}.
     *
     * @param source Object List to be transformed.
     * @return {@link E} if valid {@link T} otherwise null.
     */
    public List<E> inverseTransform(List<T> source) {
        List<E> destination = new ArrayList<>();
        E out;
        for (T in : source) {
            out = inverseTransform(in);
            if (out != null) {
                destination.add(out);
            }
        }
        return destination;
    }

    public abstract boolean equals(E source, T destination);

    public Context getContext() {
        return context;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

}
