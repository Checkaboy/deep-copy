package com.checkaboy.deepcopy.cloner;

import com.checkaboy.deepcopy.cache.ICacheContext;
import com.checkaboy.deepcopy.cloner.interf.IObjectCloner;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectCloner<O, K>
        implements IObjectCloner<O> {

    private final Supplier<O> constructor;
    private final IObjectCopyist<O> objectCopyist;
    private final ICacheContext<O, O, K> cacheContext;

    public ObjectCloner(Supplier<O> constructor, IObjectCopyist<O> objectCopyist) {
        this(constructor, objectCopyist, null);
    }

    public ObjectCloner(Supplier<O> constructor, IObjectCopyist<O> objectCopyist, ICacheContext<O, O, K> cacheContext) {
        this.constructor = constructor;
        this.objectCopyist = objectCopyist;
        this.cacheContext = cacheContext;
    }

    @Override
    public O clone(O source) {
        if (source == null)
            return null;

        O target;
        if (cacheContext == null) {
            target = constructor.get();
            objectCopyist.copy(source, target);
            return target;
        }

        target = cacheContext.putIfAbsent(source, constructor);
        objectCopyist.copy(source, target);
        return target;
    }

}
