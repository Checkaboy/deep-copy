package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.model.general.MapFiller;
import com.checkaboy.deepcopy.filler.builder.interf.IMapFillerBuilder;
import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;
import com.checkaboy.deepcopy.filler.model.predicative.PredicativeMapFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class MapFillerBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends AbstractTypifiedContainer<TV>
        implements IMapFillerBuilder<SM, SK, SV, TM, TK, TV> {

    private IFieldTransformer<SK, TK> keyTransformer;
    private IFieldTransformer<SV, TV> valueTransformer;
    private Predicate<SK> keyPredicate;
    private Predicate<SV> valuePredicate;

    public MapFillerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public MapFillerBuilder<SM, SK, SV, TM, TK, TV> setKeyTransformer(IFieldTransformer<SK, TK> keyCloner) {
        this.keyTransformer = keyCloner;
        return this;
    }

    @Override
    public MapFillerBuilder<SM, SK, SV, TM, TK, TV> setValueTransformer(IFieldTransformer<SV, TV> valueTransformer) {
        this.valueTransformer = valueTransformer;
        return this;
    }

    @Override
    public MapFillerBuilder<SM, SK, SV, TM, TK, TV> setKeyPredicate(Predicate<SK> keyPredicate) {
        this.keyPredicate = keyPredicate;
        return this;
    }

    @Override
    public MapFillerBuilder<SM, SK, SV, TM, TK, TV> setValuePredicate(Predicate<SV> valuePredicate) {
        this.valuePredicate = valuePredicate;
        return this;
    }

    @Override
    public IMapFiller<SM, SK, SV, TM, TK, TV> build() {
        if (keyTransformer == null)
            throw new NullPointerException("MapFillerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IMapFiller<" + getType().getSimpleName() + "> without keyTransformer");

        if (valueTransformer == null)
            throw new NullPointerException("MapFillerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IMapFiller<" + getType().getSimpleName() + "> without valueTransformer");

        if (keyPredicate == null && valuePredicate == null) return new MapFiller<>(keyTransformer, valueTransformer);
        else return new PredicativeMapFiller<>(keyTransformer, valueTransformer,
                keyPredicate == null ? k -> true : keyPredicate,
                valuePredicate == null ? v -> true : valuePredicate
        );
    }

}
