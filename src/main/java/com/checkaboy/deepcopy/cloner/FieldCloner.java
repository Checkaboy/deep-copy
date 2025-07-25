package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cloner.interf.ICloner;

/**
 * @author Taras Shaptala
 */
public class FieldCloner<O>
        implements ICloner<O> {

    private final ICloner<O> cloner;

    public FieldCloner(ICloner<O> cloner) {
        this.cloner = cloner;
    }

    @Override
    public O clone(O source) {
        return cloner.clone(source);
    }

    public static <O> ICloner<O> simpleCloner() {
        return new FieldCloner<>(source -> source);
    }

}
