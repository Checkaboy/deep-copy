package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.IFieldClonerBuilder;
import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.deepcopy.container.AbstractTypifiedContainer;

/**
 * @author Taras Shaptala
 */
public class FieldClonerBuilder<O>
        extends AbstractTypifiedContainer<O>
        implements IFieldClonerBuilder<O> {

    private IFieldCloner<O> fieldCloner = source -> source;

    public FieldClonerBuilder(Class<O> type) {
        super(type);
    }

    @Override
    public IFieldClonerBuilder<O> setFieldCloner(IFieldCloner<O> fieldCloner) {
        this.fieldCloner = fieldCloner;
        return this;
    }

    public IFieldCloner<O> build() {
        return new FieldCloner<>(fieldCloner);
    }

}
