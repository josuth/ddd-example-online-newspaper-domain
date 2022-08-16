package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import java.util.ArrayList;
import java.util.List;

import com.joseatorralba.ddd.onlinenewspaper.domain.exceptions.ErrorType;
import com.joseatorralba.ddd.onlinenewspaper.domain.exceptions.OnlineNewspaperException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {

	private final String idArticle;
	private @NonNull String title;
	private String bodyText;
	private boolean published;	
	private List<Comment> commentList = new ArrayList<>();
	private boolean payment;
		
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

	public Comment createComment(User user, String text) throws OnlineNewspaperException {
		if (!user.isRegistered())	{
			throw new OnlineNewspaperException(ErrorType.FORBIDDEN);
		}
		Comment comment = new Comment(generateIdComment(), user, text);
		this.commentList.add(comment);
		return comment;
	}
	
	private int generateIdComment() {
		return this.commentList.size()+1;
	}

	public boolean canRead(User user) {
		if (!this.payment)	{
			return true;
		}
		return user.isRegistered();
	}

	public void setPayment() {
		this.payment = true;
	}

	public void setFree() {
		this.payment = false;
	}

}
