package com.checkaboy.deepcopy.cloner.interf;

/**
 * @author Taras Shaptala
 */
@FunctionalInterface
public interface ICloner<O> {

    O clone(O source);

}
