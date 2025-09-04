package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.adapter.FieldAdapter;
import com.checkaboy.deepcopy.adapter.ObjectAdapter;
import com.checkaboy.deepcopy.adapter.interf.IFieldAdapter;
import com.checkaboy.deepcopy.adapter.interf.IObjectAdapter;
import com.checkaboy.deepcopy.model.book.dto.AuthorDto;
import com.checkaboy.deepcopy.model.book.dto.BookDto;
import com.checkaboy.deepcopy.model.book.entity.AuthorEntity;
import com.checkaboy.deepcopy.model.book.entity.BookEntity;
import com.checkaboy.deepcopy.model.car.Car;
import com.checkaboy.deepcopy.model.pet.Pet;
import com.checkaboy.deepcopy.transformer.ObjectTransformer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class AdapterTest {

    @Test
    public void simpleTest() {
        Car car = new Car();
        Pet pet = new Pet();

        IFieldAdapter<Car, Pet> adapter = FieldAdapter.simpleFieldAdapter(Car::getColor, Pet::setNickname);

        car.setColor("red");
        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void simpleTest2() {
        Car car = new Car();
        Pet pet = new Pet();

        IFieldAdapter<Car, Pet> adapter = FieldAdapter.simpleFieldAdapter(Car::getColor, Pet::setNickname);

        car.setColor("red");
        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterTest() {
        IObjectAdapter<Car, Pet> adapter = new ObjectAdapter<>();

        adapter.put("color", FieldAdapter.simpleFieldAdapter(Car::getColor, Pet::setNickname));

        Car car = new Car();
        car.setColor("red");

        Pet pet = new Pet();

        adapter.copy(car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterWithSubClassTest() {
        IObjectAdapter<BookDto, BookEntity> bookDtoAdapter = new ObjectAdapter<>();

        bookDtoAdapter.put("id", FieldAdapter.simpleFieldAdapter(BookDto::getId, BookEntity::setId));
        bookDtoAdapter.put("name", FieldAdapter.simpleFieldAdapter(BookDto::getName, BookEntity::setName));
        {
            IObjectAdapter<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectAdapter<>();

            authorObjectDtoAdapter.put("id", FieldAdapter.simpleFieldAdapter(AuthorDto::getId, AuthorEntity::setId));
            authorObjectDtoAdapter.put("firstName", FieldAdapter.simpleFieldAdapter(AuthorDto::getFirstName, AuthorEntity::setFirstName));
            authorObjectDtoAdapter.put("lastName", FieldAdapter.simpleFieldAdapter(AuthorDto::getLastName, AuthorEntity::setLastName));

            IFieldAdapter<BookDto, BookEntity> authorFieldDtoAdapter = new FieldAdapter<>(
                    BookDto::getAuthor,
                    BookEntity::setAuthor,
                    new ObjectTransformer<>(AuthorEntity::new, authorObjectDtoAdapter)
            );

            bookDtoAdapter.put("author", authorFieldDtoAdapter);
        }

        BookEntity bookEntity = new BookEntity();

        bookDtoAdapter.copy(createBook(), bookEntity);

        System.out.println(bookEntity.getAuthor().getFirstName());

        Assert.assertEquals(bookEntity.getAuthor().getFirstName(), "testFirstName");
    }

    @Test
    public void collectionAdapterTest() {
        /*
        IObjectAdapter<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectAdapter<>();

        authorObjectDtoAdapter.put("id", FieldAdapter.simpleFieldAdapter(AuthorDto::getId, AuthorEntity::setId));
        authorObjectDtoAdapter.put("firstName", FieldAdapter.simpleFieldAdapter(AuthorDto::getFirstName, AuthorEntity::setFirstName));
        authorObjectDtoAdapter.put("lastName", FieldAdapter.simpleFieldAdapter(AuthorDto::getLastName, AuthorEntity::setLastName));

        {
            IObjectAdapter<BookDto, BookEntity> bookDtoAdapter = new ObjectAdapter<>();

            bookDtoAdapter.put("id", FieldAdapter.simpleFieldAdapter(BookDto::getId, BookEntity::setId));
            bookDtoAdapter.put("name", FieldAdapter.simpleFieldAdapter(BookDto::getName, BookEntity::setName));

            ICollectionAdapter<List<BookDto>, BookDto, List<BookEntity>, BookEntity> bookCollectionDtoAdapter = new CollectionAdapter<>(
                    BookEntity::new,
                    bookDtoAdapter
            );

            authorObjectDtoAdapter.put("books", new FieldAdapter<>(
                    AuthorDto::getBooks,
                    () -> new ArrayList<>(),
                    bookCollectionDtoAdapter,
                    AuthorEntity::setBooks
            ));
        }
        AuthorDto authorDto = createAuthor();
        authorDto.setBooks(createBooks());

        AuthorEntity authorEntity = new AuthorEntity();

        authorObjectDtoAdapter.copy(authorDto, authorEntity);

        Assert.assertEquals(authorEntity.getBooks().size(), 3);

         */
    }

    public BookDto createBook() {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setName("test name");
        book.setAuthor(createAuthor());
        return book;
    }

    public AuthorDto createAuthor() {
        AuthorDto author = new AuthorDto();
        author.setId(1L);
        author.setFirstName("testFirstName");
        author.setLastName("testLastName");
        return author;
    }

    public List<BookDto> createBooks() {
        int bookCount = 3;
        List<BookDto> books = new ArrayList<>(bookCount);

        for (int i = 0; i < bookCount; i++) {
            BookDto book = new BookDto();
            book.setId((long) i);
            book.setName("test name " + i);
            books.add(book);
        }

        return books;
    }

}
