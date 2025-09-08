package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class CollectionCopyist<C extends Collection<V>, V>
        implements ICollectionCopyist<C, V> {

    private final IFieldCloner<V> fieldCloner;
    private final Predicate<V> predicate;

    public CollectionCopyist(IFieldCloner<V> fieldCloner) {
        this(fieldCloner, null);
    }

    public CollectionCopyist(IFieldCloner<V> fieldCloner, Predicate<V> predicate) {
        this.fieldCloner = fieldCloner;
        this.predicate = predicate;
    }

    @Override
    public void copy(C source, C target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            if (predicate != null) source.forEach(v -> {
                if (predicate.test(v)) target.add(fieldCloner.clone(v));
            });
            else source.forEach(v -> target.add(fieldCloner.clone(v)));
        }
    }

    public static <C extends Collection<V>, V> ICollectionCopyist<C, V> primitiveCollectionCopyist() {
        return new CollectionCopyist<>(FieldCloner.simpleFieldCloner());
    }

}
