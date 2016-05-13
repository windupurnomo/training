package com.sciencom.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sciencom.ws.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findByName(String name);
	
	@Query("select u from User u where u.name = ?1 and u.city = ?2")
	public List<User> findByNameAndCity(String name, String city);
	
	public List<User> findByNameOrCity(String name, String city);

	@Query("select u from User u where u.name like %?1%")
	public List<User> findByNameFree(String name);

}