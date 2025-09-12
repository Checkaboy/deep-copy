package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.model.general.FieldFiller;
import com.checkaboy.deepcopy.filler.builder.interf.IFieldFillerBuilder;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.model.predicative.PredicativeFieldFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class FieldFillerBuilder<SO, TO, SV, TV>
        extends AbstractTypifiedContainer<TV>
        implements IFieldFillerBuilder<SO, TO, SV, TV> {

    private Function<SO, SV> extractor;
    private BiConsumer<TO, TV> inserter;
    private IFieldTransformer<SV, TV> transformer;
    private Predicate<SV> predicate;

    protected FieldFillerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public FieldFillerBuilder<SO, TO, SV, TV> setExtractor(Function<SO, SV> extractor) {
        this.extractor = extractor;
        return this;
    }

    @Override
    public FieldFillerBuilder<SO, TO, SV, TV> setInserter(BiConsumer<TO, TV> inserter) {
        this.inserter = inserter;
        return this;
    }

    @Override
    public FieldFillerBuilder<SO, TO, SV, TV> setTransformer(IFieldTransformer<SV, TV> transformer) {
        this.transformer = transformer;
        return this;
    }

    @Override
    public FieldFillerBuilder<SO, TO, SV, TV> setPredicate(Predicate<SV> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public IFieldFiller<SO, TO> build() {
        if (extractor == null)
            throw new NullPointerException("FieldFillerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IFieldFiller<" + getType().getSimpleName() + "> without extractor");

        if (inserter == null)
            throw new NullPointerException("FieldFillerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IFieldFiller<" + getType().getSimpleName() + "> without inserter");

        if (transformer == null)
            throw new NullPointerException("FieldFillerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IFieldFiller<" + getType().getSimpleName() + "> without transformer");

        if (predicate == null) return new FieldFiller<>(extractor, inserter, transformer);
        else return new PredicativeFieldFiller<>(extractor, inserter, transformer, predicate);
    }

}
