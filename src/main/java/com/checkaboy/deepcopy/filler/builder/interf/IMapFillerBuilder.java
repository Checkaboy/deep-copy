package com.checkaboy.deepcopy.filler.builder.interf;

import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface IMapFillerBuilder<SM extends Map<SK, SV>, SK, SV, TM extends Map<TK, TV>, TK, TV>
        extends IFillerBuilder<SM, TM> {

    IMapFillerBuilder<SM, SK, SV, TM, TK, TV> setKeyTransformer(IFieldTransformer<SK, TK> keyCloner);

    IMapFillerBuilder<SM, SK, SV, TM, TK, TV> setValueTransformer(IFieldTransformer<SV, TV> valueTransformer);

    IMapFillerBuilder<SM, SK, SV, TM, TK, TV> setKeyPredicate(Predicate<SK> keyPredicate);

    IMapFillerBuilder<SM, SK, SV, TM, TK, TV> setValuePredicate(Predicate<SV> valuePredicate);

}
