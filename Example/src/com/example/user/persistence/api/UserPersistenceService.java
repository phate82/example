package com.example.user.persistence.api;

import java.util.Map;

import com.example.user.api.User;

public interface UserPersistenceService {
	
	Map<String, Object> createUser(User user);
	Map<String, Object> listUsers();

}
