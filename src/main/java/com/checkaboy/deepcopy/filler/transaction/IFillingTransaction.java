package com.checkaboy.deepcopy.filler.transaction;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface IFillingTransaction<S, T> {

    void fill(S source, T target);

}
