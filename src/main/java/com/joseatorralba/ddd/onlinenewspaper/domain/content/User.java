package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import lombok.Getter;

@Getter
public class User {

	private final String username;
	private boolean admin;
	private boolean registered;
	
	public User(String username, Boolean admin) {
		super();
		this.username = username;
		this.admin = admin;
		this.registered = true;		
	}

	public boolean isRegistered()	{
		return this.registered || this.admin;
	}

}
