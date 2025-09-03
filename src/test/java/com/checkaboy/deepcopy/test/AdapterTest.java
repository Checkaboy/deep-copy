package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.adapter.FieldAdapter;
import com.checkaboy.deepcopy.adapter.ObjectAdapter;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.adapter.interf.IObjectAdapter;
import com.checkaboy.deepcopy.model.car.Car;
import com.checkaboy.deepcopy.model.pet.Pet;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class AdapterTest {

    @Test
    public void simpleTest() {
        Car car = new Car();
        Pet pet = new Pet();

        IFieldAdapter<Car, Pet> adapter = FieldAdapter.simpleFieldCopyist(Car::getColor, Pet::setNickname);

        car.setColor("red");
        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterTest() {
        IObjectAdapter<Car, Pet> adapter = new ObjectAdapter<>(Pet::new);

        adapter.put("color", FieldAdapter.simpleFieldCopyist(Car::getColor, Pet::setNickname));

        Car car = new Car();
        car.setColor("red");

        Pet pet = adapter.clone(car);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

}
