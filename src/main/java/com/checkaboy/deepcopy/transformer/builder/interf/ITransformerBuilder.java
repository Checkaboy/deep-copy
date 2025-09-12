package com.checkaboy.deepcopy.transformer.builder.interf;

import com.checkaboy.deepcopy.transformer.model.interf.ITransformer;

/**
 * @author Taras Shaptala
 */
public interface ITransformerBuilder<S, T> {

    ITransformer<S, T> build();

}
