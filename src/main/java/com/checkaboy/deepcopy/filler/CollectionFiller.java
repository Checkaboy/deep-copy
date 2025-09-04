package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;
import com.checkaboy.deepcopy.filler.interf.IFieldFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class CollectionFiller<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionFiller<SC, SV, TC, TV> {

    private final IFieldTransformer<SV, TV> cloner;
    private final IFieldFiller<SV, TV> adapter;

    public CollectionFiller(Supplier<TV> cloner, IFieldFiller<SV, TV> adapter) {
        this(source -> cloner.get(), adapter);
    }

    public CollectionFiller(IFieldTransformer<SV, TV> cloner, IFieldFiller<SV, TV> adapter) {
        this.cloner = cloner;
        this.adapter = adapter;
    }

    @Override
    public void fill(SC source, TC target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            source.forEach(sv -> {
                TV tv = cloner.transform(sv);
                adapter.fill(sv, tv);
                target.add(tv);
            });
        }
    }

    public static <SC extends Collection<V>, TC extends Collection<V>, V> ICollectionFiller<SC, V, TC, V> primitiveCollectionFiller() {
        return new CollectionFiller<>(source -> source, (source, target) -> {});
    }

}
