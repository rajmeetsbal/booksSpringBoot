package com.stackroute.bookrecommendationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookrecommendationservice.controller.BookRecommendationController;
import com.stackroute.bookrecommendationservice.exception.RecommendationsNotFoundException;
import com.stackroute.bookrecommendationservice.exception.RecommendationNotCreatedException;
import com.stackroute.bookrecommendationservice.model.BookRecomendation;
import com.stackroute.bookrecommendationservice.service.RecommendationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
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
public class RecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private BookRecomendation bookRecommendation;
    @MockBean
    private RecommendationService bookRecomendationService;
    @InjectMocks
    private BookRecommendationController bookRecomendationController;
    private List<BookRecomendation> allRecommendations = null;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookRecomendationController).build();
        bookRecommendation = new BookRecomendation();
        bookRecommendation.setId("0385472579");
        bookRecommendation.setBookTitle("Zen speaks");
        bookRecommendation.setBookAuthor("Zhizhong Cai");
        ArrayList<String> recBy = new ArrayList<String>();
        recBy.add("rajmeet");
        bookRecommendation.setRecommendedBy(recBy);

        allRecommendations = new ArrayList<>();
        allRecommendations.add(bookRecommendation);


    }

    @Test
    public void createBookRecomendationSuccess() throws Exception {

        when(bookRecomendationService.createRecommendation(any())).thenReturn(bookRecommendation);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/recommend")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookRecommendation)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getBookRecomendationByIdSuccess() throws Exception {
        when(bookRecomendationService.getRecommendationById(bookRecommendation.getId())).thenReturn(bookRecommendation);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommend/0385472579")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getBookRecomendationByIdFailure() throws Exception {
        when(bookRecomendationService.getRecommendationById(bookRecommendation.getId())).thenThrow(RecommendationsNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bookRecomendation/3854725790")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }



    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
