package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserTest {
	
	@Test
	public void givenUsername_whenCreate_thenUserIsCreated_test()	{
		User user = new User("username", false);
		
		assertNotNull(user);
		assertEquals("username", user.getUsername());
		assertTrue(!user.isAdmin());
		assertTrue(user.isRegistered());
	}

	@Test
	public void givenUsername_whenCreateAdminUser_thenUserIsCreated_test()	{
		User user = new User("username", true);
		
		assertNotNull(user);
		assertEquals("username", user.getUsername());
		assertTrue(user.isAdmin());
		assertTrue(user.isRegistered());
	}
	
}
