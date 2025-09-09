package com.checkaboy.deepcopy.filler.based;

import com.checkaboy.deepcopy.filler.AbstractCollectionFiller;
import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;
import com.checkaboy.deepcopy.filler.interf.IFieldFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

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
        this(source -> transformer.get());
    }

    public CollectionFiller(IFieldTransformer<SV, TV> transformer) {
        this.transformer = transformer;
    }

    @Override
    protected void fillValue(TC target, SV sourceValue) {
        target.add(transformer.transform(sourceValue));
    }

    public static <SC extends Collection<V>, TC extends Collection<V>, V> ICollectionFiller<SC, V, TC, V> primitiveCollectionFiller() {
        return new CollectionFiller<>(source -> source);
    }

}
