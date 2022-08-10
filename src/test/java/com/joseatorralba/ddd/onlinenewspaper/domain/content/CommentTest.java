package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joseatorralba.ddd.onlinenewspaper.domain.users.User;

@ExtendWith(MockitoExtension.class)
public class CommentTest {
	
	@Test
	public void givenMinInfo_whenCreateArticle_thenSectionHasTheMinInfo_test()	{
		Comment comment = new Comment(1, new User("user1"), "Comment's text");
		
		assertEquals(1, comment.getIdComment());
		assertEquals("Comment's text", comment.getText());
		assertEquals("user1", comment.getUser().getUsername());
	} 

}
