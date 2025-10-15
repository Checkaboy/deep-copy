package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.transformer.builder.interf.IFieldTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.FieldTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import com.checkaboy.objectutils.container.AbstractBiTypifiedContainer;

/**
 * @author Taras Shaptala
 */
public class FieldTransformerBuilder<S, T>
        extends AbstractBiTypifiedContainer<S, T>
        implements IFieldTransformerBuilder<S, T> {

    private IFieldTransformer<S, T> transformer;

    protected FieldTransformerBuilder(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    @Override
    public FieldTransformerBuilder<S, T> setTransformer(IFieldTransformer<S, T> transformer) {
        this.transformer = transformer;
        return this;
    }

    @Override
    public IFieldTransformer<S, T> build() {
        return new FieldTransformer<>(transformer);
    }

}
