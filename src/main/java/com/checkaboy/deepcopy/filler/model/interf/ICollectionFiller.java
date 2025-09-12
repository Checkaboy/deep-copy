package com.checkaboy.deepcopy.filler.model.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionFiller<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IFieldFiller<SC, TC> {
}
