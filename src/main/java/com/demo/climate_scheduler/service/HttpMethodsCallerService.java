package com.demo.climate_scheduler.service;

import java.util.List;

import com.demo.climate_scheduler.model.HttpRequest;

public interface HttpMethodsCallerService {

	<T> List<T> get(HttpRequest httpRequest, List<Class<T>> responseClass);
}
