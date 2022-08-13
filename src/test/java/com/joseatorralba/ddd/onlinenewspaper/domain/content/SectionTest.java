package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SectionTest {
	
	@Test
	public void givenMinInfo_whenCreateSection_thenSectionHasTheMinInfo_test()	{
		Section section = new Section(1, "International");
		
		assertEquals(1, section.getIdSection());
		assertEquals("International", section.getSectionName());
		assertEquals(0, section.getArticleList().size());
		assertEquals(0, section.getAdList().size());
	}
	
	@Test
	public void givenInfoWithNulls_whenCreateSection_thenSectionHasTheMinInfo_test()	{
		assertThrows(NullPointerException.class, () -> {
			new Section(1, null);
		});
	}
	
	@Test
	public void givenSection_whenCreateArticle_thenArticleIsCreated_test() throws ContentException	{
		Section section = new Section(1, "International");
		Article article = section.newArticle("Article title");
		
		assertEquals(1, section.getNumArticles());
		assertEquals(article, section.getArticle(article.getIdArticle()).get());
	}
	
	@Test
	public void givenSectionWithArticles_whenGetArticle_thenArticleIsReturned_test()	{
		Section section = new Section(1, "International");
		Article article = section.newArticle("Article title");
		
		Optional<Article> op = section.getArticle(article.getIdArticle());
		
		assertTrue(op.isPresent());
		assertEquals(article.getIdArticle(), op.get().getIdArticle());
	}
	
	@Test
	public void givenSectionWithArticles_whenGetUnknownArticle_thenEmptyIsReturned_test()	{
		Section section = new Section(1, "International");
		section.newArticle("Article title");
		
		Optional<Article> op = section.getArticle("unknown");
		
		assertTrue(op.isEmpty());
	}
	
	@Test
	public void givenSectionWithArticles_whenGetArticleWithEmptyId_thenEmptyIsReturned_test()	{
		Section section = new Section(1, "International");
		section.newArticle("Article title");
		
		Optional<Article> op = section.getArticle(null);
		
		assertTrue(op.isEmpty());
	}
	
	@Test
	public void givenArticle_whenRemoveFromSection_thenArticleIsNotPublished_test() throws ContentException	{
		Section section = new Section(1, "International");
		Article article = section.newArticle("abc");
		
		section.removeArticle(article.getIdArticle());
		
		assertTrue(!section.getArticle(article.getIdArticle()).get().isPublished());
	}
	
	@Test
	public void givenUnknowArticle_when_RemoveFromSection_thenThrowError_test()	{
		Section section = new Section(1, "International");
		
		ContentException ex = assertThrows(ContentException.class, () -> {
			section.removeArticle("xxx");
		});
		
		assertEquals(ContentErrorType.ARTICLE_NOT_FOUND, ex.getErrorType());
	}
	
	@Test
	public void givenNewTitle_whenGenerateIdFromTitle_thenUniqueIdIsGenerated_test()	{
		Section section = new Section(1, "International");
		String title = "this is a sentence";
		
		String idGenerated = section.generateIdFromTitle(title);
		
		assertEquals("this-is-a-sentence-1", idGenerated);
	}
	
	@Test
	public void givenRepeatedTitle_whenGenerateIdFromTitle_thenUniqueIdIsGenerated_test()	{
		Section section = new Section(1, "International");
		String title = "this is a sentence";
		section.newArticle(title);
				
		String idGenerated = section.generateIdFromTitle(title);
		
		assertEquals("this-is-a-sentence-2", idGenerated);
	}
	
	@Test
	public void givenSection_whenCreateAd_thenSectionHasNewAd_test() throws ContentException	{
		Section section = new Section(1, "International");
		Ad ad = section.newAd("adName", "img/banner.jpg", now(), now().plusMonths(6) );
		
		Optional<Ad> op = section.getAd("adName");
		assertNotNull(op.isPresent());
		Ad expectedAd = op.get();		
		assertTrue(!ad.isActive());
		assertEquals(ad.getAdName(), expectedAd.getAdName());
		assertEquals(ad.getBannerPath(), expectedAd.getBannerPath());
		assertEquals(ad.getStartValidityPeriod(), expectedAd.getStartValidityPeriod());
		assertEquals(ad.getEndValidityPeriod(), expectedAd.getEndValidityPeriod());
	}

	@Test
	public void givenSection_whenRemoveAd_thenSectionHasNotAd_test() throws ContentException	{
		Section section = new Section(1, "International");
		section.newAd("adName", "img/banner.jpg", now(), now().plusMonths(6));
		
		Ad ad = section.removeAd("adName");
		
		assertTrue(!ad.isActive());
		assertTrue(!section.getAd("adName").get().isActive());
	}
	
	@Test
	public void givenSectionWithComments_whenGetUnknowAd_thenEmptyReturned_test()	{
		Section section = new Section(1, "International");
		section.newAd("adName", "img/banner.jpg", now(), now().plusMonths(6) );
		
		assertTrue(section.getAd("unknow").isEmpty());
	}
	
	@Test
	public void givenSectionWithComments_whenGetAdWithEmptyName_thenEmptyReturned_test()	{
		Section section = new Section(1, "International");
		section.newAd("adName", "img/banner.jpg", now(), now().plusMonths(6) );
		
		assertTrue(section.getAd(null).isEmpty());
	}
	
	@Test
	public void givenSectionWithComments_whenRemoveAd_thenError_test()	{
		Section section = new Section(1, "International");
		section.newAd("adName", "img/banner.jpg", now(), now().plusMonths(6) );

		ContentException ex = assertThrows(ContentException.class, () -> {
			section.removeAd("unknow");
		});
		
		assertEquals(ContentErrorType.AD_NOT_FOUND, ex.getErrorType());
	}
	
}
