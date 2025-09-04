package com.checkaboy.deepcopy;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICopyist<S, T> {

    void copy(S source, T target);

}
