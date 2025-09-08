package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.cloner.ObjectCloner;
import com.checkaboy.deepcopy.cloner.interf.IObjectCloner;
import com.checkaboy.deepcopy.copyist.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;
import com.checkaboy.deepcopy.model.car.Car;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class PredicativeTransformTest {

    @Test
    public void simplePredicativeTransformTest() {
        IObjectCopyist<Car> objectCopyist = new ObjectCopyist<>();

        IObjectCloner<Car> cloner = new ObjectCloner<>(Car::new, objectCopyist);

    }

}
