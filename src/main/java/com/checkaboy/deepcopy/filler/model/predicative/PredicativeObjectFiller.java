package com.checkaboy.deepcopy.filler.model.predicative;

import com.checkaboy.deepcopy.context.cache.ICache;
import com.checkaboy.deepcopy.filler.model.general.ObjectFiller;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeObjectFiller<S, T>
        extends ObjectFiller<S, T>
        implements IObjectFiller<S, T> {

    private final Predicate<String> predicate;

    public PredicativeObjectFiller(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public PredicativeObjectFiller(int initialCapacity, Predicate<String> predicate) {
        super(initialCapacity);
        this.predicate = predicate;
    }

    public PredicativeObjectFiller(int initialCapacity, float loadFactor, Predicate<String> predicate) {
        super(initialCapacity, loadFactor);
        this.predicate = predicate;
    }

    public PredicativeObjectFiller(Map<? extends String, ? extends IFieldFiller<S, T>> m, Predicate<String> predicate) {
        super(m);
        this.predicate = predicate;
    }

    @Override
    protected void fillField(ICache cache, String fieldName, IFieldFiller<S, T> fieldFiller, S source, T target) {
        if (predicate.test(fieldName))
            super.fillField(cache, fieldName, fieldFiller, source, target);
    }

}
