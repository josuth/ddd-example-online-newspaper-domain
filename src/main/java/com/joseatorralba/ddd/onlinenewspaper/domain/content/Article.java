package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Article {

	private final String idArticle;
	private @NonNull String title;
	private String bodyText;
	private boolean published;	
	private List<Comment> commentList = new ArrayList<>();
		
	@Override
	public boolean equals(Object obj) {
		if (obj == null)	{
			return false;
		}
		return idArticle.equals(((Article)obj).getIdArticle());
	}

	public void updateBody(String bodyText) {
		this.bodyText = bodyText;
	}

	public void publish() {
		this.published = true;
	}
	
	public void draft()	{
		this.published = false;
	}

	public Comment createComment(User user, String text) {
		Comment comment = new Comment(generateIdComment(), user, text);
		this.commentList.add(comment);
		return comment;
	}
	
	private int generateIdComment() {
		return this.commentList.size()+1;
	}

}
