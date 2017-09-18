package com.example.user.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.amdatu.web.rest.doc.Description;
import org.amdatu.web.rest.doc.ResponseMessage;
import org.amdatu.web.rest.doc.ResponseMessages;

import com.example.user.api.User;
import com.example.user.api.UserService;

@Path("users/1.0")
@Description("API for Users management version 1.0")
public class UserResources {

	private volatile UserService _userService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Description("Create a user")
	@ResponseMessages({ 
		@ResponseMessage(code = 201, message = "User created"), 
		@ResponseMessage(code = 500, message = "Internal server error"), 
		@ResponseMessage(code = 400, message = "Missing parameters")
	})
	public Response create(User user) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(user == null || user.getFirstName() == null || "".equals(user.getFirstName()) 
				|| user.getLastName() == null || "".equals(user.getLastName())){
			
			return Response.status(Status.BAD_REQUEST.getStatusCode()).build();
			
		} else {
			
			response = _userService.create(user);
			return Response.status((int)response.get("status")).entity(response).build();
		
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Description("Returns a list of users")
	@ResponseMessages({ 
		@ResponseMessage(code = 200, message = "User found"), 
		@ResponseMessage(code = 500, message = "Internal server error"), 
		@ResponseMessage(code = 204, message = "User not found")
	})
	public Response list() {
		
		Map<String, Object> response = new HashMap<String, Object>();
		response = _userService.listUsers();
		
		return Response.status((int)response.get("status")).entity(response).build();		
		
	}
	
}
