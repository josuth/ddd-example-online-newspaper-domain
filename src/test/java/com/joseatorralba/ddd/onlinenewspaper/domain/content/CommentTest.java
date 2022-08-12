package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentTest {
	
	@Test
	public void givenMinInfo_whenCreateArticle_thenSectionHasTheMinInfo_test()	{
		Comment comment = new Comment(1, new User("user1", false), "Comment's text");
		
		assertEquals(1, comment.getIdComment());
		assertEquals("Comment's text", comment.getText());
		assertEquals("user1", comment.getUser().getUsername());
	} 
	
	@Test
	public void givenComment_whenOwnerUserRemovesComment_CommentIsMarkedRemoved_test() throws ContentException	{
		User user = new User("user1", false);
		Comment comment = new Comment(1, user, "comment's text");
		
		comment.removeComment(user);
		
		assertTrue(comment.isRemoved());
	}

	@Test
	public void givenComment_whenAdminUserRemovesComment_CommentIsMarkedRemoved_test() throws ContentException	{
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
		
		ContentException ex = assertThrows(ContentException.class, () -> {
			comment.removeComment(user2);
		});
		
		assertEquals(ContentErrorType.FORBIDDEN, ex.getErrorType());
	}

}
