package com.checkaboy.deepcopy.transformer.model;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.general.CollectionFiller;
import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.model.interf.ICollectionTransformer;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class CollectionTransformer<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionTransformer<SC, SV, TC, TV> {

    private final Function<Integer, TC> constructor;
    private final ICollectionFiller<SC, SV, TC, TV> collectionFiller;

    public CollectionTransformer(Function<Integer, TC> constructor, ICollectionFiller<SC, SV, TC, TV> collectionFiller) {
        this.constructor = constructor;
        this.collectionFiller = collectionFiller;
    }

    @Override
    public TC transform(ICopyistCache cache, SC source) {
        if (source == null)
            return null;

        TC targetCollection = constructor.apply(source.size());
        collectionFiller.fill(cache, source, targetCollection);

        return targetCollection;
    }

    public static <S extends Collection<V>, V, T extends Collection<V>> CollectionTransformer<S, V, T, V> primitiveCollectionTransformer(Function<Integer, T> constructor) {
        return new CollectionTransformer<>(constructor, CollectionFiller.primitiveCollectionFiller());
    }

}
