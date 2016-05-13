package com.sciencom.ws.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sciencom.ws.model.User;
import com.sciencom.ws.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	@Resource(name = "userServiceBeanPg")
	private UserService userService;
	
	@RequestMapping(
			value = "/api/user", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		
		Collection<User> u = userService.findAll();
		return new ResponseEntity<Collection<User>>(u, HttpStatus.OK);
	
	}
	
	@RequestMapping(
			value = "/api/user/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") Long id){
		User user = userService.findOne(id);
		if (user == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/user",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = userService.create(user);
		return new ResponseEntity<User>(createdUser, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/user",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User updatedUser = userService.update(user);
		if (updatedUser == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/user/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
		boolean isDeleted = userService.delete(id);
		if (isDeleted)
			return new ResponseEntity<User>(HttpStatus.OK);
		else
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/api/user", params = {"name", "city"})
	public ResponseEntity<Collection<User>> getByNameAndCity(@RequestParam String name, 
			@RequestParam String city){
		Collection<User> users = userService.getByNameAndCity(name, city);
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/api/user", params="name")
	public ResponseEntity<Collection<User>> getByName(@RequestParam String name){
		return new ResponseEntity<Collection<User>>(userService.getByName(name), HttpStatus.OK);
	}
}












