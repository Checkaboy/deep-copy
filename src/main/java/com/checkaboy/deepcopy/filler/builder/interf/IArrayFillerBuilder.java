package com.checkaboy.deepcopy.filler.builder.interf;

import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface IArrayFillerBuilder<S, T>
        extends IFillerBuilder<S[], T[]> {

    IArrayFillerBuilder<S, T> setTransformer(IFieldTransformer<S, T> transformer);

    IArrayFillerBuilder<S, T> setPredicate(Predicate<S> predicate);

}
