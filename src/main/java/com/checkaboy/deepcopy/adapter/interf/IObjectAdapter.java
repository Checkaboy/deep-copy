package com.checkaboy.deepcopy.adapter.interf;

import com.checkaboy.deepcopy.ICloner;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectAdapter<S, T>
        extends ICloner<S, T>, Map<String, IFieldAdapter<S, T>> {
}
