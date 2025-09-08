package com.checkaboy.deepcopy.filler.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectFiller<S, T>
        extends IFieldFiller<S, T>, Map<String, IFieldFiller<S, T>> {

    void fieldFill(String fieldName, S source, T target);

}
