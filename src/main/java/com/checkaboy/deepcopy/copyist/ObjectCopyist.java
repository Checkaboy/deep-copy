package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.ICopyist;
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

    private final Map<String, ICopyist<O>> fieldCopyistMap;

    public ObjectCopyist() {
        this(new HashMap<>());
    }

    public ObjectCopyist(Map<String, ICopyist<O>> fieldCopyistMap) {
        this.fieldCopyistMap = fieldCopyistMap;
    }

    @Override
    public void copy(O source, O target) {
        for (Entry<String, ICopyist<O>> entry : entrySet())
            entry.getValue().copy(source, target);
    }

    @Override
    public void fieldCopy(String fieldName, O source, O target) {
        ICopyist<O> fieldComparator = fieldCopyistMap.get(fieldName);
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
    public ICopyist<O> get(Object key) {
        return fieldCopyistMap.get(key);
    }

    @Override
    public ICopyist<O> put(String key, ICopyist<O> value) {
        return fieldCopyistMap.put(key, value);
    }

    @Override
    public ICopyist<O> remove(Object key) {
        return fieldCopyistMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends ICopyist<O>> m) {
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
    public Collection<ICopyist<O>> values() {
        return fieldCopyistMap.values();
    }

    @Override
    public Set<Entry<String, ICopyist<O>>> entrySet() {
        return fieldCopyistMap.entrySet();
    }

}
