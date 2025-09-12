package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.filler.model.general.FieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
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
        IFieldFiller<SimpleTestModel, SimpleTestModel> stringFieldCopyist = FieldFiller.simpleFieldFiller(SimpleTestModel::getStringValue, SimpleTestModel::setStringValue);

        String value = "String data";
        SimpleTestModel sourceSimpleTestModel = new SimpleTestModel().setStringValue(value);
        SimpleTestModel targetSimpleTestModel = new SimpleTestModel();
        stringFieldCopyist.fill(null, sourceSimpleTestModel, targetSimpleTestModel);
        Assert.assertEquals(value, targetSimpleTestModel.getStringValue());
    }

}
