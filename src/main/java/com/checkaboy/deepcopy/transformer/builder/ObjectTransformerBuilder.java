package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.model.ObjectTransformer;
import com.checkaboy.deepcopy.transformer.builder.interf.IObjectTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.interf.IObjectTransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectTransformerBuilder<S, T>
        extends AbstractTypifiedContainer<T>
        implements IObjectTransformerBuilder<S, T> {

    private Supplier<T> constructor;
    private IObjectFiller<S, T> objectFiller;

    protected ObjectTransformerBuilder(Class<T> type) {
        super(type);
    }

    @Override
    public ObjectTransformerBuilder<S, T> setConstructor(Supplier<T> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public ObjectTransformerBuilder<S, T> setObjectFiller(IObjectFiller<S, T> objectFiller) {
        this.objectFiller = objectFiller;
        return this;
    }

    @Override
    public IObjectTransformer<S, T> build() {
        if (constructor == null)
            throw new NullPointerException("ObjectTransformerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IObjectTransformer<" + getType().getSimpleName() + "> without constructor");

        return new ObjectTransformer<>(constructor, objectFiller);
    }

}
