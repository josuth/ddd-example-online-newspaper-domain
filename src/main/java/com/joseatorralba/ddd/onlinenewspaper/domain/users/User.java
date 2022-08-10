package com.joseatorralba.ddd.onlinenewspaper.domain.users;

import lombok.Getter;

public class User {

	@Getter private String username;

	public User(String username) {
		super();
		this.username = username;
	}
	
}
