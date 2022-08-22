package com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions;

import java.time.LocalDate;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class User {

	private final String username;
	private Suscription suscription;
	private boolean activeSuscription;
	
	public void suscribe(SuscriptionType suscriptionType) {
		this.suscription = new Suscription(suscriptionType);
		this.activeSuscription = true;
	}
	
	public Optional<LocalDate> getDeactivationDate()	{
		if (this.activeSuscription)	{
			return Optional.of(this.suscription.getDeactivationDate());
		} else {
			return Optional.empty();
		}
	}
}
