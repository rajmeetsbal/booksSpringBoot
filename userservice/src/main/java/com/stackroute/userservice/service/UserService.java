package com.stackroute.userservice.service;

import java.util.List;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {

	boolean registerUser(User user) throws UserAlreadyExistsException;
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
	List<String> findAllUserIds() throws UserNotFoundException;
}
