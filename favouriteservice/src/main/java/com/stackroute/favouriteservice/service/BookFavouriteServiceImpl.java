package com.stackroute.favouriteservice.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.favouriteservice.exception.AlreadyFavouriteException;
import com.stackroute.favouriteservice.exception.FavouriteNotCreatedException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.model.BookFavourite;
import com.stackroute.favouriteservice.model.UserBook;
import com.stackroute.favouriteservice.repository.BookFavoriteRepository;

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
public class BookFavouriteServiceImpl implements BookFavouriteService {

	private BookFavoriteRepository bookFavouriteRepository;
	
	@Autowired
	public BookFavouriteServiceImpl(BookFavoriteRepository bookFavouriteRepository) {
		super();
		this.bookFavouriteRepository = bookFavouriteRepository;
	}

	@Override
	public BookFavourite createFavourite(UserBook userBook)
			throws AlreadyFavouriteException, FavouriteNotCreatedException {
		String userId = userBook.getId();
		Optional<BookFavourite> favourites = bookFavouriteRepository.findById(userId);
		if(favourites.isPresent()) {
			List<Book> favouritesList = favourites.get().getFavouritesList();
			favouritesList.add(userBook.getBook());
			BookFavourite bookFavourite = new BookFavourite();
			bookFavourite.setId(userId);
			bookFavourite.setFavouritesList(favouritesList);
			BookFavourite createdRecomendation = bookFavouriteRepository.save(bookFavourite);
			if(createdRecomendation != null) {
				return createdRecomendation;
			} else {
				throw new FavouriteNotCreatedException("Favourite not created.");
			}
		}else {
			List<Book> favouritesList = new ArrayList<Book>();
			favouritesList.add(userBook.getBook());
			BookFavourite bookFavourite = new BookFavourite();
			bookFavourite.setId(userId);
			bookFavourite.setFavouritesList(favouritesList);
			BookFavourite createdFav = bookFavouriteRepository.insert(bookFavourite);
			if(createdFav != null) {
				return createdFav;
			} else {
				throw new FavouriteNotCreatedException("Favourite not created.");
			}
		}
	}

//	@Override
//	public List<BookFavourite> getAllFavorites(String userId) {
//		return bookFavouriteRepository.findAllById(userId);
//	}
	


	/*
	 * This method should be used to get all notes with specific userId.
	 */
	public List<Book> getAllFavoritesByUser(String userId) {
		List<Book> favouritesList = null;
		
		Optional<BookFavourite> favourites = bookFavouriteRepository.findById(userId);
		if(favourites.isPresent()) {
			favouritesList = favourites.get().getFavouritesList();
		}
		return favouritesList;
	}
	
}
