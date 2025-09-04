package com.checkaboy.deepcopy.transformer;

import com.checkaboy.deepcopy.filler.CollectionFiller;
import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.interf.ICollectionTransformer;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class CollectionTransformer<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionTransformer<SC, SV, TC, TV> {

    private final Function<Integer, TC> constructor;
    private final ICollectionFiller<SC, SV, TC, TV> collectionMapper;

    public CollectionTransformer(Function<Integer, TC> constructor, ICollectionFiller<SC, SV, TC, TV> collectionMapper) {
        this.constructor = constructor;
        this.collectionMapper = collectionMapper;
    }

    @Override
    public TC transform(SC source) {
        if (source == null)
            return null;

        TC targetCollection = constructor.apply(source.size());
        collectionMapper.fill(source, targetCollection);

        return targetCollection;
    }

    public static <S extends Collection<V>, V, T extends Collection<V>> CollectionTransformer<S, V, T, V> primitiveCollectionTransformer(Function<Integer, T> constructor) {
        return new CollectionTransformer<>(constructor, CollectionFiller.primitiveCollectionFiller());
    }

}
