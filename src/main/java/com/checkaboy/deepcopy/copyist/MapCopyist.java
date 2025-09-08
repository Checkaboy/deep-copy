package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class MapCopyist<M extends Map<K, V>, K, V>
        implements IMapCopyist<M, K, V> {

    private final IFieldCloner<K> keyCloner;
    private final IFieldCloner<V> valueCloner;
    private final Predicate<K> keyPredicate;
    private final Predicate<V> valuePredicate;

    public MapCopyist(IFieldCloner<K> keyCloner, IFieldCloner<V> valueCloner) {
        this(keyCloner, valueCloner, null, null);
    }

    public MapCopyist(IFieldCloner<K> keyCloner, IFieldCloner<V> valueCloner, Predicate<K> keyPredicate, Predicate<V> valuePredicate) {
        this.keyCloner = keyCloner;
        this.valueCloner = valueCloner;
        this.keyPredicate = keyPredicate;
        this.valuePredicate = valuePredicate;
    }

    @Override
    public void copy(M source, M target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            if (keyPredicate == null && valuePredicate == null)
                source.forEach((k, v) -> target.put(keyCloner.clone(k), valueCloner.clone(v)));

            if (keyPredicate != null && valuePredicate == null) {
                source.forEach((k, v) -> {
                    if (keyPredicate.test(k))
                        target.put(keyCloner.clone(k), valueCloner.clone(v));
                });
            }

            if (keyPredicate == null && valuePredicate != null) {
                source.forEach((k, v) -> {
                    if (valuePredicate.test(v))
                        target.put(keyCloner.clone(k), valueCloner.clone(v));
                });
            }

            if (keyPredicate != null && valuePredicate != null) {
                source.forEach((k, v) -> {
                    if (keyPredicate.test(k) && valuePredicate.test(v))
                        target.put(keyCloner.clone(k), valueCloner.clone(v));
                });
            }
        }
    }

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyMapCopyist(IFieldCloner<V> valueCloner) {
        return new MapCopyist<>(FieldCloner.simpleFieldCloner(), valueCloner);
    }

    public static <M extends Map<K, V>, K, V> IMapCopyist<M, K, V> primitiveKeyAndValueMapCopyist() {
        return new MapCopyist<>(FieldCloner.simpleFieldCloner(), FieldCloner.simpleFieldCloner());
    }

}
