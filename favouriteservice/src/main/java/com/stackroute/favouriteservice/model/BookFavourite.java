package com.stackroute.favouriteservice.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
public class BookFavourite {

	@Id
	private String userId;
	private List<Book> favouritesList;
	 
	public BookFavourite() {
	}
	
	
	
	public BookFavourite(String id, List<Book> favouritesList) {
		super();
		this.userId = id;
		this.favouritesList = favouritesList;
	}



	public String getId() {
		return userId;
	}



	public void setId(String id) {
		this.userId = id;
	}



	public List<Book> getFavouritesList() {
		return favouritesList;
	}



	public void setFavouritesList(List<Book> favouritesList) {
		this.favouritesList = favouritesList;
	}
	
	
	


}
