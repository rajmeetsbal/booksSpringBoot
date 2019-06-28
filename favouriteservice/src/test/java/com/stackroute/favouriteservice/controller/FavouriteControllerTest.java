package com.stackroute.favouriteservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.controller.BookFavouriteController;
import com.stackroute.favouriteservice.exception.FavouriteNotCreatedException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.model.BookFavourite;
import com.stackroute.favouriteservice.service.BookFavouriteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FavouriteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private BookFavourite bookFavourite;
    @MockBean
    private BookFavouriteService bookFavouriteService;
    @InjectMocks
    private BookFavouriteController bookFavouriteController;
    private List<BookFavourite> allFavourites = null;
    private List<Book> allBooks = null;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookFavouriteController).build();
        bookFavourite = new BookFavourite();
        bookFavourite.setId("rajmeet");
        List<Book> favouritesList = new ArrayList<Book>();
        Book one = new Book();
        one.setId(5472579);
        String[] authors = {"Zhizhong Cai"};
        one.setAuthor_name(authors);
        one.setTitle("Zen speaks");
        favouritesList.add(one);
        bookFavourite.setFavouritesList(favouritesList);

        allBooks = new ArrayList<Book>();
        allBooks.add(one);
        allFavourites = new ArrayList<>();
        allFavourites.add(bookFavourite);


    }

//    @Test
//    public void createFavouriteSuccess() throws Exception {
//
//        when(bookFavouriteService.createFavourite(any())).thenReturn(bookFavourite);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favourites/rajmeet")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookFavourite)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void getFavouriteByIdSuccess() throws Exception {
//        when(bookFavouriteService.getAllFavoritesByUser(bookFavourite.getId())).thenReturn(allBooks);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favourites/user1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//
//    @Test
//    public void getFavouriteByIdFailure() throws Exception {
//        when(bookFavouriteService.getAllFavoritesByUser(bookFavourite.getId())).thenThrow(FavouriteNotCreatedException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favourites/user1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
    
    public void getFavs() {
    	assertEquals(allFavourites.get(0).getFavouritesList().get(0).getTitle(), "Zen speaks");
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
