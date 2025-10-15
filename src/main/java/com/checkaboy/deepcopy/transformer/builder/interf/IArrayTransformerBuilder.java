package com.checkaboy.deepcopy.transformer.builder.interf;

import com.checkaboy.deepcopy.filler.model.interf.IArrayFiller;

import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IArrayTransformerBuilder<S, T>
        extends ITransformerBuilder<S[], T[]> {

    IArrayTransformerBuilder<S, T> setConstructor(Function<Integer, T[]> constructor);

    IArrayTransformerBuilder<S, T> setArrayFiller(IArrayFiller<S, T> arrayFiller);

}
