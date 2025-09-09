package com.checkaboy.deepcopy.copyist.builder;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.based.MapCopyist;
import com.checkaboy.deepcopy.copyist.builder.interf.IMapCopyistBuilder;
import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;
import com.checkaboy.deepcopy.copyist.predicative.PredicativeMapCopyist;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class MapCopyistBuilder<M extends Map<K, V>, K, V>
        extends AbstractTypifiedContainer<V>
        implements IMapCopyistBuilder<M, K, V> {

    private IFieldCloner<K> keyCloner = source -> source;
    private IFieldCloner<V> valueCloner = source -> source;
    private Predicate<K> keyPredicate;
    private Predicate<V> valuePredicate;

    public MapCopyistBuilder(Class<V> type) {
        super(type);
    }

    @Override
    public MapCopyistBuilder<M, K, V> setKeyCloner(IFieldCloner<K> keyCloner) {
        this.keyCloner = keyCloner;
        return this;
    }

    @Override
    public MapCopyistBuilder<M, K, V> setValueCloner(IFieldCloner<V> valueCloner) {
        this.valueCloner = valueCloner;
        return this;
    }

    @Override
    public MapCopyistBuilder<M, K, V> setKeyPredicate(Predicate<K> keyPredicate) {
        this.keyPredicate = keyPredicate;
        return this;
    }

    @Override
    public MapCopyistBuilder<M, K, V> setValuePredicate(Predicate<V> valuePredicate) {
        this.valuePredicate = valuePredicate;
        return this;
    }

    @Override
    public IMapCopyist<M, K, V> build() {
        if (keyPredicate == null && valuePredicate == null)
            return new MapCopyist<>(keyCloner, valueCloner);
        else {
            return new PredicativeMapCopyist<>(keyCloner, valueCloner,
                    keyPredicate == null ? k -> true : keyPredicate,
                    valuePredicate == null ? v -> true : valuePredicate
            );
        }
    }

}
