package com.sciencom.ws.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sciencom.ws.model.User;

@Service
public class UserServiceBean implements UserService {

	private static Map<Long, User> users;
	private static Long nextId;
	
	static {
		User u1 = new User();
		u1.setName("Tonny Stark");
		save(u1);
		
		User u2 = new User();
		u2.setName("Clark Kent");
		save(u2);
	}
	
	private static User save(User user){
		if (users == null){
			users = new HashMap<Long, User>();
			nextId = 1L;
		}
		
		//update ...
		if (user.getId() != null){
			User temp = users.get(user.getId());
			if (temp == null){
				return null;
			}
			temp.setName(user.getName());
			return temp;
		}
		
		//create ...
		user.setId(nextId);
		users.put(user.getId(), user);
		++nextId;
		return user;
	}
	
	@Override
	public Collection<User> findAll() {
		return users.values();
	}

	@Override
	public User findOne(Long id) {
		return users.get(id);
	}

	@Override
	public User create(User user) {
		return save(user);
	}

	@Override
	public User update(User user) {
		return save(user);
	}

	@Override
	public boolean delete(Long id) {
		User deletedUser = users.remove(id);
		return deletedUser != null;
		
	}

	@Override
	public Collection<User> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> getByNameAndCity(String name, String city) {
		// TODO Auto-generated method stub
		return null;
	}

}
