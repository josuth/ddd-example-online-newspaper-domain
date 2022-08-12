package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArticleTest {
	
	@InjectMocks
	private Article article;
	
	@Test
	public void givenMinInfo_whenCreateArticle_thenSectionHasTheMinInfo_test()	{
		Article article = new Article("abc", "Article's title");
		
		assertEquals("abc", article.getIdArticle());
		assertEquals("Article's title", article.getTitle());
		assertEquals(false, article.isPublished());
	} 
	
	@Test
	public void givenArticle_whenEqualsWithSameId_thenTrue_test()	{
		article = new Article("abc", "Article's title");
		
		assertTrue(article.equals(new Article("abc", "")));
	}
	
	@Test
	public void givenArticle_whenEqualsWithDiferentId_thenFalse_test()	{
		article = new Article("abc", "Article's title");
		
		assertTrue(!article.equals(new Article("xxx", "")));
	}
	
	@Test
	public void givenArticle_whenEqualsWithNull_thenTrue_test()	{
		article = new Article("abc", "Article's title");
		
		assertTrue(!article.equals(null));
	}
	
	@Test
	public void givenBodyTest_whenAddBodyText_thenBodyTextIsChanged_test()	{
		String bodyText = "This is the body of the article";
		
		article.updateBody(bodyText);
		
		assertEquals(bodyText, article.getBodyText());
	}
	
	@Test
	public void givenArticle_whenPublish_thenArticleIsPublished_test()	{
		article.publish();
		
		assertTrue(article.isPublished());
	}
	
	@Test
	public void givenArticle_whenRemove_thenArticleIsNotPublished_test()	{
		article.draft();
		
		assertTrue(!article.isPublished());
	}
	
	@Test
	public void givenArticle_whenAddComment_thenCommentIsAddedToArticle_test()	{
		Comment comment = article.createComment(new User("user1", false), "comment's text");
		
		assertTrue(article.getCommentList().contains(comment));
	}
	
	@Test
	public void givenArticleWithComments_whenGetComments_thenCommentsAreReturned_test()	{
		article.createComment(new User("user1", false), "comment's text");
		
		assertEquals(1, article.getCommentList().size());
	}
	
	@Test
	public void givenArticleWithoutComments_whenGetComments_thenCommentsAreNotReturned_test()	{
		assertEquals(0, article.getCommentList().size());
	}
	
	@Test
	public void givenCommentList_whenGenerateIdComment_thenIdIsGenerated_test()	{
		Comment comment1 = article.createComment(new User("user1", false), "comment's text");
		Comment comment2 = article.createComment(new User("user1", false), "comment's text");
		
		assertEquals(1, comment1.getIdComment());
		assertEquals(2, comment2.getIdComment());
	}
	
	
	
}
