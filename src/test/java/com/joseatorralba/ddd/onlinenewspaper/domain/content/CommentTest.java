package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joseatorralba.ddd.onlinenewspaper.domain.exceptions.ContentErrorType;
import com.joseatorralba.ddd.onlinenewspaper.domain.exceptions.OnlineNewspaperException;

@ExtendWith(SpringExtension.class)
public class CommentTest {
	
	@Test
	public void givenMinInfo_whenCreateComment_thenSectionHasTheMinInfo_test()	{
		Comment comment = new Comment(1, new User("user1", false), "Comment's text");
		
		assertEquals(1, comment.getIdComment());
		assertEquals("Comment's text", comment.getText());
		assertEquals("user1", comment.getUser().getUsername());
	}
	
	@Test
	public void givenInfoWithNulls_whenCreateComment_thenError_test()	{
		assertThrows(NullPointerException.class, () -> {
			new Comment(1, null, "Comment's text");
		});
		
		assertThrows(NullPointerException.class, () -> {
			new Comment(1, new User("user1", false), null);
		});
	}
	
	@Test
	public void givenComment_whenOwnerUserRemovesComment_CommentIsMarkedRemoved_test() throws OnlineNewspaperException	{
		User user = new User("user1", false);
		Comment comment = new Comment(1, user, "comment's text");
		
		comment.removeComment(user);
		
		assertTrue(comment.isRemoved());
	}

	@Test
	public void givenComment_whenAdminUserRemovesComment_CommentIsMarkedRemoved_test() throws OnlineNewspaperException	{
		User adminUser = new User("admin", true);
		User user = new User("user1", false);
		Comment comment = new Comment(1, user, "comment's text");
		
		comment.removeComment(adminUser);
		
		assertTrue(comment.isRemoved());
	}

	@Test	
	public void givenComment_whenAnyUserRemovesComment_ErrorIsThrowed_test()	{
		User user1 = new User("user1", false);
		User user2 = new User("user2", false);
		Comment comment = new Comment(1, user1, "comment's text");
		
		OnlineNewspaperException ex = assertThrows(OnlineNewspaperException.class, () -> {
			comment.removeComment(user2);
		});
		
		assertEquals(ContentErrorType.FORBIDDEN, ex.getErrorType());
	}

}
