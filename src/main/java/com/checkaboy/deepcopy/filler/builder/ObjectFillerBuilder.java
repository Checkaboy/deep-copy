package com.checkaboy.deepcopy.filler.builder;

import com.checkaboy.deepcopy.filler.model.general.ObjectFiller;
import com.checkaboy.deepcopy.filler.builder.interf.IObjectFillerBuilder;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.filler.model.predicative.PredicativeObjectFiller;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class ObjectFillerBuilder<S, T>
        extends AbstractTypifiedContainer<T>
        implements IObjectFillerBuilder<S, T> {

    private Map<String, IFieldFiller<S, T>> fieldCopyistMap = new HashMap<>();
    private Predicate<String> predicate;

    protected ObjectFillerBuilder(Class<T> type) {
        super(type);
    }

    @Override
    public ObjectFillerBuilder<S, T> setFieldFillers(Map<String, IFieldFiller<S, T>> fieldFillerMap) {
        this.fieldCopyistMap = fieldFillerMap;
        return this;
    }

    @Override
    public ObjectFillerBuilder<S, T> putFieldFiller(String fieldName, IFieldFiller<S, T> fieldFiller) {
        this.fieldCopyistMap.put(fieldName, fieldFiller);
        return this;
    }

    @Override
    public ObjectFillerBuilder<S, T> putAllFieldFillers(Map<String, IFieldFiller<S, T>> fieldFillerMap) {
        this.fieldCopyistMap.putAll(fieldFillerMap);
        return this;
    }

    @Override
    public ObjectFillerBuilder<S, T> setPredicate(Predicate<String> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public IObjectFiller<S, T> build() {
        if (predicate == null) return new ObjectFiller<>(fieldCopyistMap);
        else return new PredicativeObjectFiller<>(fieldCopyistMap, predicate);
    }

}
