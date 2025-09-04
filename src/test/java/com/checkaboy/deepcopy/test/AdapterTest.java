package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.adapter.FieldAdapter2;
import com.checkaboy.deepcopy.adapter.ObjectAdapter;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
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
                (source, target) -> {
                },
                Pet::setNickname
        );

        car.setColor("red");
        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterTest() {
        IObjectAdapter<Car, Pet> adapter = new ObjectAdapter<>();

        adapter.put("color", new FieldAdapter2<>(Car::getColor, s -> s, (source, target) -> {
        }, Pet::setNickname));

        Car car = new Car();
        car.setColor("red");

        Pet pet = new Pet();

        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterWithSubClassTest() {
        IObjectAdapter<BookDto, BookEntity> bookDtoAdapter = new ObjectAdapter<>();

        bookDtoAdapter.put("id", FieldAdapter2.simpleFieldAdapter(BookDto::getId, BookEntity::setId));
        bookDtoAdapter.put("name", FieldAdapter2.simpleFieldAdapter(BookDto::getName, BookEntity::setName));
        {
            IObjectAdapter<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectAdapter<>();

            authorObjectDtoAdapter.put("id", FieldAdapter2.simpleFieldAdapter(AuthorDto::getId, AuthorEntity::setId));
            authorObjectDtoAdapter.put("firstName", FieldAdapter2.simpleFieldAdapter(AuthorDto::getFirstName, AuthorEntity::setFirstName));
            authorObjectDtoAdapter.put("lastName", FieldAdapter2.simpleFieldAdapter(AuthorDto::getLastName, AuthorEntity::setLastName));

            FieldAdapter2<BookDto, BookEntity, AuthorDto, AuthorEntity> authorFieldDtoAdapter = new FieldAdapter2<>(
                    BookDto::getAuthor,
                    AuthorEntity::new,
                    authorObjectDtoAdapter,
                    BookEntity::setAuthor
            );

            bookDtoAdapter.put("author", authorFieldDtoAdapter);
        }

        BookEntity bookEntity = new BookEntity();

        bookDtoAdapter.copy(createBook(), bookEntity);

        System.out.println(bookEntity.getAuthor().getFirstName());
    }

    public BookDto createBook() {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setName("test name");
        {
            AuthorDto author = new AuthorDto();
            author.setId(1L);
            author.setFirstName("testFirstName");
            author.setLastName("testLastName");
            book.setAuthor(author);
        }

        return book;
    }

}
