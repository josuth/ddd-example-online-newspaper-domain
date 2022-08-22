package com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions;

import static com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions.SuscriptionType.MONTHLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserTest {
	
	@Test
	public void givenUsername_whenCreate_thenUserIsCreated_test()	{
		User user = new User("username");
		
		assertEquals("username", user.getUsername());
		assertNull(user.getSuscription());
		assertTrue(!user.isActiveSuscription());
		assertTrue(user.isRegistered());
	}
	
	@Test
	public void givenUserWithActiveSuscription_whenIsSuscribed_thenTrue_test()	{
		User user = new User("username");
		user.suscribe(SuscriptionType.ANUAL);
		
		assertTrue(user.isActiveSuscription());
	}
	
	@Test
	public void givenUserWithNoActiveSuscription_whenIsSuscribed_thenFalse_test()	{
		User user = new User("username");
		
		assertTrue(!user.isActiveSuscription());
	}
	
	@Test
	public void givenUserWithActiveSuscription_whenGetDeactivationDate_thenDateIsReturned_test()	{
		User user = new User("username");
		user.suscribe(SuscriptionType.MONTHLY);
		
		assertEquals(LocalDate.now().plusMonths(1), user.getDeactivationDate().get());
	}
	
	@Test
	public void givenUserWithNoActiveSuscription_whenGetDeactivationDate_thenEmptyIsReturned_test()	{
		User user = new User("username");
		
		assertTrue(user.getDeactivationDate().isEmpty());
	}
	
	@Test
	public void givenUser_whenRegister_thenUserIsRegistered_test()	{
		User user = new User("username");

		user.register();

		assertTrue(user.isRegistered());
	}
	
	@Test
	public void givenUser_whenUnregister_thenUserIsNotRegistered_test()	{
		User user = new User("username");

		user.unregister();

		assertTrue(!user.isRegistered());
	}
	
	@Test
	public void givenUser_whenCancelSuscription_thenDeactivationDateIsReturned_test()	{
		User user = new User("username");
		user.suscribe(MONTHLY);
		
		Optional<LocalDate> deactivationDate = user.cancelSuscription();

		assertTrue(user.isActiveSuscription());
		assertEquals(LocalDate.now().plusMonths(1), deactivationDate.get());
	}
	
	@Test
	public void givenUserWithoutSuscription_whenCancelSuscription_thenDeactivationDateIsEmpty_test()	{
		User user = new User("username");
		
		Optional<LocalDate> deactivationDate = user.cancelSuscription();

		assertTrue(!user.isActiveSuscription());
		assertTrue(deactivationDate.isEmpty());
	}
	
}
