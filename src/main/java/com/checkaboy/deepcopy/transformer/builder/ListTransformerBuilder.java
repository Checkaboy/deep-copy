package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.model.CollectionTransformer;
import com.checkaboy.deepcopy.transformer.builder.interf.ICollectionTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.interf.ITransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ListTransformerBuilder<SV, TV>
        extends AbstractTypifiedContainer<TV>
        implements ICollectionTransformerBuilder<List<SV>, SV, List<TV>, TV> {

    private Function<Integer, List<TV>> constructor = ArrayList::new;
    private ICollectionFiller<List<SV>, SV, List<TV>, TV> collectionFiller;

    protected ListTransformerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public ListTransformerBuilder<SV, TV> setConstructor(Function<Integer, List<TV>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public ListTransformerBuilder<SV, TV> setCollectionFiller(ICollectionFiller<List<SV>, SV, List<TV>, TV> collectionFiller) {
        this.collectionFiller = collectionFiller;
        return this;
    }

    @Override
    public ITransformer<List<SV>, List<TV>> build() {
        if (collectionFiller == null)
            throw new NullPointerException("ListTransformerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "ITransformer<" + getType().getSimpleName() + "> without collectionFiller");

        return new CollectionTransformer<>(constructor, collectionFiller);
    }

}
