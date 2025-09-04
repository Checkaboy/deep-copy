package com.checkaboy.deepcopy.cloner;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICloner<O> {

    O clone(O source);

}
