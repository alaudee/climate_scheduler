package com.demo.climate_scheduler.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.climate_scheduler.model.HttpRequest;
import com.demo.climate_scheduler.service.HttpMethodsCallerService;

import reactor.core.publisher.Mono;

@Service
public class HttpMethodsCallerServiceImpl implements HttpMethodsCallerService {

	private WebClient.Builder webClientBuilder;
	
	HttpMethodsCallerServiceImpl(final WebClient.Builder webClient){
		this.webClientBuilder = webClient;
	}
	
	public <T> List<T> get(HttpRequest httpRequest, List<Class<T>> responseClass) {
		return getRequest(httpRequest, new ParameterizedTypeReference<List<T>>() {}).block();
	}

	private <T> Mono<List<T>> getRequest(HttpRequest httpRequest, ParameterizedTypeReference<List<T>> responseClass) {
        return webClientBuilder
                .baseUrl(httpRequest.getBaseUrl())// base url is the server url to make http calls
                .build()
                .get()
                	.uri(uriBuilder -> uriBuilder.path(httpRequest.getEndPoint()) // End point to make a call
                                             .queryParams(httpRequest.getQueryParams()) // Adds query params to the end point
                                             .build()
                	)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .headers(httpHeaders -> httpHeaders.addAll(httpRequest.getHeaders())) // Adds headers to the request
                .accept(MediaType.ALL)
                .retrieve()
//                // In Case of 500 or 400 errors throws Runtime exception, if we want we can throw custom exceptions as well
                .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError() || httpStatusCode.is4xxClientError(),
                          error -> error.bodyToMono(String.class)
                                        .map(RuntimeException::new))
                // Serializes the response into java object
                .bodyToMono(responseClass);

	}
	
}
