package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

/**
 * @author Taras Shaptala
 */
public class FieldCloner<O>
        implements IFieldCloner<O> {

    private final IFieldCloner<O> fieldCloner;

    public FieldCloner(IFieldCloner<O> fieldCloner) {
        this.fieldCloner = fieldCloner;
    }

    @Override
    public O clone(O source) {
        if(source == null)
            return null;

        return fieldCloner.clone(source);
    }

    public static <O> IFieldCloner<O> simpleFieldCloner() {
        return new FieldCloner<>(source -> source);
    }

}
