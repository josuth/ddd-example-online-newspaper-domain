package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AdTest {
	
	@Test
	public void givenMinInfo_whenCreateAd_thenAdHasTheMinInfo_test()	{
		LocalDate now = LocalDate.now();
		Period period = Period.ofMonths(6);
		LocalDate finalDate = LocalDate.now().plus(period);
		
		Ad ad = new Ad("adName1", "img/banner.jpg", now(), now().plusMonths(6));
		
		assertEquals("adName1", ad.getAdName());
		assertEquals("img/banner.jpg", ad.getBannerPath());
		assertEquals(now, ad.getStartValidityPeriod());
		assertEquals(finalDate, ad.getEndValidityPeriod());
	}
	
	@Test
	public void givenInfoWithNulls_whenCreateAd_thenError_test()	{
		assertThrows(NullPointerException.class, () -> {
			new Ad(null, "img/banner.jpg", now(), now().plusMonths(6));
		});
		
		assertThrows(NullPointerException.class, () -> {
			new Ad("adName1", null, now(), now().plusMonths(6));
		});
		
		assertThrows(NullPointerException.class, () -> {
			new Ad("adName1", "img/banner.jpg", null, now().plusMonths(6));
		});
		
		assertThrows(NullPointerException.class, () -> {
			new Ad("adName1", "img/banner.jpg", now(), null);
		});
	}

}
