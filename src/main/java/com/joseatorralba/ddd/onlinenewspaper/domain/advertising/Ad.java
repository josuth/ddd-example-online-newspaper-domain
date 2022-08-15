package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ad {

	private final String name;
	private double costPerClick;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)	{
			return false;
		}
		return this.name.equals(((Ad)obj).getName());
	}
}
