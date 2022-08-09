package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import lombok.Getter;

public enum ContentErrorType {
	
	ARTICLE_ALREADY_EXISTS("The article is already in the section"), 
	ARTICLE_NOT_FOUND("The article has been found");
	
	@Getter private String message;

	private ContentErrorType(String message) {
		this.message = message;
	}
	
}
