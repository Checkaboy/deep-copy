package com.checkaboy.deepcopy.filler.builder.interf;

import com.checkaboy.deepcopy.filler.model.interf.IFiller;

/**
 * @author Taras Shaptala
 */
public interface IFillerBuilder<S, T> {

    IFiller<S, T> build();

}
