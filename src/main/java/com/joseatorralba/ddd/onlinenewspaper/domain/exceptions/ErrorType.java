package com.joseatorralba.ddd.onlinenewspaper.domain.exceptions;

import lombok.Getter;

public enum ErrorType {
	
	ARTICLE_ALREADY_EXISTS("The article is already in the section"), 
	ARTICLE_NOT_FOUND("The article has not been found"),
	FORBIDDEN("This action is forbidden"), 
	AD_NOT_FOUND("The comment has not been found"), 
	OUT_OF_VALIDITY_PERIOD("The action is out of the validity period"), 
	AD_ALREADY_EXISTS("The ad already exists");
	
	@Getter private String message;

	private ErrorType(String message) {
		this.message = message;
	}
	
}
