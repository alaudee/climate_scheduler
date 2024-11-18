package com.demo.climate_scheduler.service;

import java.util.List;

import com.demo.climate_scheduler.model.dto.CountryClimateInfoDTO;

public interface CountryApiCallerService {

	List<CountryClimateInfoDTO> getCountryClimateInfo(final String countryAbbreviation);
	
}
