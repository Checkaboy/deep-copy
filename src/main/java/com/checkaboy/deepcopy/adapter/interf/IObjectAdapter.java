package com.checkaboy.deepcopy.adapter.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectAdapter<S, T>
        extends IFieldAdapter<S, T>, Map<String, IFieldAdapter<S, T>> {
}
