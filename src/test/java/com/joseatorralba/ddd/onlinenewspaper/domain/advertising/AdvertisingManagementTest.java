package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joseatorralba.ddd.onlinenewspaper.domain.exceptions.OnlineNewspaperException;

@ExtendWith(SpringExtension.class)
public class AdvertisingManagementTest {

	@Test
	public void givenNothing_whenCreateAdsMng_thenCreateWithMinInfo_test()	{
		AdvertisingManagement adMng = new AdvertisingManagement();
		
		assertNotNull(adMng);
		assertEquals(0, adMng.getContracts().size());
	}
	
	@Test
	public void givenAdvertiser_whenHire_thenAdvertiserHireAAd_test() throws OnlineNewspaperException	{		
		AdvertisingManagement adMng = new AdvertisingManagement();
		Contract contract = adMng.hire(new Advertiser("advertiserName"), new Ad("adName", 0.25));
		
		assertNotNull(contract);
		assertEquals("advertiserName", contract.getAdvertiser().getName());
		assertEquals("adName", contract.getAdList().get(0).getName());
	}
	
	@Test
	public void givenAdvertiser_whenHireExistingAd_thenError_test() throws OnlineNewspaperException	{
		AdvertisingManagement adMng = new AdvertisingManagement();
		adMng.hire(new Advertiser("advertiserName1"), new Ad("adName1", 0.25));
		
		assertThrows(OnlineNewspaperException.class, () -> {
			adMng.hire(new Advertiser("advertiserName1"), new Ad("adName1", 0.15));
		});

	}
	
	@Test
	public void givenContracts_whenRescindAdvertiserContract_thenGetAdvertiserContractsAreEmpty_test() throws OnlineNewspaperException	{		
		AdvertisingManagement adMng = new AdvertisingManagement();
		adMng.hire(new Advertiser("advertiserAAA"), new Ad("adName1", 0.25));
		adMng.hire(new Advertiser("advertiserAAA"), new Ad("adName2", 0.15));
		adMng.hire(new Advertiser("advertiserBBB"), new Ad("adName1", 0.25));
		adMng.hire(new Advertiser("advertiserBBB"), new Ad("adName2", 0.15));
		
		adMng.rescindContract("advertiserAAA");
		
		assertEquals(0, adMng.getAdvertiserAds("advertiserName1").size());
	}
	
	@Test
	public void givenContracts_whenRescindAd_thenAdvertiserHasZeroAds_test() throws OnlineNewspaperException	{		
		AdvertisingManagement adMng = new AdvertisingManagement();
		adMng.hire(new Advertiser("advertiserName1"), new Ad("adName1", 0.25));
		adMng.hire(new Advertiser("advertiserName2"), new Ad("adName2", 0.15));
		
		adMng.rescindContract("advertiserName1", "adName1");
		
		assertEquals(0, adMng.getAdvertiserAds("advertiserName1").size());
	}
	
	@Test
	public void givenContracts_whenRescindAdWithNull_thenError_test() throws OnlineNewspaperException	{		
		AdvertisingManagement adMng = new AdvertisingManagement();
				
		assertThrows(NullPointerException.class, () -> {
			adMng.rescindContract("aaa", null);
		});
		
		assertThrows(NullPointerException.class, () -> {
			adMng.rescindContract(null, "aaa");
		});
	}
}
