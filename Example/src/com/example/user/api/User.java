package com.example.user.api;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class User {

	@Id
	@ObjectId
	private String _id;
	private String firstName;
	private String lastName;
	
	@Id
	@ObjectId
	public String get_id() {
		return _id;
	}
	@Id
	@ObjectId
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
