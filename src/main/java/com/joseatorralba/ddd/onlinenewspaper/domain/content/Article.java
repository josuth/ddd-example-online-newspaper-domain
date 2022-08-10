package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import java.util.ArrayList;
import java.util.List;

import com.joseatorralba.ddd.onlinenewspaper.domain.users.User;

import lombok.Getter;

public class Article {

	@Getter String idArticle;
	@Getter String title;
	@Getter private String bodyText;
	@Getter boolean published;	
	@Getter List<Comment> commentList;
	
	protected Article(String idArticle, String title) {
		super();
		this.idArticle = idArticle;
		this.title = title;
		this.published = false;
		this.commentList = new ArrayList<>();
	}

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
