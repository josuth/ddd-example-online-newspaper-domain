package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

public class Section {

	@Getter int idSection;
	@Getter String sectionName;
	List<Article> articleList;
	
	public Section() {
		this.articleList = new ArrayList<>();
	}

	public Section(int id, String sectionName) {
		this.idSection = id;
		this.sectionName = sectionName;
	}

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
		return articleList.stream()
			.filter(a -> idArticle.equals(a.getIdArticle()))
			.findFirst();
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
