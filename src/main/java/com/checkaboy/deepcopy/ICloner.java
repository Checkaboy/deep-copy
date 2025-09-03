package com.checkaboy.deepcopy;

/**
 * @author Taras Shaptala
 */
public interface ICloner<S, T> {

    T clone(S source);

}
