package com.demo.climate_scheduler.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.climate_scheduler.model.dto.CountryClimateInfoDTO;
import com.demo.climate_scheduler.service.CountryInfoService;

@RestController
@RequestMapping("/country")
public class CountryController {
	
	private CountryInfoService countryInfoService;
	
	public CountryController(final CountryInfoService countryInfoService) {
		this.countryInfoService = countryInfoService;
	}
	
	@GetMapping("/info")
	public ResponseEntity<List<CountryClimateInfoDTO>> getCountryInfo(@RequestParam final String countryAbbreviation) {
			final List<CountryClimateInfoDTO> countryResponse = this.countryInfoService.recoverCountryInfo(countryAbbreviation);
			
			return new ResponseEntity<>(countryResponse, HttpStatus.OK);
		
	}
	
}
