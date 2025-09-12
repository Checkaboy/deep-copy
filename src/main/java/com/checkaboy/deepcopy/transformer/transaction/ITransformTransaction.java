package com.checkaboy.deepcopy.transformer.transaction;

/**
 * @author Taras Shaptala
 */
public interface ITransformTransaction<S, T> {

    T transform(S source);

}
