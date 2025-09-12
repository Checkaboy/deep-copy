package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.transformer.model.FieldTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class TransformerTest {

    @Test
    public void simpleTest() {
        IFieldTransformer<Integer, String> adapter = new FieldTransformer<>(Object::toString);
        String string = adapter.transform(1);
        Assert.assertEquals(string, "1");
    }

}
