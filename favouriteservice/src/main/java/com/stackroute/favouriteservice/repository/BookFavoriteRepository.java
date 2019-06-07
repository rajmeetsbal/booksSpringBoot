package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.model.BookFavourite;

/*
* This class is implementing the MongoRepository interface for User.
* Annotate this class with @Repository annotation
* */
@Repository
public interface BookFavoriteRepository extends MongoRepository<BookFavourite, String> {

//	List<BookRecomendation> findAllBookRecomendationByBookRecomendationRecommendedBy(String recommendedBy);
}
