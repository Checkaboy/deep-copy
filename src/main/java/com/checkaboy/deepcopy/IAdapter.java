package com.checkaboy.deepcopy;

/**
 * @author Taras Shaptala
 */
public interface IAdapter<S, T> {

    void copy(S source, T target);

}
