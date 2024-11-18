package com.demo.climate_scheduler.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryClimateInfoDTO {

	private String name;
	private String date;
	private String message;
	
}
