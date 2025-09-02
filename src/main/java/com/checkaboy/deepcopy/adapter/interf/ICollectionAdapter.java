package com.checkaboy.deepcopy.adapter.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionAdapter<SC extends Collection<V>, TC extends Collection<V>, V>
        extends IFieldAdapter<SC, TC> {
}
