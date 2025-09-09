package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.filler.based.MapFiller;
import com.checkaboy.deepcopy.filler.builder.interf.IMapFillerBuilder;
import com.checkaboy.deepcopy.filler.interf.IMapFiller;
import com.checkaboy.deepcopy.filler.predicative.PredicativeMapFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class MapFillerBuilder<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        extends AbstractTypifiedContainer<TV>
        implements IMapFillerBuilder<SM, SV, TM, TV, K> {

    private IFieldCloner<K> keyCloner = source -> source;
    private IFieldTransformer<SV, TV> valueTransformer;
    private Predicate<K> keyPredicate;
    private Predicate<SV> valuePredicate;

    public MapFillerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public MapFillerBuilder<SM, SV, TM, TV, K> setKeyCloner(IFieldCloner<K> keyCloner) {
        this.keyCloner = keyCloner;
        return this;
    }

    @Override
    public MapFillerBuilder<SM, SV, TM, TV, K> setValueTransformer(IFieldTransformer<SV, TV> valueTransformer) {
        this.valueTransformer = valueTransformer;
        return this;
    }

    @Override
    public MapFillerBuilder<SM, SV, TM, TV, K> setKeyPredicate(Predicate<K> keyPredicate) {
        this.keyPredicate = keyPredicate;
        return this;
    }

    @Override
    public MapFillerBuilder<SM, SV, TM, TV, K> setValuePredicate(Predicate<SV> valuePredicate) {
        this.valuePredicate = valuePredicate;
        return this;
    }

    @Override
    public IMapFiller<SM, SV, TM, TV, K> build() {
        if (valueTransformer == null)
            throw new NullPointerException("MapFillerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IMapFiller<" + getType().getSimpleName() + "> without valueTransformer");

        if (keyPredicate == null && valuePredicate == null) return new MapFiller<>(keyCloner, valueTransformer);
        else return new PredicativeMapFiller<>(keyCloner, valueTransformer,
                keyPredicate == null ? k -> true : keyPredicate,
                valuePredicate == null ? v -> true : valuePredicate
        );
    }

}
