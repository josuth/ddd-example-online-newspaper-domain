package com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SuscriptionManagementTest {

	@Test
	public void givenNothing_whenCreate_thenInstanceCreated_test()	{
		SuscriptionManagement susMng = new SuscriptionManagement();
		
		assertEquals(0, susMng.getUsers().size());
	}
	
	@Test
	public void givenRegisteredUser_whenRegisterUser_thenUserIsRegistered_test()	{
		
	}
	
	@Test
	public void givenUnregisteredUser_whenRegisterUser_thenError_test()	{
		
	}

	@Test
	public void givenUserWithSuscription_whenCancelSuscription_thenDeactivationDateIsReturned_test()	{
		
	}

	@Test
	public void givenUserWithoutSuscription_whenCancelSuscription_thenEmptyIsReturned_test()	{
		
	}
	
	@Test
	public void givenSomeUsers_whenGetRegisteredUsers_thenUserAreReturned_test()	{
		
	}
	
}
