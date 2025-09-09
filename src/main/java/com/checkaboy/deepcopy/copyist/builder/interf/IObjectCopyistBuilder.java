package com.checkaboy.deepcopy.copyist.builder.interf;

import com.checkaboy.deepcopy.copyist.builder.ObjectCopyistBuilder;
import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;

import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Taras Shaptala
 */
public interface IObjectCopyistBuilder<O>
        extends ICopyistBuilder<O> {

    IObjectCopyistBuilder<O> setFieldCopyists(Map<String, IFieldCopyist<O>> fieldCopyistMap);

    IObjectCopyistBuilder<O> putFieldCopyist(String fieldName, IFieldCopyist<O> copyist);

    IObjectCopyistBuilder<O> putAllFieldCopyists(Map<String, IFieldCopyist<O>> fieldCopyistMap);

    ObjectCopyistBuilder<O> setPredicate(Predicate<String> predicate);

}
