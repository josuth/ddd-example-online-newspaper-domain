package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import lombok.Getter;

public class User {

	@Getter private String username;
	@Getter private boolean admin;

	public User(String username, boolean admin) {
		super();
		this.username = username;
		this.admin = admin;
	}
	
}
