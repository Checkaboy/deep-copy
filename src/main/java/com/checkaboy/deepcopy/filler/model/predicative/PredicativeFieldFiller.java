package com.checkaboy.deepcopy.filler.model.predicative;

import com.checkaboy.deepcopy.context.cache.ICopyistCache;
import com.checkaboy.deepcopy.filler.model.general.FieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeFieldFiller<SO, TO, SV, TV>
        extends FieldFiller<SO, TO, SV, TV>
        implements IFieldFiller<SO, TO> {

    private final Predicate<SV> predicate;

    public PredicativeFieldFiller(Function<SO, SV> extractor, BiConsumer<TO, TV> inserter, IFieldTransformer<SV, TV> transformer, Predicate<SV> predicate) {
        super(extractor, inserter, transformer);
        this.predicate = predicate;
    }

    @Override
    protected void fillValue(ICopyistCache cache, TO target, SV sourceValue) {
        if (predicate.test(sourceValue))
            super.fillValue(cache, target, sourceValue);
    }

}
