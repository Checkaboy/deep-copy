package com.checkaboy.deepcopy.transformer.builder.interf;

import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.builder.ObjectTransformerBuilder;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public interface IObjectTransformerBuilder<S, T>
        extends ITransformerBuilder<S, T> {

    ObjectTransformerBuilder<S, T> setConstructor(Supplier<T> constructor);

    ObjectTransformerBuilder<S, T> setObjectFiller(IObjectFiller<S, T> objectFiller);

}
