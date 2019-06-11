package com.stackroute.favouriteservice.service;


import java.util.List;

import com.stackroute.favouriteservice.exception.AlreadyFavouriteException;
import com.stackroute.favouriteservice.exception.FavouriteNotCreatedException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.model.BookFavourite;
import com.stackroute.favouriteservice.model.UserBook;

public interface BookFavouriteService {
//    BookFavourite createFavourite(BookFavourite bookRecomendations) throws AlreadyFavouriteException, FavouriteNotCreatedException;
    List<Book> getAllFavoritesByUser(String userId);
	BookFavourite createFavourite(UserBook userBook) throws AlreadyFavouriteException, FavouriteNotCreatedException;
}
