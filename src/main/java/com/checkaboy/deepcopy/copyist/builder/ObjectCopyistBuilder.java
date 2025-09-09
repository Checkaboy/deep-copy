package com.checkaboy.deepcopy.copyist.builder;

import com.checkaboy.deepcopy.copyist.based.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.builder.interf.IObjectCopyistBuilder;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;
import com.checkaboy.deepcopy.copyist.predicative.PredicativeObjectCopyist;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyistBuilder<O>
        extends AbstractTypifiedContainer<O>
        implements IObjectCopyistBuilder<O> {

    private Map<String, IFieldCopyist<O>> fieldCopyistMap = new HashMap<>();
    private Predicate<String> predicate;

    public ObjectCopyistBuilder(Class<O> type) {
        super(type);
    }

    @Override
    public ObjectCopyistBuilder<O> setFieldCopyists(Map<String, IFieldCopyist<O>> fieldCopyistMap) {
        this.fieldCopyistMap = fieldCopyistMap;
        return this;
    }

    @Override
    public ObjectCopyistBuilder<O> putFieldCopyist(String fieldName, IFieldCopyist<O> fieldCopyist) {
        this.fieldCopyistMap.put(fieldName, fieldCopyist);
        return this;
    }

    @Override
    public ObjectCopyistBuilder<O> putAllFieldCopyists(Map<String, IFieldCopyist<O>> fieldCopyistMap) {
        this.fieldCopyistMap.putAll(fieldCopyistMap);
        return this;
    }

    @Override
    public ObjectCopyistBuilder<O> setPredicate(Predicate<String> predicate) {
        this.predicate = predicate;
        return this;
    }

    @Override
    public IObjectCopyist<O> build() {
        if (predicate == null) return new ObjectCopyist<>(fieldCopyistMap);
        else return new PredicativeObjectCopyist<>(fieldCopyistMap, predicate);
    }

}
