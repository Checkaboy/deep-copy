package com.checkaboy.deepcopy.filler.model.general;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.abstr.AbstractCollectionFiller;
import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class CollectionFiller<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends AbstractCollectionFiller<SC, SV, TC, TV>
        implements ICollectionFiller<SC, SV, TC, TV> {

    private final IFieldTransformer<SV, TV> transformer;

    public CollectionFiller(Supplier<TV> transformer) {
        this((cacheContext, source) -> transformer.get());
    }

    public CollectionFiller(IFieldTransformer<SV, TV> transformer) {
        this.transformer = transformer;
    }

    @Override
    protected void fillValue(ICopyistCache cache, TC target, SV sourceValue) {
        target.add(transformer.transform(cache, sourceValue));
    }

    public static <SC extends Collection<V>, TC extends Collection<V>, V> ICollectionFiller<SC, V, TC, V> primitiveCollectionFiller() {
        return new CollectionFiller<>((cacheContext, source) -> source);
    }

}
