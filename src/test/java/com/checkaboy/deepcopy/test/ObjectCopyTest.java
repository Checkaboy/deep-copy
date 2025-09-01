package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.cloner.ObjectCloner;
import com.checkaboy.deepcopy.copyist.FieldCopyist;
import com.checkaboy.deepcopy.copyist.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.IObjectCopyist;
import com.checkaboy.deepcopy.model.car.Car;
import com.checkaboy.deepcopy.model.car.ETransmissionType;
import com.checkaboy.deepcopy.model.car.Engine;
import com.checkaboy.deepcopy.model.car.Transmission;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyTest {

    @Test
    public void carCopyTest() {
        IObjectCopyist<Car> carCopyist = new ObjectCopyist<>();

        carCopyist.put("carBrand", FieldCopyist.simpleFieldCopyist(Car::getCarBrand, Car::setCarBrand));
        carCopyist.put("model", FieldCopyist.simpleFieldCopyist(Car::getModel, Car::setModel));
        carCopyist.put("color", FieldCopyist.simpleFieldCopyist(Car::getColor, Car::setColor));
        carCopyist.put("doorCount", FieldCopyist.simpleFieldCopyist(Car::getDoorCount, Car::setDoorCount));

        {
            IObjectCopyist<Engine> engineCopyist = new ObjectCopyist<>();
            engineCopyist.put("horsePower", FieldCopyist.simpleFieldCopyist(Engine::getHorsePower, Engine::setCountCylinder));
            engineCopyist.put("volume", FieldCopyist.simpleFieldCopyist(Engine::getVolume, Engine::setVolume));
            engineCopyist.put("countCylinder", FieldCopyist.simpleFieldCopyist(Engine::getCountCylinder, Engine::setCountCylinder));

            FieldCopyist<Car, Engine> copyist = new FieldCopyist<>(
                    Car::getEngine,
                    Car::setEngine,
                    new ObjectCloner<>(Engine::new, engineCopyist)
            );

            carCopyist.put("engine", copyist);
        }

        {
            IObjectCopyist<Transmission> objectCopyist = new ObjectCopyist<>();
            objectCopyist.put("countSteps", FieldCopyist.simpleFieldCopyist(Transmission::getCountSteps, Transmission::setCountSteps));
            objectCopyist.put("transmissionType", FieldCopyist.simpleFieldCopyist(Transmission::getTransmissionType, Transmission::setTransmissionType));

            FieldCopyist<Car, Transmission> copyist = new FieldCopyist<>(
                    Car::getTransmission,
                    Car::setTransmission,
                    new ObjectCloner<>(Transmission::new, objectCopyist)
            );

            carCopyist.put("transmission", copyist);
        }

        Car sourceCar = createBmvI3();
        Car newCarCopy = new Car();

        carCopyist.copy(sourceCar, newCarCopy);

        Assert.assertEquals(sourceCar.getEngine().getCountCylinder(), newCarCopy.getEngine().getCountCylinder());
        Assert.assertNotEquals(sourceCar.getEngine(), newCarCopy.getEngine());

        Assert.assertEquals(sourceCar.getTransmission().getTransmissionType(), newCarCopy.getTransmission().getTransmissionType());
        Assert.assertNotEquals(sourceCar.getTransmission(), newCarCopy.getTransmission());
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
