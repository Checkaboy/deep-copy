package com.checkaboy.deepcopy.copyist.predicative;

import com.checkaboy.deepcopy.copyist.based.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public class PredicativeObjectCopyist<O>
        extends ObjectCopyist<O>
        implements IObjectCopyist<O> {

    private final Predicate<String> predicate;

    public PredicativeObjectCopyist(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public PredicativeObjectCopyist(int initialCapacity, Predicate<String> predicate) {
        super(initialCapacity);
        this.predicate = predicate;
    }

    public PredicativeObjectCopyist(int initialCapacity, float loadFactor, Predicate<String> predicate) {
        super(initialCapacity, loadFactor);
        this.predicate = predicate;
    }

    public PredicativeObjectCopyist(Map<? extends String, ? extends IFieldCopyist<O>> m, Predicate<String> predicate) {
        super(m);
        this.predicate = predicate;
    }

    @Override
    protected void copyField(String fieldName, IFieldCopyist<O> fieldCopyist, O source, O target) {
        if (predicate.test(fieldName))
            super.copyField(fieldName, fieldCopyist, source, target);
    }

}
