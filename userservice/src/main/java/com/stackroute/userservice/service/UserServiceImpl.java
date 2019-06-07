package com.stackroute.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean registerUser(User user) throws UserAlreadyExistsException {
		Optional<User> existingUser = userRepository.findById(user.getUserId());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User already exists.");
		}

		User createdUser = 	userRepository.save(user);
	    if(createdUser != null) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("UserId or Password did not matched.");
		}
		return user;
	}
	
	@Override
	public List<String> findAllUserIds() throws UserNotFoundException {
		List<User> userList = userRepository.findAll();
		if (userList.isEmpty()) {
			throw new UserNotFoundException("Users not found.");
		}
		
		List<String> userIds = new ArrayList<String>();
		for(User user: userList) {
			userIds.add(user.getUserId());
		}
		if (userIds.isEmpty()) {
			throw new UserNotFoundException("Users not found.");
		}
		return userIds;
	}
}
