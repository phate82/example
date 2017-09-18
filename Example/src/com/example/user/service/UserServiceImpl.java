package com.example.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import com.example.user.api.User;
import com.example.user.api.UserService;
import com.example.user.persistence.api.UserPersistenceService;

public class UserServiceImpl implements UserService {
	
	private volatile UserPersistenceService _userPersistenceService;

	@Override
	public Map<String, Object> create(User user) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		Map<String, Object> persistenceMap = _userPersistenceService.createUser(user);
		
		if((int) persistenceMap.get("status") == 201){
			
			response.put("user", (User) persistenceMap.get("user"));
			response.put("message", "User created");
			response.put("status", Status.CREATED.getStatusCode());
		
		} else {
			
			response.put("message", "Internal server error");
			response.put("status", Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}

		return response;
	}

	@Override
	public Map<String, Object> listUsers() {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		Map<String, Object> persistenceMap = _userPersistenceService.listUsers();
		
		if((int) persistenceMap.get("status") == 200){
			
			response.put("message", "User found");
			response.put("status", Status.OK.getStatusCode());
			response.put("user", persistenceMap.get("user"));
			
		} else if((int) persistenceMap.get("status") == 204){

			response.put("message", "User not found");
			response.put("status", Status.NO_CONTENT.getStatusCode());
		} 	else {
			
			response.put("message", "Internal server error");
			response.put("status", Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
		
		return response;
		
	}

}
