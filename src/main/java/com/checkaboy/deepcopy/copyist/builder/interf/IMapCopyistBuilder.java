package com.checkaboy.deepcopy.copyist.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.builder.MapCopyistBuilder;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface IMapCopyistBuilder<M extends Map<K, V>, K, V>
        extends ICopyistBuilder<M> {

    IMapCopyistBuilder<M, K, V> setKeyCloner(IFieldCloner<K> keyCloner);

    IMapCopyistBuilder<M, K, V> setValueCloner(IFieldCloner<V> valueCloner);

    MapCopyistBuilder<M, K, V> setKeyPredicate(Predicate<K> keyPredicate);

    MapCopyistBuilder<M, K, V> setValuePredicate(Predicate<V> valuePredicate);

}
