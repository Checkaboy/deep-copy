package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyist<O>
        implements IObjectCopyist<O> {

    private final Map<String, IFieldCopyist<O>> fieldCopyistMap;

    public ObjectCopyist() {
        this(new HashMap<>());
    }

    public ObjectCopyist(Map<String, IFieldCopyist<O>> fieldCopyistMap) {
        this.fieldCopyistMap = fieldCopyistMap;
    }

    @Override
    public void copy(O source, O target) {
        for (Entry<String, IFieldCopyist<O>> entry : entrySet())
            entry.getValue().copy(source, target);
    }

    @Override
    public void fieldCopy(String fieldName, O source, O target) {
        IFieldCopyist<O> fieldComparator = fieldCopyistMap.get(fieldName);
        if (fieldComparator != null)
            fieldComparator.copy(source, target);
    }

    @Override
    public int size() {
        return fieldCopyistMap.size();
    }

    @Override
    public boolean isEmpty() {
        return fieldCopyistMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return fieldCopyistMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return fieldCopyistMap.containsValue(value);
    }

    @Override
    public IFieldCopyist<O> get(Object key) {
        return fieldCopyistMap.get(key);
    }

    @Override
    public IFieldCopyist<O> put(String key, IFieldCopyist<O> value) {
        return fieldCopyistMap.put(key, value);
    }

    @Override
    public IFieldCopyist<O> remove(Object key) {
        return fieldCopyistMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends IFieldCopyist<O>> m) {
        fieldCopyistMap.putAll(m);
    }

    @Override
    public void clear() {
        fieldCopyistMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return fieldCopyistMap.keySet();
    }

    @Override
    public Collection<IFieldCopyist<O>> values() {
        return fieldCopyistMap.values();
    }

    @Override
    public Set<Entry<String, IFieldCopyist<O>>> entrySet() {
        return fieldCopyistMap.entrySet();
    }

}
