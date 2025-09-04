package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.ICopyist;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class FieldAdapter2<SO, TO, SV, TV>
        implements IFieldAdapter<SO, TO> {

    private final Function<SO, SV> extractor;
    private final Function<SV, TV> creator;
    private final IFieldAdapter<SV, TV> mapper;
    private final BiConsumer<TO, TV> inserter;

    public FieldAdapter2(Function<SO, SV> extractor, Function<SV, TV> creator, IFieldAdapter<SV, TV> mapper, BiConsumer<TO, TV> inserter) {
        this.extractor = extractor;
        this.creator = creator;
        this.mapper = mapper;
        this.inserter = inserter;
    }

    public FieldAdapter2(Function<SO, SV> extractor, Supplier<TV> creator, IFieldAdapter<SV, TV> mapper, BiConsumer<TO, TV> inserter) {
        this.extractor = extractor;
        this.creator = sv -> creator.get();
        this.mapper = mapper;
        this.inserter = inserter;
    }

    public void copy(SO source, TO target) {
        SV valueSource = extractor.apply(source);
        if (valueSource == null) {
            return;
        }

        TV valueTarget = creator.apply(valueSource);
        mapper.copy(valueSource, valueTarget);
        inserter.accept(target, valueTarget);
    }

}
