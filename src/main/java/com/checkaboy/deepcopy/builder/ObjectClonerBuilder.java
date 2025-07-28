package com.checkaboy.deepcopy.builder;

import com.checkaboy.deepcopy.cloner.ObjectCloner;
import com.checkaboy.deepcopy.cloner.interf.IObjectCloner;
import com.checkaboy.deepcopy.copyist.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectClonerBuilder<O> {

    private final Class<O> type;
    private Supplier<O> constructor = () -> null;
    private IObjectCopyist<O> objectCopyist = new ObjectCopyist<>();

    public ObjectClonerBuilder(Class<O> type) {
        this.type = type;
    }

    public void setConstructor(Supplier<O> constructor) {
        this.constructor = constructor;
    }

    public void setObjectCopyist(IObjectCopyist<O> objectCopyist) {
        this.objectCopyist = objectCopyist;
    }

    public IObjectCloner<O> build() {
        return new ObjectCloner<>(constructor, objectCopyist);
    }

    public Class<O> getType() {
        return type;
    }

}
