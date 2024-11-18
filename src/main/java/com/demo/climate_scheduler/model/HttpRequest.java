package com.demo.climate_scheduler.model;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class HttpRequest {

	private String baseUrl;
	
	private String endPoint;
	
	private Object body;
	
	/* Caso não receber valor no momento de build
	 * instancia uma nova lista para o campo
	 */
	@Builder.Default
    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    @Builder.Default
    private MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
}
