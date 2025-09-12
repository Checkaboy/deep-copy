package com.checkaboy.deepcopy.transformer.model;

import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

/**
 * @author Taras Shaptala
 */
public class FieldTransformer<S, T>
        implements IFieldTransformer<S, T> {

    private final IFieldTransformer<S, T> transformer;

    public FieldTransformer(IFieldTransformer<S, T> transformer) {
        this.transformer = transformer;
    }

    @Override
    public T transform(S source) {
        if(source == null)
            return null;

        return transformer.transform(source);
    }

    public static <O> IFieldTransformer<O, O> simpleFieldTransformer() {
        return new FieldTransformer<>(source -> source);
    }

}
