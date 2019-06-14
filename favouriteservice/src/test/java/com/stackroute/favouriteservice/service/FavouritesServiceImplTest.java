package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.exception.FavouriteNotCreatedException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.model.BookFavourite;
import com.stackroute.favouriteservice.model.UserBook;
import com.stackroute.favouriteservice.repository.BookFavoriteRepository;
import com.stackroute.favouriteservice.service.BookFavouriteServiceImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;

import javax.validation.constraints.AssertTrue;

public class FavouritesServiceImplTest {

    private BookFavourite bookFavourite;
    private UserBook userBook;
    @Mock
    private BookFavoriteRepository repository;
    @InjectMocks
    private BookFavouriteServiceImpl favouriteServiceImpl;
    private List<BookFavourite> allFavourites = null;
    Optional<BookFavourite> options;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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
        userBook = new UserBook();
        userBook.setBook(one);
        userBook.setId("user1");

        allFavourites = new ArrayList<>();
        allFavourites.add(bookFavourite);
        
        
        options = Optional.of(bookFavourite);
    }
    
    @After
    public void tearDown() throws Exception {
        repository.deleteById("user1");
    }

    @Test
    public void createRecommendationTestSuccess() throws Exception {
        when(repository.insert((BookFavourite) any())).thenReturn(bookFavourite);
        BookFavourite savedFavourite = favouriteServiceImpl.createFavourite(userBook);
        Assert.assertEquals(bookFavourite, savedFavourite);
//        BookFavourite savedRecomendation = recommendationServiceImpl.createRecommendation(bookFavourite);
    	assertTrue(true);
    }

    @Test(expected = FavouriteNotCreatedException.class)
    public void createRecommendationTestFailure() throws Exception {
        when(repository.insert((BookFavourite) any())).thenReturn(null);
        BookFavourite savedFavourite = favouriteServiceImpl.createFavourite(userBook);
        Assert.assertEquals(bookFavourite, savedFavourite);
    }
}
