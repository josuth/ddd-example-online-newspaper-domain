package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AdTest {
	
	@Test
	public void givenTwoEqualAds_whenEquals_thenEqualsTrue_test()	{
		Ad ad1 = new Ad("adName", 0);
		Ad ad2 = new Ad("adName", 0);
		
		assertTrue(ad1.equals(ad2));
	}

	@Test
	public void givenTwoDifferentAds_whenEquals_thenEqualsFalse_test()	{
		Ad ad1 = new Ad("adName", 0);
		Ad ad2 = new Ad("xxxxxx", 0);
		
		assertTrue(!ad1.equals(ad2));
	}
	
	@Test
	public void givenAdAndNull_whenEquals_thenEqualsFalse_test()	{
		Ad ad1 = new Ad("adName", 0);
		Ad ad2 = null;
		
		assertTrue(!ad1.equals(ad2));
	}
}
