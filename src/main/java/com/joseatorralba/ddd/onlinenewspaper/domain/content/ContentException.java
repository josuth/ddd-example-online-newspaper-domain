package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import lombok.Getter;

public class ContentException extends Exception {

	private static final long serialVersionUID = -5061799298942230573L;
	
	@Getter ContentErrorType errorType;

	public ContentException(ContentErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
	
}
