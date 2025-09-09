package com.checkaboy.deepcopy.filler.predicative;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.filler.based.MapFiller;
import com.checkaboy.deepcopy.filler.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeMapFiller<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        extends MapFiller<SM, SV, TM, TV, K>
        implements IMapFiller<SM, SV, TM, TV, K> {

    private final Predicate<K> keyPredicate;
    private final Predicate<SV> valuePredicate;

    public PredicativeMapFiller(IFieldCloner<K> keyCloner, IFieldTransformer<SV, TV> valueTransformer, Predicate<K> keyPredicate, Predicate<SV> valuePredicate) {
        super(keyCloner, valueTransformer);
        this.keyPredicate = keyPredicate;
        this.valuePredicate = valuePredicate;
    }

    @Override
    protected void fillValue(TM target, K key, SV value) {
        if (keyPredicate.test(key) && valuePredicate.test(value))
            super.fillValue(target, key, value);
    }

}
