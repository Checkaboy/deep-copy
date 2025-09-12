package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.filler.model.general.ObjectFiller;
import com.checkaboy.deepcopy.model.car.Car;
import com.checkaboy.deepcopy.model.pet.Pet;
import com.checkaboy.deepcopy.transformer.model.ObjectTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IObjectTransformer;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class ClassCastTest {

    @Test
    public void classTest() {
        IObjectTransformer<Car, Car> carObjectTransformer = new ObjectTransformer<>(Car::new, new ObjectFiller<>());
        System.out.println(carObjectTransformer);

        IObjectTransformer<Pet, Pet> petObjectTransformer = new ObjectTransformer<>(Pet::new, new ObjectFiller<>());
        System.out.println(petObjectTransformer);
    }

}
