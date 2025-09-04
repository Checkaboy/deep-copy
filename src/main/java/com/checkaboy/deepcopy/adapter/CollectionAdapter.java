package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.ICloner;
import com.checkaboy.deepcopy.adapter.interf.ICollectionAdapter;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class CollectionAdapter<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        implements ICollectionAdapter<SC, SV, TC, TV> {

    private final ICloner<SV, TV> cloner;
    private final IFieldAdapter<SV, TV> adapter;

    public CollectionAdapter(Supplier<TV> cloner, IFieldAdapter<SV, TV> adapter) {
        this(source -> cloner.get(), adapter);
    }

    public CollectionAdapter(ICloner<SV, TV> cloner, IFieldAdapter<SV, TV> adapter) {
        this.cloner = cloner;
        this.adapter = adapter;
    }

    @Override
    public void copy(SC source, TC target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            source.forEach(sv -> {
                TV tv = cloner.clone(sv);
                adapter.copy(sv, tv);
                target.add(tv);
            });
        }
    }

    public static <SC extends Collection<V>, TC extends Collection<V>, V> ICollectionAdapter<SC, V, TC, V> simpleCollectionAdapter() {
        return new CollectionAdapter<>(source -> source, (source, target) -> {});
    }

}
