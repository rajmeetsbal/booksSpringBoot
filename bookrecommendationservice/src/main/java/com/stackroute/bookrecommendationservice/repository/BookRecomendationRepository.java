package com.stackroute.bookrecommendationservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.bookrecommendationservice.model.BookRecomendation;

/*
* This class is implementing the MongoRepository interface for User.
* Annotate this class with @Repository annotation
* */
@Repository
public interface BookRecomendationRepository extends MongoRepository<BookRecomendation, String> {

//	List<BookRecomendation> findAllBookRecomendationByBookRecomendationRecommendedBy(String recommendedBy);
}
