package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AdvertisingManagementTest {

	@Test
	public void givenNothing_whenCreateAdsMng_thenCreateWithMinInfo_test()	{
		AdvertisingManagement adMng = new AdvertisingManagement();
		
		assertNotNull(adMng);
		assertEquals(0, adMng.getContracts().size());
	}
	
	@Test
	public void givenAdvertiser_whenHire_thenAdvertiserHireAAd_test()	{		
		AdvertisingManagement adMng = new AdvertisingManagement();
		Contract contract = adMng.hire(new Advertiser("advertiserName"), new Ad("adName", 0.25));
		
		assertNotNull(contract);
		assertEquals("advertiserName", contract.getAdvertiser().getName());
		assertEquals("adName", contract.getAdList().get(0).getName());
	}
	
	
}
