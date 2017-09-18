package com.example.user.api;

import java.util.Map;

public interface UserService {

	Map<String, Object> create(User user);
	Map<String, Object> listUsers();
	
}
