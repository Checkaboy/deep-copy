package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.IFieldCopyistBuilder;
import com.checkaboy.deepcopy.cloner.FieldCloner;
import com.checkaboy.deepcopy.cloner.interf.IFieldCloner;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

/**
 * @author Taras Shaptala
 */
public class FieldCopyistBuilder<O>
        extends AbstractTypifiedContainer<O>
        implements IFieldCopyistBuilder<O> {

    private IFieldCloner<O> fieldCloner = source -> source;

    public FieldCopyistBuilder(Class<O> type) {
        super(type);
    }

    @Override
    public FieldCopyistBuilder<O> setFieldCloner(IFieldCloner<O> fieldCloner) {
        this.fieldCloner = fieldCloner;
        return this;
    }

    public IFieldCloner<O> build() {
        return new FieldCloner<>(fieldCloner);
    }

}
