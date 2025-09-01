package com.checkaboy.deepcopy.copyist.interf;

import java.util.Collection;

/**
 * @author Taras Shaptala
 */
public interface ICollectionCopyist<C extends Collection<V>, V>
        extends IFieldCopyist<C> {
}
