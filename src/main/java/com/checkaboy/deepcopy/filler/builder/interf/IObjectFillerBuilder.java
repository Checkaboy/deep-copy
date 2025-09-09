package com.checkaboy.deepcopy.filler.builder.interf;

import com.checkaboy.deepcopy.filler.interf.IFieldFiller;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface IObjectFillerBuilder<S, T>
        extends IFillerBuilder<S, T> {

    IObjectFillerBuilder<S, T> setFieldFillers(Map<String, IFieldFiller<S, T>> fieldFillerMap);

    IObjectFillerBuilder<S, T> putFieldFiller(String fieldName, IFieldFiller<S, T> fieldFiller);

    IObjectFillerBuilder<S, T> putAllFieldFillers(Map<String, IFieldFiller<S, T>> fieldFillerMap);

    IObjectFillerBuilder<S, T> setPredicate(Predicate<String> predicate);

}
