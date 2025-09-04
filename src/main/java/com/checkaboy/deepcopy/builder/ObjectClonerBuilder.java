package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.builder.interf.IObjectClonerBuilder;
import com.checkaboy.deepcopy.cloner.ObjectCloner;
import com.checkaboy.deepcopy.cloner.interf.IObjectCloner;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;
import com.checkaboy.deepcopy.copyist.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectClonerBuilder<O>
        extends AbstractTypifiedContainer<O>
        implements IObjectClonerBuilder<O> {

    private Supplier<O> constructor;
    private IObjectCopyist<O> objectCopyist = new ObjectCopyist<>();

    public ObjectClonerBuilder(Class<O> type) {
        super(type);
    }

    @Override
    public ObjectClonerBuilder<O> setConstructor(Supplier<O> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public ObjectClonerBuilder<O> setObjectCopyist(IObjectCopyist<O> objectCopyist) {
        this.objectCopyist = objectCopyist;
        return this;
    }

    public IObjectCloner<O> build() {
        if (constructor == null)
            throw new NullPointerException("ObjectClonerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "ObjectCloner<" + getType().getSimpleName() + "> without constructor");
        return new ObjectCloner<>(constructor, objectCopyist);
    }

}
