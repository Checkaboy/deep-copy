package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.copyist.FieldCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICopyist;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class FieldCopyTest {

    private static class SimpleTestModel {

        private int intValue;
        private String stringValue;

        public int getIntValue() {
            return intValue;
        }

        public SimpleTestModel setIntValue(int intValue) {
            this.intValue = intValue;
            return this;
        }

        public String getStringValue() {
            return stringValue;
        }

        public SimpleTestModel setStringValue(String stringValue) {
            this.stringValue = stringValue;
            return this;
        }

    }

    @Test
    public void simpleFieldCopyTest() {
        ICopyist<SimpleTestModel> stringFieldCopyist = FieldCopyist.simpleCopyist(SimpleTestModel::getStringValue, SimpleTestModel::setStringValue);

        String value = "String data";
        SimpleTestModel sourceSimpleTestModel = new SimpleTestModel().setStringValue(value);
        SimpleTestModel targetSimpleTestModel = new SimpleTestModel();
        stringFieldCopyist.copy(sourceSimpleTestModel, targetSimpleTestModel);
        Assert.assertEquals(value, targetSimpleTestModel.getStringValue());
    }

}
