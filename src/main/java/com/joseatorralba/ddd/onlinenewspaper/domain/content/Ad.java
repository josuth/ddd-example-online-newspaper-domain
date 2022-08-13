package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Ad {

	private @NonNull final String adName;
	private @NonNull String bannerPath;
	private @NonNull LocalDate startValidityPeriod;
	private @NonNull LocalDate endValidityPeriod;
	private boolean active;
	
	public boolean isActive() throws ContentException {
		if (isValidityPeriod())	{
			return this.active;
		}
		throw new ContentException(ContentErrorType.OUT_OF_VALIDITY_PERIOD);
	}

	public void deactivate() {
		this.active = false;
	}
	
	public void reactivate()	{
		this.active = true;
	}
	
	public boolean isValidityPeriod()	{
		LocalDate today = LocalDate.now();
		if (today.isEqual(this.startValidityPeriod))		{
			return true;
		} 
		if (today.isEqual(this.endValidityPeriod))	{
			return true;
		}
		return today.isAfter(this.startValidityPeriod) && today.isBefore(this.endValidityPeriod);
	}

}
