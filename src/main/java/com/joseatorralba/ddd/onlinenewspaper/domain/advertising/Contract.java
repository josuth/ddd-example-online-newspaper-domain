package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Contract {

	private @NonNull Advertiser advertiser;
	private @NonNull List<Ad> adList;
	
	protected Contract(Advertiser advertiser, Ad ad)	{
		this.advertiser = advertiser;
		adList = new ArrayList<>();		
		this.adList.add(ad);
	}
	
	public void addAd(Ad ad) {
		this.adList.add(ad);
	}

	public double calculatePrice() {
		return this.adList.stream()
			.mapToDouble(Ad::getCostPerClick)
			.sum();
	}
	
}
