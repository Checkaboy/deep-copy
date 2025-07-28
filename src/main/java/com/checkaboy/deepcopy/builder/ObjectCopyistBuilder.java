package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.copyist.interf.ICopyist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyistBuilder<O> {

    private final Class<O> type;
    private Map<String, ICopyist<O>> fieldCopyistMap = new HashMap<>();

    public ObjectCopyistBuilder(Class<O> type) {
        this.type = type;
    }

    public void setFieldCopyists(Map<String, ICopyist<O>> fieldCopyistMap) {
        this.fieldCopyistMap = fieldCopyistMap;
    }

    public void putFieldCopyist(String fieldName, ICopyist<O> copyist) {
        this.fieldCopyistMap.put(fieldName, copyist);
    }

    public void putAllFieldCopyists(Map<String, ICopyist<O>> fieldCopyistMap) {
        this.fieldCopyistMap.putAll(fieldCopyistMap);
    }

    public Class<O> getType() {
        return type;
    }

}
