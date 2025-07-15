package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cloner.interf.ICloner;

/**
 * @author Taras Shaptala
 */
public class Cloner<O>
        implements ICloner<O> {

    private final ICloner<O> cloner;

    public Cloner(ICloner<O> cloner) {
        this.cloner = cloner;
    }

    @Override
    public O clone(O source) {
        return cloner.clone(source);
    }

    public static <O> ICloner<O> simpleCloner() {
        return new Cloner<>(source -> source);
    }

}
