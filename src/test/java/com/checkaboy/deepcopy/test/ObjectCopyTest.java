package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.cloner.Cloner;
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

        carCopyist.put("carBrand", FieldCopyist.simpleCopyist(Car::getCarBrand, Car::setCarBrand));
        carCopyist.put("model", FieldCopyist.simpleCopyist(Car::getModel, Car::setModel));
        carCopyist.put("color", FieldCopyist.simpleCopyist(Car::getColor, Car::setColor));
        carCopyist.put("doorCount", FieldCopyist.simpleCopyist(Car::getDoorCount, Car::setDoorCount));

        {
            IObjectCopyist<Engine> engineCopyist = new ObjectCopyist<>();
            engineCopyist.put("horsePower", FieldCopyist.simpleCopyist(Engine::getHorsePower, Engine::setCountCylinder));
            engineCopyist.put("volume", FieldCopyist.simpleCopyist(Engine::getVolume, Engine::setVolume));
            engineCopyist.put("countCylinder", FieldCopyist.simpleCopyist(Engine::getCountCylinder, Engine::setCountCylinder));
            SubObjectCopyist1<Car, Engine> subObjectCopyist = new SubObjectCopyist1<>(car -> {
                Engine engine = car.getEngine();
                if (engine == null) return new Engine();
                return engine;
            }, Car::setEngine, engineCopyist);
            carCopyist.put("engine", subObjectCopyist);
        }

        {
            FieldCopyist<Car, Transmission> copyist = new FieldCopyist<>(
                    Car::getTransmission,
                    Car::setTransmission,
                    new Cloner<>(source -> {
                        Transmission transmission = new Transmission();

                        IObjectCopyist<Transmission> objectCopyist = new ObjectCopyist<>();
                        objectCopyist.put("countSteps", FieldCopyist.simpleCopyist(Transmission::getCountSteps, Transmission::setCountSteps));
                        objectCopyist.put("transmissionType", FieldCopyist.simpleCopyist(Transmission::getTransmissionType, Transmission::setTransmissionType));
                        objectCopyist.copy(source, transmission);

                        return transmission;
                    })
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
