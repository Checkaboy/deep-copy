package com.checkaboy.deepcopy.filler.builder.interf;

import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface IMapFillerBuilder<SM extends Map<K, SV>, SV, TM extends Map<K, TV>, TV, K>
        extends IFillerBuilder<SM, TM> {

    IMapFillerBuilder<SM, SV, TM, TV, K> setKeyCloner(IFieldCloner<K> keyCloner);

    IMapFillerBuilder<SM, SV, TM, TV, K> setValueTransformer(IFieldTransformer<SV, TV> valueTransformer);

    IMapFillerBuilder<SM, SV, TM, TV, K> setKeyPredicate(Predicate<K> keyPredicate);

    IMapFillerBuilder<SM, SV, TM, TV, K> setValuePredicate(Predicate<SV> valuePredicate);

}
