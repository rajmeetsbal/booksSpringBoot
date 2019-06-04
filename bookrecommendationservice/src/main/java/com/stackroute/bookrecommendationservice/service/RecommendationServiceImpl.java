package com.stackroute.bookrecommendationservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookrecommendationservice.exception.AlreadyRecommendedException;
import com.stackroute.bookrecommendationservice.exception.RecommendationNotCreatedException;
import com.stackroute.bookrecommendationservice.exception.RecommendationsNotFoundException;
import com.stackroute.bookrecommendationservice.model.BookRecomendation;
import com.stackroute.bookrecommendationservice.repository.BookRecomendationRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class RecommendationServiceImpl implements RecommendationService {

	private BookRecomendationRepository bookRecomendationRepository;
	
	@Autowired
	public RecommendationServiceImpl(BookRecomendationRepository bookRecomendationRepository) {
		super();
		this.bookRecomendationRepository = bookRecomendationRepository;
	}

	@Override
	public BookRecomendation createRecommendation(BookRecomendation bookRecomendation)
			throws AlreadyRecommendedException, RecommendationNotCreatedException {
		BookRecomendation createdRecomendation = bookRecomendationRepository.insert(bookRecomendation);
		if(createdRecomendation != null) {
			return createdRecomendation;
		} else {
			throw new RecommendationNotCreatedException("Recommendation not created.");
		}
	}

	@Override
	public BookRecomendation getRecommendationById(String id) throws RecommendationsNotFoundException {
		BookRecomendation bookRecomendation = null;
		try {
			Optional<BookRecomendation> currentBookRecomendation = bookRecomendationRepository.findById(id);
			if(currentBookRecomendation.isPresent()) {
				bookRecomendation = currentBookRecomendation.get();
			}
		} catch (Exception e) {
//			return 0;
			throw new RecommendationsNotFoundException("Recommendation not found");
		}
		return bookRecomendation;
		
	}
	
	
}
