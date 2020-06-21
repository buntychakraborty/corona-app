package io.corona.api.rest.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import io.corona.api.model.IndiaCoivid19;

@FeignClient(url = "https://api.rootnet.in/covid19-in/unofficial/covid19india.org",name="INDIA-CLIENT")
public interface IndiaCovidRestClient {

	@GetMapping("/statewise")
	public IndiaCoivid19 getIndianCases();
}
