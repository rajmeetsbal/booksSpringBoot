
package com.stackroute.userservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserServiceImpl;

 
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	private User user1;
	private User user2;
	private Optional<User> user1Options;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user1 = new User("mohan1", "Mohan1", "mohan1@yahoo.com", "MohanPwd1", new Date());
		user2 = new User("mohan2", "Mohan2", "mohan2@yahoo.com", "MohanPwd2", new Date());
		user1Options = Optional.of(user1);
	}

	@Test
	public void testRegisterUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.save(user1)).thenReturn(user1);
		boolean flag = userServiceImpl.registerUser(user1);
		assertEquals("Register user success",  true, flag);
		verify(userRepository, times(1)).save(user1);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testRegisterUserFailure() throws UserAlreadyExistsException {
		when(userRepository.findById(user1.getUserId())).thenReturn(user1Options);
		when(userRepository.save(user1)).thenReturn(user1);
		boolean flag = userServiceImpl.registerUser(user1);
		assertTrue("Register user failed", flag);
		verify(userRepository, times(1)).findById(user1.getUserId());
	}

	@Test
	public void testFindByUserIdAndPasswordSuccess() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user1.getUserId(), user1.getPassword())).thenReturn(user1);
		User userResult = userServiceImpl.findByUserIdAndPassword(user1.getUserId(), user1.getPassword());
		assertNotNull(userResult);
		assertEquals(user1.getUserId(), userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user1.getUserId(), user1.getPassword());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testFindByUserIdAndPasswordFailure() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user1.getUserId(), user1.getPassword())).thenReturn(user1);
		User userResult = userServiceImpl.findByUserIdAndPassword(user2.getUserId(), user2.getPassword());
		assertNotNull(userResult);
		assertNotEquals(user1.getUserId(), userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user1.getUserId(), user1.getPassword());
	}

	@Test
	public void testFindAllUserIds() throws UserNotFoundException {
		List<User> userList = Arrays.asList(user1, user2);
		when(userRepository.findAll()).thenReturn(userList);
		userServiceImpl.findAllUserIds();
	}
}
