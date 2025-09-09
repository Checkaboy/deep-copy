package com.checkaboy.deepcopy.transformer.builder.interf;

import com.checkaboy.deepcopy.filler.interf.ICollectionFiller;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public interface ICollectionTransformerBuilder<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends ITransformerBuilder<SC, TC> {

    ICollectionTransformerBuilder<SC, SV, TC, TV> setConstructor(Function<Integer, TC> constructor);

    ICollectionTransformerBuilder<SC, SV, TC, TV> setCollectionFiller(ICollectionFiller<SC, SV, TC, TV> collectionFiller);

}
