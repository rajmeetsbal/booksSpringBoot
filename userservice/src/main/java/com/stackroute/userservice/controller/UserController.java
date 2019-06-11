package com.stackroute.userservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins= {"http://localhost:4200", "http://localhost:4200/*"})
@EnableFeignClients("com.stackroute.userservice.controller")
@RibbonClient(name="userservice")
@Api
public class UserController {

	static final long EXPIRATION_TIME = 300000;
	private Map<String,String> map = new HashMap<>();
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ApiOperation(value = "Register user using post api url: /api/v1/user")
	@PostMapping("/user")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception {
		try {
			boolean createdUser = this.userService.registerUser(user);
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "Login using post api url: /api/v1/login")
	@PostMapping("/login")
   	public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException {
		List<String> userIds = this.userService.findAllUserIds();
   		String jwtToken= "";
   		try {
   			jwtToken = getToken(user.getUserId(), user.getPassword());
   				map.clear();
   				map.put("message", "User successfully loggedIn");
   				map.put("token", jwtToken);
   				map.put("loggedInUser", user.getUserId());
   				if(!userIds.isEmpty()) {
   					map.put("userIds", userIds.toString());
   				}
   				
   			} catch (Exception e) {
   				e.printStackTrace();
   				map.clear();
   				map.put("message", e.getMessage());
   				map.put("token", null);
   				return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
   			}
   		
   		return new ResponseEntity<>(map, HttpStatus.OK);
   	}
	
	@ApiOperation(value = "get all users: /api/v1/users")
	@GetMapping("/users")
   	public ResponseEntity<?> getAllUsers() throws UserNotFoundException {
		List<String> userIds = this.userService.findAllUserIds(); 		
   		return new ResponseEntity<>(userIds, HttpStatus.OK);
   	}
	
    // Generate JWT token
	public String getToken(String userId, String password) throws Exception {
		if(userId == null || password == null) {
			throw new UserNotFoundException("User does not exist.");
		}
		
		User user = userService.findByUserIdAndPassword(userId, password);
		if(user == null) {
			throw new UserNotFoundException("UserId or Password did not matched.");
		}
		
		String jwtToken = Jwts.builder()
		    .setSubject(userId)
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		    .signWith(SignatureAlgorithm.HS256, "secretkey")
		    .compact();
		
		return jwtToken;
	}
	
	//@GetMapping("/")
	@RequestMapping(method = RequestMethod.GET, value="/")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}
}
