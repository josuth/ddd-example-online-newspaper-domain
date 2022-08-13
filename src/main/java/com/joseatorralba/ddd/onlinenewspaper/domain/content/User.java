package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {

	private final String username;
	private boolean admin;

}
