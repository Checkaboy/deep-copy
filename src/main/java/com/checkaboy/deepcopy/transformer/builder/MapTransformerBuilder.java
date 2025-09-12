package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.model.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.builder.interf.IMapTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.MapTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IMapTransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapTransformerBuilder<SK, SV, TK, TV>
        extends AbstractTypifiedContainer<TV>
        implements IMapTransformerBuilder<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> {

    private Function<Integer, Map<TK, TV>> constructor = HashMap::new;
    private IMapFiller<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> mapFiller;

    protected MapTransformerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public MapTransformerBuilder<SK, SV, TK, TV> setMapFiller(IMapFiller<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> mapFiller) {
        this.mapFiller = mapFiller;
        return this;
    }

    @Override
    public MapTransformerBuilder<SK, SV, TK, TV> setConstructor(Function<Integer, Map<TK, TV>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public IMapTransformer<Map<SK, SV>, SK, SV, Map<TK, TV>, TK, TV> build() {
        if (mapFiller == null)
            throw new NullPointerException("MapTransformerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IMapTransformer<" + getType().getSimpleName() + "> without mapFiller");

        return new MapTransformer<>(constructor, mapFiller);
    }

}
