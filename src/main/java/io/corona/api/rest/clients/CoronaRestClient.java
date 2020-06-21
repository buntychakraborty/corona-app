package io.corona.api.rest.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.corona.api.model.Covid19B0;

@FeignClient(url = "https://corona.lmao.ninja/v2", name = "CORONA-CLIENT")
public interface CoronaRestClient {
	@GetMapping("/countries")
	public List<Covid19B0> getWorldCases();
	@GetMapping("countries/{country}")
	public Covid19B0 getCountryWiseCases(@PathVariable String country);
}
