package com.checkaboy.deepcopy.transformer.model.interf;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ITransformer<S, T> {

    T transform(S source);

}
