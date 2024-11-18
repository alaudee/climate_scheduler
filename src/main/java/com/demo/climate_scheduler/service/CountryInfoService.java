package com.demo.climate_scheduler.service;

import java.util.List;

import com.demo.climate_scheduler.model.dto.CountryClimateInfoDTO;

public interface CountryInfoService {

	List<CountryClimateInfoDTO> recoverCountryInfo(final String countryAbbreviation);
	
}
