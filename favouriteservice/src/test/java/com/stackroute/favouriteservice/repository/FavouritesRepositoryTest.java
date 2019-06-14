package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.repository.BookFavoriteRepository;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.model.BookFavourite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavouritesRepositoryTest {

    @Autowired
    private BookFavoriteRepository repository;
    private BookFavourite bookFavourite;

    @Before
    public void setUp() throws Exception {
        bookFavourite = new BookFavourite();
        bookFavourite.setId("user1");
        List<Book> favouritesList = new ArrayList<Book>();
        Book one = new Book();
        one.setId(5472579);
        String[] authors = {"Zhizhong Cai"};
        one.setAuthor_name(authors);
        one.setTitle("Zen speaks");
        favouritesList.add(one);
        bookFavourite.setFavouritesList(favouritesList);
        
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteById("user1");
    }

    @Test
    public void createFavouriteTest() {
        repository.insert(bookFavourite);
        BookFavourite bf = repository.findById(bookFavourite.getId()).get();
        Assert.assertEquals("user1", bf.getId());

    }

    @Test
    public void getAllFavouritesTest() {
        repository.insert(bookFavourite);
        List<BookFavourite> bookFavourite = repository.findAll();
        assertTrue(bookFavourite.size() > 0);
    }
}
