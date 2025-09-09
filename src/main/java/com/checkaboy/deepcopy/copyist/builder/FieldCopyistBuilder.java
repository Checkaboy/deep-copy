package com.checkaboy.deepcopy.copyist.builder;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.copyist.based.FieldCopyist;
import com.checkaboy.deepcopy.copyist.builder.interf.IFieldCopyistBuilder;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.predicative.PredicativeFieldCopyist;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class FieldCopyistBuilder<O, V>
        extends AbstractTypifiedContainer<O>
        implements IFieldCopyistBuilder<O, V> {

    private Function<O, V> extractor;
    private BiConsumer<O, V> inserter;
    private IFieldCloner<V> fieldCloner = source -> source;
    private Predicate<V> predicate;

    public FieldCopyistBuilder(Class<O> type) {
        super(type);
    }

    @Override
    public IFieldCopyistBuilder<O, V> setExtractor(Function<O, V> extractor) {
        this.extractor = extractor;
        return this;
    }

    @Override
    public IFieldCopyistBuilder<O, V> setInserter(BiConsumer<O, V> inserter) {
        this.inserter = inserter;
        return this;
    }

    @Override
    public IFieldCopyistBuilder<O, V> setFieldCloner(IFieldCloner<V> fieldCloner) {
        this.fieldCloner = fieldCloner;
        return this;
    }

    @Override
    public IFieldCopyistBuilder<O, V> setPredicate(Predicate<V> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public IFieldCopyist<O> build() {
        if (extractor == null)
            throw new NullPointerException("FieldCopyistBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IFieldCopyist<" + getType().getSimpleName() + "> without extractor");

        if (inserter == null)
            throw new NullPointerException("FieldCopyistBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IFieldCopyist<" + getType().getSimpleName() + "> without inserter");

        if (predicate == null) return new FieldCopyist<>(extractor, inserter, fieldCloner);
        else return new PredicativeFieldCopyist<>(extractor, inserter, fieldCloner, predicate);
    }

}
