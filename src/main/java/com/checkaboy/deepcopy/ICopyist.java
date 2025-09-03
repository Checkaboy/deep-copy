package com.checkaboy.deepcopy;

/**
 * @author Taras Shaptala
 */
public interface ICopyist<S, T> {

    void copy(S source, T target);

}
