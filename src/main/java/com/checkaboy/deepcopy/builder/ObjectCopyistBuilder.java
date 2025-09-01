package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.IObjectCopyistBuilder;
import com.checkaboy.deepcopy.container.AbstractTypifiedContainer;
import com.checkaboy.deepcopy.copyist.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyistBuilder<O>
        extends AbstractTypifiedContainer<O>
        implements IObjectCopyistBuilder<O> {

    private Map<String, IFieldCopyist<O>> fieldCopyistMap = new HashMap<>();

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

    public IObjectCopyist<O> build() {
        return new ObjectCopyist<>(fieldCopyistMap);
    }

}
