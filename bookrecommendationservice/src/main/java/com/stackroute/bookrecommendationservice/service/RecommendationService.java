package com.stackroute.bookrecommendationservice.service;

import java.util.List;

import com.stackroute.bookrecommendationservice.exception.AlreadyRecommendedException;
import com.stackroute.bookrecommendationservice.exception.RecommendationNotCreatedException;
import com.stackroute.bookrecommendationservice.exception.RecommendationsNotFoundException;
import com.stackroute.bookrecommendationservice.model.BookRecomendation;

public interface RecommendationService {
    BookRecomendation createRecommendation(BookRecomendation bookRecomendations) throws AlreadyRecommendedException, RecommendationNotCreatedException;
    BookRecomendation getRecommendationById(String bookId) throws RecommendationsNotFoundException;
    List<BookRecomendation> getAllRecommended() throws RecommendationsNotFoundException;
}
