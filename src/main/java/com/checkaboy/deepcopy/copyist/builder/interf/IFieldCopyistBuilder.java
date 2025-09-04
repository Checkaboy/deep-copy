package com.checkaboy.deepcopy.copyist.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface IFieldCopyistBuilder<O, V>
        extends ICopyistBuilder<O> {

    IFieldCopyistBuilder<O, V> setExtractor(Function<O, V> extractor);

    IFieldCopyistBuilder<O, V> setInserter(BiConsumer<O, V> inserter);

    IFieldCopyistBuilder<O, V> setFieldCloner(IFieldCloner<V> fieldCloner);

}
