package com.vmojing.mongodb.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection ="access_token")
public class AccessToken {
	@Id
	private ObjectId id;
	@Field("access_token")
	private String token;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public AccessToken(){
		
	}
	public AccessToken(String token) {
		super();
		this.token = token;
	}
	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", token=" + token + "]";
	}
	
}
