package com.demo.climate_scheduler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import com.demo.climate_scheduler.model.HttpRequest;
import com.demo.climate_scheduler.model.dto.CountryClimateInfoDTO;
import com.demo.climate_scheduler.service.CountryApiCallerService;
import com.demo.climate_scheduler.service.HttpMethodsCallerService;

@Service
public class CountryApiCallerServiceImpl implements CountryApiCallerService {

	@Value("${urls.get-country-info}")
	private String baseURL;
	
	@Value("${app.application-token}")
	private String appKey;
	
	private String URI = "/api/v1/anl/synoptic/locale/";
	
	private HttpMethodsCallerService httpMethodsCallerService;
	
	CountryApiCallerServiceImpl(final HttpMethodsCallerService httpMethodsCallerService) {
		this.httpMethodsCallerService = httpMethodsCallerService;
	}
	
	@Override
	public List<CountryClimateInfoDTO> getCountryClimateInfo(final String countryAbbreviation) {
		
		final String finalURI = this.URI + countryAbbreviation;
		
		final LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("token", appKey);
		
		final HttpRequest request = HttpRequest.builder()
				.baseUrl(baseURL)
				.endPoint(finalURI)
				.queryParams(queryParams)
				.build();
		
		final List<Class<CountryClimateInfoDTO>> countryInfoList = new ArrayList<>();
		
		final List<CountryClimateInfoDTO> response = httpMethodsCallerService.get(request, countryInfoList);
		
		return response;
	}

}
