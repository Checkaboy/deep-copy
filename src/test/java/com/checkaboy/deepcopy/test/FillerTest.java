package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.context.cache.Cache;
import com.checkaboy.deepcopy.filler.model.general.CollectionFiller;
import com.checkaboy.deepcopy.filler.model.general.FieldFiller;
import com.checkaboy.deepcopy.filler.model.general.ObjectFiller;
import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.filler.model.interf.IFieldFiller;
import com.checkaboy.deepcopy.filler.model.interf.IObjectFiller;
import com.checkaboy.deepcopy.model.book.dto.AuthorDto;
import com.checkaboy.deepcopy.model.book.dto.BookDto;
import com.checkaboy.deepcopy.model.book.entity.AuthorEntity;
import com.checkaboy.deepcopy.model.book.entity.BookEntity;
import com.checkaboy.deepcopy.model.car.Car;
import com.checkaboy.deepcopy.model.pet.Pet;
import com.checkaboy.deepcopy.transformer.model.CollectionTransformer;
import com.checkaboy.deepcopy.transformer.model.ObjectTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IFieldTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.IObjectTransformer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taras Shaptala
 */
public class FillerTest {

    @Test
    public void simpleTest() {
        Car car = new Car();
        Pet pet = new Pet();

        IFieldFiller<Car, Pet> adapter = FieldFiller.simpleFieldFiller(Car::getColor, Pet::setNickname);

        car.setColor("red");
        adapter.fill(null, car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void simpleTest2() {
        Car car = new Car();
        Pet pet = new Pet();

        IFieldFiller<Car, Pet> adapter = FieldFiller.simpleFieldFiller(Car::getColor, Pet::setNickname);

        car.setColor("red");
        adapter.fill(null, car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterTest() {
        IObjectFiller<Car, Pet> adapter = new ObjectFiller<>();

        adapter.put("color", FieldFiller.simpleFieldFiller(Car::getColor, Pet::setNickname));

        Car car = new Car();
        car.setColor("red");

        Pet pet = new Pet();

        adapter.fill(null, car, pet);

        Assert.assertEquals(car.getColor(), pet.getNickname());
    }

    @Test
    public void objectAdapterWithSubClassTest() {
        IObjectFiller<BookDto, BookEntity> bookDtoAdapter = new ObjectFiller<>();

        bookDtoAdapter.put("id", FieldFiller.simpleFieldFiller(BookDto::getId, BookEntity::setId));
        bookDtoAdapter.put("name", FieldFiller.simpleFieldFiller(BookDto::getName, BookEntity::setName));
        {
            IObjectFiller<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectFiller<>();

            authorObjectDtoAdapter.put("id", FieldFiller.simpleFieldFiller(AuthorDto::getId, AuthorEntity::setId));
            authorObjectDtoAdapter.put("firstName", FieldFiller.simpleFieldFiller(AuthorDto::getFirstName, AuthorEntity::setFirstName));
            authorObjectDtoAdapter.put("lastName", FieldFiller.simpleFieldFiller(AuthorDto::getLastName, AuthorEntity::setLastName));

            IFieldFiller<BookDto, BookEntity> authorFieldDtoAdapter = new FieldFiller<>(
                    BookDto::getAuthor,
                    BookEntity::setAuthor,
                    new ObjectTransformer<>(AuthorEntity::new, authorObjectDtoAdapter)
            );

            bookDtoAdapter.put("author", authorFieldDtoAdapter);
        }

        BookEntity bookEntity = new BookEntity();

        bookDtoAdapter.fill(null, createBook(), bookEntity);

        System.out.println(bookEntity.getAuthor().getFirstName());

        Assert.assertEquals(bookEntity.getAuthor().getFirstName(), "testFirstName");
    }

    @Test
    public void collectionAdapterTest() {
        IObjectFiller<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectFiller<>();

        authorObjectDtoAdapter.put("id", FieldFiller.simpleFieldFiller(AuthorDto::getId, AuthorEntity::setId));
        authorObjectDtoAdapter.put("firstName", FieldFiller.simpleFieldFiller(AuthorDto::getFirstName, AuthorEntity::setFirstName));
        authorObjectDtoAdapter.put("lastName", FieldFiller.simpleFieldFiller(AuthorDto::getLastName, AuthorEntity::setLastName));

        {
            IObjectFiller<BookDto, BookEntity> bookDtoAdapter = new ObjectFiller<>();

            bookDtoAdapter.put("id", FieldFiller.simpleFieldFiller(BookDto::getId, BookEntity::setId));
            bookDtoAdapter.put("name", FieldFiller.simpleFieldFiller(BookDto::getName, BookEntity::setName));

            ICollectionFiller<List<BookDto>, BookDto, List<BookEntity>, BookEntity> bookCollectionDtoAdapter = new CollectionFiller<>(
                    new ObjectTransformer<>(BookEntity::new, bookDtoAdapter)
            );

            authorObjectDtoAdapter.put("books", new FieldFiller<>(
                    AuthorDto::getBooks,
                    AuthorEntity::setBooks,
                    new CollectionTransformer<>(
                            ArrayList::new,
                            bookCollectionDtoAdapter
                    )
            ));
        }
        AuthorDto authorDto = createAuthor();
        authorDto.setBooks(createBooks());

        AuthorEntity authorEntity = new AuthorEntity();

        authorObjectDtoAdapter.fill(null, authorDto, authorEntity);

        Assert.assertEquals(authorEntity.getBooks().size(), 3);
    }

    @Test
    public void collectionAdapterTest2() {
        IObjectFiller<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectFiller<>();

        authorObjectDtoAdapter.put("id", FieldFiller.simpleFieldFiller(AuthorDto::getId, AuthorEntity::setId));
        authorObjectDtoAdapter.put("firstName", FieldFiller.simpleFieldFiller(AuthorDto::getFirstName, AuthorEntity::setFirstName));
        authorObjectDtoAdapter.put("lastName", FieldFiller.simpleFieldFiller(AuthorDto::getLastName, AuthorEntity::setLastName));

        {
            IObjectFiller<BookDto, BookEntity> bookDtoAdapter = new ObjectFiller<>();

            bookDtoAdapter.put("id", FieldFiller.simpleFieldFiller(BookDto::getId, BookEntity::setId));
            bookDtoAdapter.put("name", FieldFiller.simpleFieldFiller(BookDto::getName, BookEntity::setName));
            bookDtoAdapter.put("author", new FieldFiller<>(
                    BookDto::getAuthor,
                    BookEntity::setAuthor,
                    new ObjectTransformer<>(AuthorEntity::new, authorObjectDtoAdapter)
            ));

            ICollectionFiller<List<BookDto>, BookDto, List<BookEntity>, BookEntity> bookCollectionDtoAdapter = new CollectionFiller<>(
                    new ObjectTransformer<>(BookEntity::new, bookDtoAdapter)
            );

            authorObjectDtoAdapter.put("books", new FieldFiller<>(
                    AuthorDto::getBooks,
                    AuthorEntity::setBooks,
                    new CollectionTransformer<>(
                            ArrayList::new,
                            bookCollectionDtoAdapter
                    )
            ));
        }
        AuthorDto authorDto = createAuthor();
        authorDto.setBooks(createBooks());
        authorDto.getBooks().forEach(bookDto -> bookDto.setAuthor(authorDto));

        IObjectTransformer<AuthorDto, AuthorEntity> transformer = new ObjectTransformer<>(AuthorEntity::new, authorObjectDtoAdapter);
        AuthorEntity authorEntity = transformer.transform(new Cache(), authorDto);
        System.out.println(authorEntity.getId());
    }

    @Test
    public void collectionAdapterTest3() {
        IObjectFiller<AuthorDto, AuthorEntity> authorObjectDtoAdapter = new ObjectFiller<>();
        authorObjectDtoAdapter.put("id", FieldFiller.simpleFieldFiller(AuthorDto::getId, AuthorEntity::setId));
        authorObjectDtoAdapter.put("firstName", FieldFiller.simpleFieldFiller(AuthorDto::getFirstName, AuthorEntity::setFirstName));
        authorObjectDtoAdapter.put("lastName", FieldFiller.simpleFieldFiller(AuthorDto::getLastName, AuthorEntity::setLastName));

        IObjectFiller<BookDto, BookEntity> bookDtoAdapter = new ObjectFiller<>();
        bookDtoAdapter.put("id", FieldFiller.simpleFieldFiller(BookDto::getId, BookEntity::setId));
        bookDtoAdapter.put("name", FieldFiller.simpleFieldFiller(BookDto::getName, BookEntity::setName));

        bookDtoAdapter.put("author", new FieldFiller<>(
                BookDto::getAuthor,
                BookEntity::setAuthor,
                new ObjectTransformer<>(AuthorEntity::new, authorObjectDtoAdapter)
        ));

        IFieldTransformer<BookDto, BookEntity> bookTransformer = new ObjectTransformer<>(BookEntity::new, bookDtoAdapter);
        ICollectionFiller<List<BookDto>, BookDto, List<BookEntity>, BookEntity> bookCollectionDtoAdapter =
                new CollectionFiller<>(bookTransformer);

        authorObjectDtoAdapter.put("books", new FieldFiller<>(
                AuthorDto::getBooks,
                AuthorEntity::setBooks,
                new CollectionTransformer<>(ArrayList::new, bookCollectionDtoAdapter)
        ));

        IObjectTransformer<AuthorDto, AuthorEntity> transformer = new ObjectTransformer<>(AuthorEntity::new, authorObjectDtoAdapter);

        AuthorDto authorDto = createAuthor();
        authorDto.setBooks(createBooks());
        authorDto.getBooks().forEach(bookDto -> bookDto.setAuthor(authorDto));

        AuthorEntity authorEntity = transformer.transform(new Cache(), authorDto);
        System.out.println(authorEntity.getId());
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
