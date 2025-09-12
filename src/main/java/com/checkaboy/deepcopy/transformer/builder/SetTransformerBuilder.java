package com.checkaboy.deepcopy.transformer.builder;

import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.transformer.model.CollectionTransformer;
import com.checkaboy.deepcopy.transformer.builder.interf.ICollectionTransformerBuilder;
import com.checkaboy.deepcopy.transformer.model.interf.ITransformer;
import com.checkaboy.objectutils.container.AbstractTypifiedContainer;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class SetTransformerBuilder<SV, TV>
        extends AbstractTypifiedContainer<TV>
        implements ICollectionTransformerBuilder<Set<SV>, SV, Set<TV>, TV> {

    private Function<Integer, Set<TV>> constructor = HashSet::new;
    private ICollectionFiller<Set<SV>, SV, Set<TV>, TV> collectionFiller;

    protected SetTransformerBuilder(Class<TV> type) {
        super(type);
    }

    @Override
    public SetTransformerBuilder<SV, TV> setConstructor(Function<Integer, Set<TV>> constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public SetTransformerBuilder<SV, TV> setCollectionFiller(ICollectionFiller<Set<SV>, SV, Set<TV>, TV> collectionFiller) {
        this.collectionFiller = collectionFiller;
        return this;
    }

    @Override
    public ITransformer<Set<SV>, Set<TV>> build() {
        if (collectionFiller == null)
            throw new NullPointerException("SetTransformerBuilder<" + getType().getSimpleName() + "> can`t create " +
                    "ITransformer<" + getType().getSimpleName() + "> without collectionFiller");

        return new CollectionTransformer<>(constructor, collectionFiller);
    }

}
