package com.joseatorralba.ddd.onlinenewspaper.domain.advertising;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AdvertisingManagement {

	private List<Contract> contracts = new ArrayList<Contract>();

	
	public Contract hire(Advertiser advertiser, Ad ad) {
		Contract contract = new Contract(advertiser, ad);
		this.contracts.add(contract);
		return contract;
	}
	
	

}
