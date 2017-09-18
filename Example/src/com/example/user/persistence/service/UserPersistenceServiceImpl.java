package com.example.user.persistence.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import org.amdatu.mongo.MongoDBService;

import com.example.user.api.User;
import com.example.user.persistence.api.UserPersistenceService;
import com.mongodb.DBCollection;

public class UserPersistenceServiceImpl implements UserPersistenceService {
	
	private static final int SUCCESS = 200;
	private static final int USER_CREATED = 201;
	private static final int USER_NOT_FOUND = 204;
	private static final int INTERNAL_SERVER_ERROR = 500;
	
	private volatile MongoDBService m_mongoDBService;
	
	private static final String COLLECTION = "users";
	private DBCollection userCollection;
	
	public void start() {

		userCollection = m_mongoDBService.getDB().getCollection(COLLECTION);
	}

	@Override
	public Map<String, Object> createUser(User user) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		JacksonDBCollection<User, String> users = JacksonDBCollection.wrap(userCollection, User.class, String.class);
		
		String savedId = users.save(user).getSavedId();
		if (savedId != null) {
			User createdUser = users.findOneById(savedId);
			if (createdUser != null) {
				response.put("user", createdUser);
				response.put("created", true);
				response.put("status", USER_CREATED);
			}
		} else {
			response.put("created", false);
			response.put("status", INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	public Map<String, Object> listUsers() {
		
		Map<String,Object> response = new HashMap<String,Object>();
		JacksonDBCollection<User, Object> users = JacksonDBCollection.wrap(userCollection, User.class);
		DBCursor<User> cursor = null;
		cursor = users.find();

		List<User> list = new ArrayList<>();
		while(cursor.hasNext()) {
			list.add(cursor.next());
		}
		
		if(list.size()!=0){
			response.put("found", true);
			response.put("user",list);
			response.put("status", SUCCESS);
		} else {
			response.put("found", false);
			response.put("status", USER_NOT_FOUND);
		}
		
		return response;
	}

}
