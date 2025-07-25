package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cloner.interf.ICloner;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectCloner<O>
        implements ICloner<O> {

    private final Supplier<O> constructor;
    private final IObjectCopyist<O> objectCopyist;

    public ObjectCloner(Supplier<O> constructor, IObjectCopyist<O> objectCopyist) {
        this.constructor = constructor;
        this.objectCopyist = objectCopyist;
    }

    @Override
    public O clone(O source) {
        O newTarget = constructor.get();
        objectCopyist.copy(source, newTarget);
        return newTarget;
    }

}
