package com.demo.climate_scheduler.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.climate_scheduler.model.dto.CountryClimateInfoDTO;
import com.demo.climate_scheduler.service.CountryApiCallerService;
import com.demo.climate_scheduler.service.CountryInfoService;

@Service
public class CountryInfoServiceImpl implements CountryInfoService {

	private CountryApiCallerService countryApiCallerService;
	
	CountryInfoServiceImpl(final CountryApiCallerService countryApiCallerService){
		this.countryApiCallerService = countryApiCallerService;
	}
	
	@Override
	public List<CountryClimateInfoDTO> recoverCountryInfo(final String countryAbbreviation) {
		final List<CountryClimateInfoDTO> countryInfo = this.countryApiCallerService.getCountryClimateInfo(countryAbbreviation);
		
		return countryInfo;
	}

}
