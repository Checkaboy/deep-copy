package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.builder.interf.IMapFillerBuilder;
import com.checkaboy.deepcopy.filler.model.general.MapFiller;
import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;
import com.checkaboy.deepcopy.filler.model.predicative.PredicativeMapFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class MapFillerBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends AbstractBiTypifiedContainer<SV, TV>
        implements IMapFillerBuilder<SM, SK, SV, TM, TK, TV> {

    private IFieldTransformer<SK, TK> keyTransformer = (cache, source) -> null;
    private IFieldTransformer<SV, TV> valueTransformer = (cache, source) -> null;
    private Predicate<SK> keyPredicate;
    private Predicate<SV> valuePredicate;

    public MapFillerBuilder(Class<SV> sourceType, Class<TV> targetType) {
        super(sourceType, targetType);
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
        if (keyPredicate == null && valuePredicate == null) return new MapFiller<>(keyTransformer, valueTransformer);
        else return new PredicativeMapFiller<>(keyTransformer, valueTransformer,
                keyPredicate == null ? k -> true : keyPredicate,
                valuePredicate == null ? v -> true : valuePredicate
        );
    }

}
