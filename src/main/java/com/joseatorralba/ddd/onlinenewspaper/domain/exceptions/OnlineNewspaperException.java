package com.joseatorralba.ddd.onlinenewspaper.domain.exceptions;

import lombok.Getter;

public class OnlineNewspaperException extends Exception {

	private static final long serialVersionUID = -5061799298942230573L;
	
	@Getter ErrorType errorType;

	public OnlineNewspaperException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
	
}
