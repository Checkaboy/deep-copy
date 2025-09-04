package com.checkaboy.deepcopy.transformer;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ITransformer<S, T> {

    T transform(S source);

}
