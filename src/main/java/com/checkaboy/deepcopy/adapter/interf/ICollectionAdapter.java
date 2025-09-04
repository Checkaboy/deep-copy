package com.checkaboy.deepcopy.adapter.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionAdapter<SC extends Collection<SV>, SV, TC extends Collection<TV>, TV>
        extends IFieldAdapter<SC, TC> {
}
