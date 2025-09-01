package com.checkaboy.deepcopy;

/**
 * @author Taras Shaptala
 */
public interface ICloner<O> {

    O clone(O source);

}
