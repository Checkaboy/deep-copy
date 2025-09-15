package com.checkaboy.deepcopy.transformer.transaction;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ITransformationTransaction<S, T> {

    T transform(S source);

}
