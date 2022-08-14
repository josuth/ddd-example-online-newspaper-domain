package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ContractTest {
	
	@Test
	public void givenAdvertiser_whenCreateContract_thenContractIsCreated_test()	{
		Contract contract = new Contract(new Advertiser("advertiserName"), new Ad("adName", 0.25));
		
		assertNotNull(contract);
		assertEquals("advertiserName", contract.getAdvertiser().getName());
		assertEquals("adName", contract.getAdList().get(0).getName());
		assertEquals(1, contract.getAdList().size());
	}
	
	@Test
	public void givenContract_whenAddAd_thenAdIsAdded_test()	{
		Contract contract = new Contract(new Advertiser("advertiserName"), new Ad("adName", 0.25));
		
		contract.addAd(new Ad("International", 0.25));
		
		assertEquals(2, contract.getAdList().size());
	}

	@Test
	public void givenContract_whenCalculatePrice_thenCostIsZero_test()	{
		Contract contract = new Contract(new Advertiser("advertiserName"), new Ad("adName", 0.25));
		
		double cost = contract.calculatePrice();
		
		assertEquals(0.25, cost);
	}
	
	@Test
	public void givenContractWithSections_whenCalculatePrice_thenCostIsReturned_test()	{
		Contract contract = new Contract(new Advertiser("advertiserName"), new Ad("adName1", 0.25));
		contract.addAd(new Ad("adName2", 0.15));
		
		double cost = contract.calculatePrice();
		
		assertEquals(0.40, cost);
	}
}
