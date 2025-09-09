package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.interf.IMapFiller;
import com.checkaboy.deepcopy.transformer.MapTransformer;
import com.checkaboy.deepcopy.transformer.builder.interf.IMapTransformerBuilder;
import com.checkaboy.deepcopy.transformer.interf.IMapTransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class MapTransformerBuilder<SV, TV, K>
        extends AbstractTypifiedContainer<TV>
        implements IMapTransformerBuilder<Map<K, SV>, SV, Map<K, TV>, TV, K> {

    private Function<Integer, Map<K, TV>> constructor = HashMap::new;
    private IMapFiller<Map<K, SV>, SV, Map<K, TV>, TV, K> mapFiller;

    protected MapTransformerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public MapTransformerBuilder<SV, TV, K> setMapFiller(IMapFiller<Map<K, SV>, SV, Map<K, TV>, TV, K> mapFiller) {
        this.mapFiller = mapFiller;
        return this;
    }

    @Override
    public MapTransformerBuilder<SV, TV, K> setConstructor(Function<Integer, Map<K, TV>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public IMapTransformer<Map<K, SV>, SV, Map<K, TV>, TV, K> build() {
        if (mapFiller == null)
            throw new NullPointerException("MapTransformerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "IMapTransformer<" + getType().getSimpleName() + "> without mapFiller");

        return new MapTransformer<>(constructor, mapFiller);
    }

}
