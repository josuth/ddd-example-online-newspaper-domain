package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Ad {

	private final String name;
	private @NonNull Double costPerClick;
	
}
