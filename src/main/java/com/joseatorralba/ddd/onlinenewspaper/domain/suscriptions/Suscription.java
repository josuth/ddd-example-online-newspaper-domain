package com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions;

import static com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions.SuscriptionType.MONTHLY;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Suscription {
	private @NonNull SuscriptionType suscriptionType;
	private LocalDate startDate = LocalDate.now();
	
	public LocalDate getDeactivationDate() {
		if (MONTHLY.equals(this.suscriptionType))	{
			return this.startDate.plusMonths(1);
			
		} else {
			return this.startDate.plusYears(1);
		
		} 
	}
}
