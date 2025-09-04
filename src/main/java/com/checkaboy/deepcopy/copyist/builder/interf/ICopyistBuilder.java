package com.checkaboy.deepcopy.copyist.builder.interf;

import com.checkaboy.deepcopy.copyist.ICopyist;

/**
 * @author Taras Shaptala
 */
public interface ICopyistBuilder<O> {

    ICopyist<O> build();

}
