package com.stackroute.userservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	private transient User user;
	
	@Autowired
	private transient MockMvc mockMvc;

	@MockBean
	private transient UserService userService;

	@InjectMocks
	private UserController userController;

	@InjectMocks
	private UserController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("mohan1", "Mohan1", "mohan1@yahoo.com", "MohanPwd1", new Date());
	}

	@Test
	public void testRegisterUser() throws Exception {
		when(userService.registerUser(user)).thenReturn(true);
		mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status()
						.isCreated()).andDo(print());
		verify(userService, times(1)).registerUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}

	@Test
    public void testLogin() throws Exception {
        Mockito.when(userService.registerUser(user)).thenReturn(true);
        Mockito.when(userService.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }
}