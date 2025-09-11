package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.filler.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.interf.IObjectTransformer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectTransformer<S, T>
        extends AbstractObjectTransformer<S, T>
        implements IObjectTransformer<S, T> {

    private final Supplier<T> constructor;
    private final IObjectFiller<S, T> objectFiller;

    public ObjectTransformer(Supplier<T> constructor, IObjectFiller<S, T> objectFiller) {
        this.constructor = constructor;
        this.objectFiller = objectFiller;
    }

    @Override
    protected T create() {
        return constructor.get();
    }

    @Override
    protected void fill(S source, T target) {
        objectFiller.fill(source, target);
    }

    public static <S, T> IObjectTransformer<S, T> simpleObjectTransformerWithIdentityCache(Supplier<T> constructor, IObjectFiller<S, T> objectAdapter) {
        return new ObjectTransformer<>(constructor, objectAdapter);
    }

}
