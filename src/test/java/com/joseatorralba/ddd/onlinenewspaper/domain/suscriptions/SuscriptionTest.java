package com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SuscriptionTest {

	@Test
	public void givenSuscriptionType_whenCreate_thenSuscriptionIsCreated_test()	{
		Suscription suscription = new Suscription(SuscriptionType.ANUAL);
		
		assertEquals(SuscriptionType.ANUAL, suscription.getSuscriptionType());
		assertEquals(LocalDate.now(), suscription.getStartDate());
	}
	
	@Test
	public void givenSuscriptionTypeNull_whenCreate_thenError_test()	{
		assertThrows(NullPointerException.class, () -> {
			new Suscription(null);
		});
	}
	
	@Test
	public void givenAnualSuscription_whenGetDeactivationDate_thenExpirationDayIsWithin30days_test()	{
		Suscription suscription = new Suscription(SuscriptionType.ANUAL);
		
		assertEquals(LocalDate.now().plusYears(1), suscription.getDeactivationDate());
	}
	
	@Test
	public void givenMonthlySuscription_whenGetDeactivationDate_thenExpirationDayIsWithin30days_test()	{
		Suscription suscription = new Suscription(SuscriptionType.MONTHLY);
		
		assertEquals(LocalDate.now().plusMonths(1), suscription.getDeactivationDate());
	}
}
