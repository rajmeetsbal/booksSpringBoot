package com.stackroute.bookrecommendationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.stackroute.bookrecommendationservice.exception.RecommendationNotCreatedException;
import com.stackroute.bookrecommendationservice.exception.RecommendationsNotFoundException;
import com.stackroute.bookrecommendationservice.model.BookRecomendation;
import com.stackroute.bookrecommendationservice.service.RecommendationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:8765", maxAge = 3600)
@CrossOrigin(origins= {"http://localhost:4200", "http://localhost:4200/*"})
@EnableFeignClients("com.stackroute.bookrecommendationservice.controller")
@RibbonClient(name="book-recomendations")
@Api
public class BookRecommendationController {
	private RecommendationService recommendationService;
	
	@Autowired
	public BookRecommendationController(RecommendationService recommendationService) {
		super();
		this.recommendationService = recommendationService;
	}
	
	@ApiOperation(value = "Create recommendation using post api url: /api/v1/recommend")
	@PostMapping("/recommend")
	public ResponseEntity<?> createBookRecomendation(@RequestBody BookRecomendation bookRecomendation) throws Exception {
		try {
			BookRecomendation createdBookRecomendation = this.recommendationService.createRecommendation(bookRecomendation);
			return new ResponseEntity<>(createdBookRecomendation, HttpStatus.CREATED);
		} catch (RecommendationNotCreatedException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ApiOperation(value = "Get recommendation using get api url: /api/v1/recommend/id")
	@GetMapping("/recommend/{id}")
	public ResponseEntity<?> getBookRecomendationById(@PathVariable("id") String bookId) {
		try {
			BookRecomendation recommendationExist = this.recommendationService.getRecommendationById(bookId);
			if(recommendationExist != null) {
				return new ResponseEntity<>(recommendationExist,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (RecommendationsNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@ApiOperation(value = "Get all recommended books list: /api/v1/recommended")
	@GetMapping("/recommended")
	public ResponseEntity<?> getAllRecommended() {
		try {
			return new ResponseEntity<>(this.recommendationService.getAllRecommended(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@GetMapping("/")
	@RequestMapping(method = RequestMethod.GET, value="/")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}
}
