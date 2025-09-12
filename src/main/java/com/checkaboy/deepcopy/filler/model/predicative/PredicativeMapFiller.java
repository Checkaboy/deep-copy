package com.checkaboy.deepcopy.filler.model.predicative;

import com.checkaboy.deepcopy.filler.model.general.MapFiller;
import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeMapFiller<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends MapFiller<SM, SK, SV, TM, TK, TV>
        implements IMapFiller<SM, SK, SV, TM, TK, TV> {

    private final Predicate<SK> keyPredicate;
    private final Predicate<SV> valuePredicate;

    public PredicativeMapFiller(IFieldTransformer<SK, TK> fieldTransformer, IFieldTransformer<SV, TV> valueTransformer, Predicate<SK> keyPredicate, Predicate<SV> valuePredicate) {
        super(fieldTransformer, valueTransformer);
        this.keyPredicate = keyPredicate;
        this.valuePredicate = valuePredicate;
    }

    @Override
    protected void fillValue(TM target, SK key, SV value) {
        if (keyPredicate.test(key) && valuePredicate.test(value))
            super.fillValue(target, key, value);
    }

}
