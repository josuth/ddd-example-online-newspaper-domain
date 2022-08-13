package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Comment {

	private final int idComment;
	@NonNull private final User user;
	@NonNull private String text;
	private boolean removed;
		
	public void removeComment(User user) throws ContentException {
		if (user.isAdmin() || user.getUsername().equals(this.user.getUsername()))	{
			this.removed = true;
		} else {
			throw new ContentException(ContentErrorType.FORBIDDEN);
		}
	}

}
