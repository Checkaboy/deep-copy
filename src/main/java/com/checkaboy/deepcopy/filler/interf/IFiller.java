package com.checkaboy.deepcopy.filler.interf;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IFiller<S, T> {

    void fill(S source, T target);

}
