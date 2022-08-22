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
	private boolean registered = true;
	
	public void suscribe(SuscriptionType suscriptionType) {
		this.suscription = new Suscription(suscriptionType);
	}
	
	public Optional<LocalDate> cancelSuscription()	{
		if (this.suscription != null)	{
			return Optional.of(this.suscription.getDeactivationDate());
		}
		return Optional.empty();
	}
	
	public Optional<LocalDate> getDeactivationDate()	{
		if (this.suscription != null)	{
			return Optional.of(this.suscription.getDeactivationDate());
		} else {
			return Optional.empty();
		}
	}
	
	public void register() {
		this.registered = true;
	}
	
	public void unregister() {
		this.registered = false;
	}
	
	public boolean isActiveSuscription()	{
		if (this.suscription == null)	{
			return false;
		}
		return LocalDate.now().isBefore(this.suscription.getDeactivationDate());
	}
}
