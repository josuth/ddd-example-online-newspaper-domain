package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import lombok.Getter;

public class Comment {

	@Getter int idComment;
	@Getter private User user;
	@Getter private String text;
	@Getter private boolean removed;
	
	public Comment(int id, User user, String text) {
		super();
		this.idComment = id;
		this.user = user;
		this.text = text;
		this.removed = false;
	}

	public void removeComment(User user) throws ContentException {
		if (user.isAdmin() || user.getUsername().equals(this.user.getUsername()))	{
			this.removed = true;
		} else {
			throw new ContentException(ContentErrorType.FORBIDDEN);
		}
	}

}
