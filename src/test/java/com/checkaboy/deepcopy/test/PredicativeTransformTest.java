package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.filler.model.general.ObjectFiller;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.model.car.Car;
import com.checkaboy.deepcopy.transformer.model.ObjectTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IObjectTransformer;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class PredicativeTransformTest {

    @Test
    public void simplePredicativeTransformTest() {
        IObjectFiller<Car, Car> objectCopyist = new ObjectFiller<>();

        IObjectTransformer<Car, Car> cloner = new ObjectTransformer<>(Car::new, objectCopyist);
    }

}
