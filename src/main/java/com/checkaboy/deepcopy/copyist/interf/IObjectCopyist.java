package com.checkaboy.deepcopy.copyist.interf;

import java.util.Map;

/**
 * @author Taras Shaptala
 */
public interface IObjectCopyist<O>
        extends ICopyist<O>, Map<String, ICopyist<O>> {

    void fieldCopy(String fieldName, O source, O target);

}
