package com.stackroute.favouriteservice.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
public class UserBook {

	@Id
	private String userId;
	private Book book;
	 
	public UserBook() {
	}
	
	
	
	public UserBook(String id, Book book) {
		super();
		this.userId = id;
		this.book = book;
	}



	public String getId() {
		return userId;
	}



	public void setId(String id) {
		this.userId = id;
	}



	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
	}

	

//	public List<Book> getFavouritesList() {
//		return favouritesList;
//	}
//
//
//
//	public void setFavouritesList(List<Book> favouritesList) {
//		this.favouritesList = favouritesList;
//	}
	
	
	


}
