package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.filler.model.general.FieldFiller;
import com.checkaboy.deepcopy.filler.model.general.ObjectFiller;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.model.car.Car;
import com.checkaboy.deepcopy.model.car.ETransmissionType;
import com.checkaboy.deepcopy.model.car.Engine;
import com.checkaboy.deepcopy.model.car.Transmission;
import com.checkaboy.deepcopy.transformer.model.ObjectTransformer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Taras Shaptala
 */
public class ObjectCopyTest {

    @Test
    public void carCopyTest() {
        IObjectFiller<Car, Car> carCopyist = new ObjectFiller<>();

        carCopyist.put("carBrand", FieldFiller.simpleFieldFiller(Car::getCarBrand, Car::setCarBrand));
        carCopyist.put("model", FieldFiller.simpleFieldFiller(Car::getModel, Car::setModel));
        carCopyist.put("color", FieldFiller.simpleFieldFiller(Car::getColor, Car::setColor));
        carCopyist.put("doorCount", FieldFiller.simpleFieldFiller(Car::getDoorCount, Car::setDoorCount));

        {
            IObjectFiller<Engine, Engine> engineCopyist = new ObjectFiller<>();
            engineCopyist.put("horsePower", FieldFiller.simpleFieldFiller(Engine::getHorsePower, Engine::setCountCylinder));
            engineCopyist.put("volume", FieldFiller.simpleFieldFiller(Engine::getVolume, Engine::setVolume));
            engineCopyist.put("countCylinder", FieldFiller.simpleFieldFiller(Engine::getCountCylinder, Engine::setCountCylinder));

            IFieldFiller<Car, Car> copyist = new FieldFiller<>(
                    Car::getEngine,
                    Car::setEngine,
                    new ObjectTransformer<>(Engine::new, engineCopyist)
            );

            carCopyist.put("engine", copyist);
        }

        {
            IObjectFiller<Transmission, Transmission> objectCopyist = new ObjectFiller<>();
            objectCopyist.put("countSteps", FieldFiller.simpleFieldFiller(Transmission::getCountSteps, Transmission::setCountSteps));
            objectCopyist.put("transmissionType", FieldFiller.simpleFieldFiller(Transmission::getTransmissionType, Transmission::setTransmissionType));

            IFieldFiller<Car, Car> copyist = new FieldFiller<>(
                    Car::getTransmission,
                    Car::setTransmission,
                    new ObjectTransformer<>(Transmission::new, objectCopyist)
            );

            carCopyist.put("transmission", copyist);
        }

        Car sourceCar = createBmvI3();
        Car newCarCopy = new Car();

        carCopyist.fill(sourceCar, newCarCopy);

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
