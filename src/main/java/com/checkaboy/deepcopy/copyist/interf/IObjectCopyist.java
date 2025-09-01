package com.checkaboy.deepcopy.copyist.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectCopyist<O>
        extends IFieldCopyist<O>, Map<String, IFieldCopyist<O>> {

    void fieldCopy(String fieldName, O source, O target);

}
