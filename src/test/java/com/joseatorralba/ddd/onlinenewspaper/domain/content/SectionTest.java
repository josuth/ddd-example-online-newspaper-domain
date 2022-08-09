package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SectionTest {
	
	@InjectMocks
	private Section section;
	
	@Test
	public void givenMinInfo_whenCreateSection_thenSectionHasTheMinInfo_test()	{
		section = new Section(1, "International");
		
		assertEquals(1, section.getIdSection());
		assertEquals("International", section.getSectionName());
	}
	
	@Test
	public void givenSection_whenCreateArticle_thenArticleIsCreated_test() throws ContentException	{
		Article article = section.newArticle("Article title");
		
		assertEquals(1, section.getNumArticles());
		assertEquals(article, section.getArticle(article.getIdArticle()).get());
	}
	
	@Test
	public void givenArticle_whenRemoveFromSection_thenArticleIsNotPublished_test() throws ContentException	{
		Article article = section.newArticle("abc");
		
		section.removeArticle(article.getIdArticle());
		
		assertTrue(!section.getArticle(article.getIdArticle()).get().isPublished());
	}
	
	@Test
	public void givenUnknowArticle_when_RemoveFromSection_thenThrowError_test()	{
		ContentException ex = assertThrows(ContentException.class, () -> {
			section.removeArticle("xxx");
		});
		
		assertEquals(ContentErrorType.ARTICLE_NOT_FOUND, ex.getErrorType());
	}
	
	@Test
	public void givenNewTitle_whenGenerateIdFromTitle_thenUniqueIdIsGenerated_test()	{
		String title = "this is a sentence";
		
		String idGenerated = section.generateIdFromTitle(title);
		
		assertEquals("this-is-a-sentence-1", idGenerated);
	}
	
	@Test
	public void givenRepeatedTitle_whenGenerateIdFromTitle_thenUniqueIdIsGenerated_test()	{
		String title = "this is a sentence";
		section.newArticle(title);
				
		String idGenerated = section.generateIdFromTitle(title);
		
		assertEquals("this-is-a-sentence-2", idGenerated);
	}

}
