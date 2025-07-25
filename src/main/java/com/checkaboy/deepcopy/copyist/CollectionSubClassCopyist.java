package com.checkaboy.deepcopy.copyist;

import com.checkaboy.deepcopy.copyist.interf.ICopyist;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Taras Shaptala
 */
public class CollectionSubClassCopyist<O, C extends Collection<V>, V>
        implements ICopyist<O> {

    private final Function<O, C> extractor;
    private final BiConsumer<O, C> inserter;
    protected final Function<Integer, C> collectionFactory;
    protected final Supplier<V> constructor;
    protected final ICopyist<V> copyist;

    public CollectionSubClassCopyist(Function<O, C> extractor, BiConsumer<O, C> inserter, Function<Integer, C> collectionFactory, Supplier<V> constructor, ICopyist<V> copyist) {
        this.extractor = extractor;
        this.inserter = inserter;
        this.collectionFactory = collectionFactory;
        this.constructor = constructor;
        this.copyist = copyist;
    }

    @Override
    public void copy(O source, O target) {
        C sourceCollection = extractor.apply(source);

        if(sourceCollection != null) {
            C newTargetCollection = collectionFactory.apply(sourceCollection.size());

            for (V sourceValue : sourceCollection) {
                V newTargetValue = constructor.get();
                copyist.copy(sourceValue, newTargetValue);
                newTargetCollection.add(newTargetValue);
            }

            inserter.accept(target, newTargetCollection);
        }
    }

}
