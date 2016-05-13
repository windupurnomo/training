package com.sciencom.ws.service;

import java.util.Collection;

import com.sciencom.ws.model.User;

public interface UserService {
	public Collection<User> findAll();
	public User findOne(Long id);
	public User create(User user);
	public User update(User user);
	public boolean delete(Long id);
	public Collection<User> getByName(String name);
	public Collection<User> getByNameAndCity(String name, String city);
}