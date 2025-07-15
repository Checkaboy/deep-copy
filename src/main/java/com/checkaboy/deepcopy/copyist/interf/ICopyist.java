package com.checkaboy.deepcopy.copyist.interf;

/**
 * @author Taras Shaptala
 */
public interface ICopyist<O> {

    void copy(O source, O target);

}
