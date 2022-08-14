package com.joseatorralba.ddd.onlinenewspaper.domain.content;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ad {

	private @NonNull final String adName;
	private @NonNull String bannerPath;
	private @NonNull LocalDate startValidityPeriod;
	private @NonNull LocalDate endValidityPeriod;
	
	@Getter(AccessLevel.NONE)
	private boolean deactivated = false;
	
	public boolean isActive() throws ContentException {
		if (isValidityPeriod() && !this.deactivated)	{
			return true;
		}
		return false;
	}

	public void deactivate() {
		this.deactivated = true;
	}
	
	public void reactivate()	{
		this.deactivated = false;
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
