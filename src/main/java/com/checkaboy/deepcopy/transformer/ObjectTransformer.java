package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.cloner.CollectionCloner;
import com.checkaboy.deepcopy.cloner.interf.ICollectionCloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.filler.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.interf.IObjectTransformer;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectTransformer<S, T>
        implements IObjectTransformer<S, T> {

    private final Supplier<T> constructor;
    private final IObjectFiller<S, T> objectAdapter;

    public ObjectTransformer(Supplier<T> constructor, IObjectFiller<S, T> objectAdapter) {
        this.constructor = constructor;
        this.objectAdapter = objectAdapter;
    }

    @Override
    public T transform(S source) {
        if(source == null)
            return null;

        T target = constructor.get();
        objectAdapter.fill(source, target);
        return target;
    }

}
