package com.checkaboy.deepcopy.adapter;

import com.checkaboy.deepcopy.ICloner;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Taras Shaptala
 */
public class ObjectFieldAdapter<OS, OT, VS, VT>
        implements IFieldAdapter<OS, OT> {

    private final Function<OS, VS> extractor;
    private final BiConsumer<OT, VT> inserter;
    private final ICloner<VS, VT> cloner;

    public ObjectFieldAdapter(Function<OS, VS> extractor, BiConsumer<OT, VT> inserter, ICloner<VS, VT> cloner) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.cloner = cloner;
    }

    @Override
    public void copy(OS source, OT target) {
        VS valueSource = extractor.apply(source);
        VT valueTarget = cloner.clone(valueSource);
        inserter.accept(target, valueTarget);
    }

}
