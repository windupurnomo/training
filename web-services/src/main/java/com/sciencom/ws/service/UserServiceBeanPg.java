package com.sciencom.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sciencom.ws.model.User;
import com.sciencom.ws.repository.UserRepository;

@Service
public class UserServiceBeanPg implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean delete(Long id) {
		User shouldDeletedUser = userRepository.findOne(id);
		if (shouldDeletedUser != null){
			userRepository.delete(id);
			User deletedUser = userRepository.findOne(id);
			return deletedUser == null;
		}
		return false;
	}

	@Override
	public Collection<User> getByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public Collection<User> getByNameAndCity(String name, String city) {
		return userRepository.findByNameOrCity(name, city);
	}
	
	
	

}
