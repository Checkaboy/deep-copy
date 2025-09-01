package com.checkaboy.deepcopy;

/**
 * @author Taras Shaptala
 */
public interface ICopyist<O> {

    void copy(O source, O target);

}
