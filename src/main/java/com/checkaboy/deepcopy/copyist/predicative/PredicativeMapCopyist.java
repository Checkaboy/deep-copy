package com.checkaboy.deepcopy.copyist.predicative;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.based.MapCopyist;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeMapCopyist<M extends Map<K, V>, K, V>
        extends MapCopyist<M, K, V>
        implements IMapCopyist<M, K, V> {

    private final Predicate<K> keyPredicate;
    private final Predicate<V> valuePredicate;

    public PredicativeMapCopyist(IFieldCloner<K> keyCloner, IFieldCloner<V> valueCloner, Predicate<K> keyPredicate, Predicate<V> valuePredicate) {
        super(keyCloner, valueCloner);
        this.keyPredicate = keyPredicate;
        this.valuePredicate = valuePredicate;
    }

    @Override
    protected void copyValue(M target, K key, V value) {
        if (keyPredicate.test(key) && valuePredicate.test(value))
            super.copyValue(target, key, value);
    }

}
