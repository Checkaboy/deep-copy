package com.checkaboy.deepcopy.transformer.builder.interf;

import com.checkaboy.deepcopy.transformer.interf.IFieldTransformer;

/**
 * @author Taras Shaptala
 */
public interface IFieldTransformerBuilder<S, T>
        extends ITransformerBuilder<S, T> {

    IFieldTransformerBuilder<S, T> setTransformer(IFieldTransformer<S, T> transformer);

}
