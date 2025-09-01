package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.copyist.interf.IMapCopyist;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IMapClonerBuilder<M extends Map<K, V>, K, V> {

    IMapClonerBuilder<M, K, V> setConstructor(Function<Integer, M> constructor);

    IMapClonerBuilder<M, K, V> setMapCopyist(IMapCopyist<M, K, V> mapCopyist);

}
