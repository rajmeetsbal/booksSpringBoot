package com.stackroute.bookrecommendationservice.repository;

import com.stackroute.bookrecommendationservice.model.BookRecomendation;
import com.stackroute.bookrecommendationservice.repository.BookRecomendationRepository;
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
public class RecommendationsRepositoryTest {

    @Autowired
    private BookRecomendationRepository repository;
    private BookRecomendation bookRecommendation;

    @Before
    public void setUp() throws Exception {
        bookRecommendation = new BookRecomendation();
        bookRecommendation.setId("0385472579");
        bookRecommendation.setBookTitle("Zen speaks");
        bookRecommendation.setBookAuthor("Zhizhong Cai");
        ArrayList<String> recBy = new ArrayList<String>();
        recBy.add("rajmeet");
        bookRecommendation.setRecommendedBy(recBy);
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteById("0385472579");
    }

    @Test
    public void createRecommendationTest() {

        repository.insert(bookRecommendation);
        BookRecomendation br = repository.findById(bookRecommendation.getId()).get();
        Assert.assertEquals("0385472579", br.getId());

    }

    @Test
    public void getAllRecommendedTest() {
        repository.insert(bookRecommendation);
//        bookRecommendation = new BookRecomendation();
//        bookRecommendation.setId("9780140219180");
//        bookRecommendation.setBookTitle("Europe since 1870");
//        bookRecommendation.setBookAuthor("James Joll");
//        ArrayList<String> recBy = new ArrayList<String>();
//        recBy.add("rajmeet");
//        bookRecommendation.setRecommendedBy(recBy);
//        repository.insert(bookRecommendation);
        List<BookRecomendation> bookRecommendation = repository.findAll();
        assertTrue(bookRecommendation.size() > 0);
    }
}
