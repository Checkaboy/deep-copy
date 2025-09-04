package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.adapter.FieldAdapter;
import com.checkaboy.deepcopy.adapter.FieldAdapter2;
import com.checkaboy.deepcopy.adapter.ObjectAdapter;
import com.checkaboy.deepcopy.adapter.ObjectFieldAdapter;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter2;
import com.checkaboy.deepcopy.adapter.interf.IObjectAdapter;
import com.checkaboy.deepcopy.model.book.dto.AuthorDto;
import com.checkaboy.deepcopy.model.book.dto.BookDto;
import com.checkaboy.deepcopy.model.book.entity.AuthorEntity;
import com.checkaboy.deepcopy.model.book.entity.BookEntity;
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

//        IFieldAdapter<Car, Pet> adapter = FieldAdapter.simpleFieldAdapter(Car::getColor, Pet::setNickname);
//
//        car.setColor("red");
//        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void simpleTest2() {
        Car car = new Car();
        Pet pet = new Pet();

        IFieldAdapter<Car, Pet> adapter = new FieldAdapter2<>(
                Car::getColor,
                s -> s,
                (source, target) -> {},
                Pet::setNickname
        );

        car.setColor("red");
        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterTest() {
        IObjectAdapter<Car, Pet> adapter = new ObjectAdapter<>();

        adapter.put("color", new FieldAdapter2<>(Car::getColor, s -> s, (source, target) -> {}, Pet::setNickname));

        Car car = new Car();
        car.setColor("red");

        Pet pet = new Pet();

        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterWithSubClassTest() {
        IObjectAdapter<BookDto, BookEntity> bookDtoAdapter = new ObjectAdapter<>();

        bookDtoAdapter.put("id", new FieldAdapter2<>(BookDto::getId, o -> o, (source, target) -> {}, BookEntity::setId));
        bookDtoAdapter.put("name", new FieldAdapter2<>(BookDto::getName, o -> o, (source, target) -> {}, BookEntity::setName));
        {
            IObjectAdapter<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectAdapter<>();

            FieldAdapter2<BookDto, BookEntity, AuthorDto, AuthorEntity> authorFieldDtoAdapter = new FieldAdapter2<>(
                    BookDto::getAuthor,
                    AuthorEntity::new,
                    authorObjectDtoAdapter,
                    BookEntity::setAuthor
            );

            bookDtoAdapter.put("author", authorFieldDtoAdapter);
        }


    }

}
