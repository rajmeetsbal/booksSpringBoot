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
		Optional<BookRecomendation> br = bookRecomendationRepository.findById(bookRecomendation.getId());
		if(br.isPresent()) {
			BookRecomendation alreadyRecommended = br.get();
			List<String> recs = alreadyRecommended.getRecommendedBy();
			String recommendedBy = bookRecomendation.getRecommendedBy().get(0);
			if(recs.contains(recommendedBy)){
				throw new AlreadyRecommendedException("you already recommended this book");
			} else {
				recs.add(recommendedBy);
				alreadyRecommended.setRecommendedBy(recs);
				BookRecomendation createdRecomendation = bookRecomendationRepository.save(alreadyRecommended);
				if(createdRecomendation != null) {
					return createdRecomendation;
				} else {
					throw new RecommendationNotCreatedException("Recommendation not created.");
				}
			}
		}else {
			BookRecomendation createdRecomendation = bookRecomendationRepository.insert(bookRecomendation);
			if(createdRecomendation != null) {
				return createdRecomendation;
			} else {
				throw new RecommendationNotCreatedException("Recommendation not created.");
			}
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

	@Override
	public List<BookRecomendation> getAllRecommended() throws RecommendationsNotFoundException {
		return bookRecomendationRepository.findAll();
	}
	
	
}
