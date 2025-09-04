package com.checkaboy.deepcopy;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICloner<S, T> {

    T clone(S source);

}
