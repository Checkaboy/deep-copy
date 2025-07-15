package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.copyist.FieldCopyist;
import com.checkaboy.deepcopy.copyist.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.SubObjectCopyist;
import com.checkaboy.deepcopy.copyist.SubObjectCopyist1;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;
import com.checkaboy.deepcopy.model.Car;
import com.checkaboy.deepcopy.model.ETransmissionType;
import com.checkaboy.deepcopy.model.Engine;
import com.checkaboy.deepcopy.model.Transmission;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyTest {

    @Test
    public void carCopyTest() {
        IObjectCopyist<Car> carCopyist = new ObjectCopyist<>();

        carCopyist.put("carBrand", new FieldCopyist<>(Car::getCarBrand, Car::setCarBrand));
        carCopyist.put("model", new FieldCopyist<>(Car::getModel, Car::setModel));
        carCopyist.put("color", new FieldCopyist<>(Car::getColor, Car::setColor));
        carCopyist.put("doorCount", new FieldCopyist<>(Car::getDoorCount, Car::setDoorCount));

        {
            IObjectCopyist<Engine> engineCopyist = new ObjectCopyist<>();
            engineCopyist.put("horsePower", new FieldCopyist<>(Engine::getHorsePower, Engine::setCountCylinder));
            engineCopyist.put("volume", new FieldCopyist<>(Engine::getVolume, Engine::setVolume));
            engineCopyist.put("countCylinder", new FieldCopyist<>(Engine::getCountCylinder, Engine::setCountCylinder));
            SubObjectCopyist1<Car, Engine> subObjectCopyist = new SubObjectCopyist1<>(car -> {
                Engine engine = car.getEngine();
                if (engine == null) return new Engine();
                return engine;
            }, Car::setEngine, engineCopyist);
            carCopyist.put("engine", subObjectCopyist);
        }

        {
            IObjectCopyist<Transmission> transmissionComparator = new ObjectCopyist<>();
            transmissionComparator.put("countSteps", new FieldCopyist<>(Transmission::getCountSteps, Transmission::setCountSteps));
            transmissionComparator.put("transmissionType", new FieldCopyist<>(Transmission::getTransmissionType, Transmission::setTransmissionType));
            SubObjectCopyist<Car, Transmission> subObjectCopyist = new SubObjectCopyist<>(Car::getTransmission, Car::setTransmission, Transmission::new, transmissionComparator);
            carCopyist.put("transmission", subObjectCopyist);
        }

        Car sourceCar = createBmvI3();
        Car newCarCopy = new Car();

        carCopyist.copy(sourceCar, newCarCopy);

        Assert.assertEquals(sourceCar.getEngine().getCountCylinder(), newCarCopy.getEngine().getCountCylinder());
        Assert.assertNotEquals(sourceCar.getEngine(), newCarCopy.getEngine());
    }

    private Car createBmvI3() {
        Car car = new Car();
        car.setCarBrand("BMW");
        car.setModel("I3");
        car.setColor("Red");
        car.setDoorCount(5);
        {
            Engine engine = new Engine();
            engine.setCountCylinder(0);
            engine.setVolume(0);
            engine.setHorsePower(238);
            car.setEngine(engine);
        }
        {
            Transmission transmission = new Transmission();
            transmission.setTransmissionType(ETransmissionType.AUTOMATIC);
            transmission.setCountSteps(1);
            car.setTransmission(transmission);
        }

        return car;
    }

}
