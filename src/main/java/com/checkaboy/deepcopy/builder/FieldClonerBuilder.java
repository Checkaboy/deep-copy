package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.ICloner;

/**
 * @author Taras Shaptala
 */
public class FieldClonerBuilder<O> {

    private final Class<O> type;
    private ICloner<O> cloner = source -> source;

    public FieldClonerBuilder(Class<O> type) {
        this.type = type;
    }

    public void setCloner(ICloner<O> cloner) {
        this.cloner = cloner;
    }

    public ICloner<O> build() {
        return new FieldCloner<>(cloner);
    }

    public Class<O> getType() {
        return type;
    }

}
