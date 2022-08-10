package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import com.joseatorralba.ddd.onlinenewspaper.domain.users.User;

import lombok.Getter;

public class Comment {

	@Getter int idComment;
	@Getter private User user;
	@Getter private String text;
	
	public Comment(int id, User user, String text) {
		super();
		this.idComment = id;
		this.user = user;
		this.text = text;
	}

}
