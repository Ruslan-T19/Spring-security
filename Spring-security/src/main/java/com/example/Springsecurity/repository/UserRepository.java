package com.example.Springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Springsecurity.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	User findByUsername(String name);
}
