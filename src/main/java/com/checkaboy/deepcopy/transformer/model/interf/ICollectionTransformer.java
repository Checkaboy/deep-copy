package com.checkaboy.deepcopy.transformer.model.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionTransformer<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IFieldTransformer<SC, TC> {
}
