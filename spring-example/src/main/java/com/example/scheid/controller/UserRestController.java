package com.example.scheid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.scheid.entity.User;
import com.example.scheid.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/rest/user/{id}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id){
		userService.deleteUserById(id);
	}
	
	@RequestMapping(value="/rest/user/{id}", method=RequestMethod.GET)
	public User findUser(@PathVariable Long id){
		return userService.findUserById(id);
	}
	
	@RequestMapping(value="/rest/user/", method=RequestMethod.GET)
	public List<User> findAllUsers(){
		return userService.findAll();
	}
	
	@RequestMapping(value="/rest/user/", method=RequestMethod.POST)
	public User saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}

	@RequestMapping(value="/rest/test/", method=RequestMethod.GET)
	public List<User> test(){
		return userService.findAllWS();
	}
	
	
	/*
	 * 
	 * RETORNANDO STATUS:
	 * @RequestMapping(value = "/controller", method = RequestMethod.GET)
		@ResponseBody
		public ResponseEntity sendViaResponseEntity() {
		    return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		
		OU VIA EXCEPTION:
		@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="To show an example of a custom message")
		public class ForbiddenException extends RuntimeException {}
		
		
	 * 
	 */
}
