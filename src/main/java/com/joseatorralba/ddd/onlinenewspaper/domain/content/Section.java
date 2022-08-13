package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Section {

	final int idSection;
	private	@NonNull String sectionName;
	private	List<Article> articleList = new ArrayList<>();
	private	List<Ad> adList = new ArrayList<>();
	
	
	public int getNumArticles() {
		return articleList.size();
	}
	
	public Article newArticle(String title) {
		Article article = new Article(generateIdFromTitle(title), title);
		this.articleList.add(article);
		return article;
	}

	public void removeArticle(String idArticle) throws ContentException {
		Optional<Article> op = getArticle(idArticle);
		if (op.isPresent())	{
			op.get().draft();
		} else {
			throw new ContentException(ContentErrorType.ARTICLE_NOT_FOUND);
		}
	}
	
	public Optional<Article> getArticle(String idArticle) {
		if (!hasText(idArticle))	{
			return Optional.empty();
		}
		return articleList.stream()
			.filter(a -> idArticle.equals(a.getIdArticle()))
			.findFirst();
	}	
	
	public Ad newAd(String adName, String fileNameBanner, LocalDate startValidityPeriod, LocalDate endValidityPeriod) {
		Ad ad = new Ad(adName, fileNameBanner, startValidityPeriod, endValidityPeriod);
		this.adList.add(ad);
		return ad;
	}

	public Optional<Ad> getAd(String adName) {
		if (!hasText(adName))	{
			return Optional.empty();
		}
		return this.adList.stream()
				.filter(a -> a.getAdName().equals(adName))
				.findFirst();
	}
	
	public Ad removeAd(String adName) throws ContentException {
		Optional<Ad> op = getAd(adName);
		if (op.isPresent()) {
			op.get().deactivate();
			return op.get();
		}
		throw new ContentException(ContentErrorType.AD_NOT_FOUND);
	}
	
	protected String generateIdFromTitle(String title) {
		int i = 1;
		String newId = serializeTitle(title + "-" + String.valueOf(i++));
		while(getArticle(newId).isPresent())	{
			newId = serializeTitle(title + "-" + String.valueOf(i++));			
		}
		return newId;
	}
	
	private String serializeTitle(String s)	{
		return s.replace(' ', '-');
	}

}
