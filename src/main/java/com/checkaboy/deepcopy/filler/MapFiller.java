package com.checkaboy.deepcopy.filler;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.filler.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class MapFiller<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        implements IMapFiller<SM, SV, TM, TV, K> {

    private final IFieldCloner<K> keyCloner;
    private final IFieldTransformer<SV, TV> valueTransformer;
    private final Predicate<K> keyPredicate;
    private final Predicate<SV> valuePredicate;

    public MapFiller(IFieldCloner<K> keyCloner, IFieldTransformer<SV, TV> valueTransformer) {
        this(keyCloner, valueTransformer, null, null);
    }

    public MapFiller(IFieldCloner<K> keyCloner, IFieldTransformer<SV, TV> valueTransformer, Predicate<K> keyPredicate, Predicate<SV> valuePredicate) {
        this.keyCloner = keyCloner;
        this.valueTransformer = valueTransformer;
        this.keyPredicate = keyPredicate;
        this.valuePredicate = valuePredicate;
    }

    @Override
    public void fill(SM source, TM target) {
        if (source != null) {
            if (!target.isEmpty()) target.clear();
            if (source.isEmpty()) return;

            if (keyPredicate == null && valuePredicate == null)
                source.forEach((k, v) -> target.put(keyCloner.clone(k), valueTransformer.transform(v)));

            if (keyPredicate != null && valuePredicate == null) {
                source.forEach((k, v) -> {
                    if (keyPredicate.test(k))
                        target.put(keyCloner.clone(k), valueTransformer.transform(v));
                });
            }

            if (keyPredicate == null && valuePredicate != null) {
                source.forEach((k, v) -> {
                    if (valuePredicate.test(v))
                        target.put(keyCloner.clone(k), valueTransformer.transform(v));
                });
            }

            if (keyPredicate != null && valuePredicate != null) {
                source.forEach((k, v) -> {
                    if (keyPredicate.test(k) && valuePredicate.test(v))
                        target.put(keyCloner.clone(k), valueTransformer.transform(v));
                });
            }
        }
    }

}
