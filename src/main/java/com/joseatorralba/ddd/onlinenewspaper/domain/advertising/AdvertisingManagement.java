package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.joseatorralba.ddd.onlinenewspaper.domain.exceptions.ContentErrorType;
import com.joseatorralba.ddd.onlinenewspaper.domain.exceptions.OnlineNewspaperException;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class AdvertisingManagement {

	private List<Contract> contracts = new ArrayList<Contract>();

	
	public Contract hire(Advertiser advertiser, Ad ad) throws OnlineNewspaperException {
		List<Ad> contractList = getAdvertiserAds(advertiser.getName());
		Optional<Ad> existAd = contractList.stream()
				.filter(c -> c.getName().equals(ad.getName()))
				.findFirst();
		if (existAd.isPresent())	{
			throw new OnlineNewspaperException(ContentErrorType.AD_ALREADY_EXISTS);
		}
		
		Contract contract = new Contract(advertiser, ad);
		this.contracts.add(contract);
		return contract;
	}

	public void rescindContract(String advertiserName) {
		this.contracts = this.contracts.stream()
				.filter(c -> !c.getAdvertiser().getName().equals(advertiserName))
				.collect(Collectors.toList());
	}
	
	public void rescindContract(@NonNull String advertiserName, @NonNull String adName) {
		List<Ad> contractList = getAdvertiserAds(advertiserName);
		Optional<Ad> adToRemove = contractList.stream()
				.filter(c -> c.getName().equals(adName))
				.findFirst();
		if (adToRemove.isPresent())	{
			contractList.remove(adToRemove.get());
		}
		
	}
	
	public List<Ad> getAdvertiserAds(String advertiserName) {
		Optional<Contract> op = this.contracts.stream()
				.filter(c -> c.getAdvertiser().getName().equals(advertiserName))
				.findFirst();
		if (op.isEmpty())	{
			return new ArrayList<Ad>();
		}
		
		return op.get().getAdList();			
	}

}
