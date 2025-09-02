package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.adapter.interf.ICollectionAdapter;
import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public class CollectionAdapter<CS extends Collection<V>, CT extends Collection<V>, V>
        implements ICollectionAdapter<CS, CT, V> {

    private final IFieldCloner<V> fieldCloner;

    public CollectionAdapter(IFieldCloner<V> fieldCloner) {
        this.fieldCloner = fieldCloner;
    }

    @Override
    public void copy(CS source, CT target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            source.forEach(o -> target.add(fieldCloner.clone(o)));
        }
    }

    public static <CS extends Collection<V>, CT extends Collection<V>, V> ICollectionAdapter<CS, CT, V> primitiveCollectionCopyist() {
        return new CollectionAdapter<>(FieldCloner.simpleFieldCloner());
    }

}
