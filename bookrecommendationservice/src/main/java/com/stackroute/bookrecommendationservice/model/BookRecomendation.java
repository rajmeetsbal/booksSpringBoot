package com.stackroute.bookrecommendationservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
public class BookRecomendation {

	@Id
	private String id;
	private String bookTitle;
	private String bookAuthor;
	private String[] recommendedBy;
	 
	public BookRecomendation() {
	}
	
	
	
	public BookRecomendation(String id, String bookTitle, String bookAuthor, String[] recommendedBy) {
		super();
		this.id = id;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.recommendedBy = recommendedBy;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getBookTitle() {
		return bookTitle;
	}



	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}



	public String getBookAuthor() {
		return bookAuthor;
	}



	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}



	public String[] getRecommendedBy() {
		return recommendedBy;
	}



	public void setRecommendedBy(String[] recommendedBy) {
		this.recommendedBy = recommendedBy;
	}
	
	

}
