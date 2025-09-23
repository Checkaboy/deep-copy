package com.checkaboy.deepcopy.context.cache;

/**
 * @author Taras Shaptala
 */
public interface ICopyistCache {

    <S, T> ICopyistContext<S, T> get(Object object);

}
