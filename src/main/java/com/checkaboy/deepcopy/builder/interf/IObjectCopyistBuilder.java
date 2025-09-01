package com.checkaboy.deepcopy.builder.interf;

import com.checkaboy.deepcopy.copyist.interf.IFieldCopyist;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectCopyistBuilder<O> {

    IObjectCopyistBuilder<O> setFieldCopyists(Map<String, IFieldCopyist<O>> fieldCopyistMap);

    IObjectCopyistBuilder<O> putFieldCopyist(String fieldName, IFieldCopyist<O> copyist);

    IObjectCopyistBuilder<O> putAllFieldCopyists(Map<String, IFieldCopyist<O>> fieldCopyistMap);

}
