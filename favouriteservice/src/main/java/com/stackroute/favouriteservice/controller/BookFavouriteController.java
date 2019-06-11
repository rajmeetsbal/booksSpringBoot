package com.stackroute.favouriteservice.controller;

import java.util.List;

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

import com.stackroute.favouriteservice.exception.FavouriteNotCreatedException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.model.BookFavourite;
import com.stackroute.favouriteservice.model.UserBook;
import com.stackroute.favouriteservice.service.BookFavouriteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 * 
 * @CrossOrigin,@EnableFeignClients and @RibbonClient needs to be added 
 * 
 */
@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:8765", maxAge = 3600)
@CrossOrigin(origins= {"http://localhost:4200", "http://localhost:4200/*"})
@EnableFeignClients("com.stackroute.favouriteservice.controller")
@RibbonClient(name="book-favourites")
@Api
public class BookFavouriteController {

	/*
	 * Autowiring should be implemented for the BookFavouriteService. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword
	 */
	private BookFavouriteService favouriteService;
	
	@Autowired
	public BookFavouriteController(BookFavouriteService favouriteService) {
		super();
		this.favouriteService = favouriteService;
	}
	
	@ApiOperation(value = "Create favourite using post api url: /api/v1/favourites")
	@PostMapping("/favourites")
	public ResponseEntity<?> createFavourite(@RequestBody UserBook favourite) throws Exception {
		try {
			System.out.println("favourite "+favourite);
//			System.out.println("data : "+favourite.getId() + " " + favourite.getFavouritesList() );
			BookFavourite createdFavourite = this.favouriteService.createFavourite(favourite);
			return new ResponseEntity<>(createdFavourite, HttpStatus.CREATED);
		} catch (FavouriteNotCreatedException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@ApiOperation(value = "Get favourite using get api url: /api/v1/favourites/id")
	@GetMapping("/favourites/{id}")
	public ResponseEntity<?> getFavouritesById(@PathVariable("id") String userId) {
		try {
			List<Book> favouriteExist = this.favouriteService.getAllFavoritesByUser(userId);
			if(favouriteExist != null) {
				return new ResponseEntity<>(favouriteExist,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
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
