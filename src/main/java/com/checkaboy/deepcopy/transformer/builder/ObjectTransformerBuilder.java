package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.transformer.builder.interf.IObjectTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.ObjectTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IObjectTransformer;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class ObjectTransformerBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IObjectTransformerBuilder<S, T> {

    private Supplier<T> constructor = () -> null;
    private IObjectFiller<S, T> objectFiller = (cache, source, target) -> {
    };

    protected ObjectTransformerBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
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
        return new ObjectTransformer<>(constructor, objectFiller);
    }

}
