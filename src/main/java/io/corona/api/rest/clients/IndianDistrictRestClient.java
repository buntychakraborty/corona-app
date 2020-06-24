package io.corona.api.rest.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import io.corona.api.model.IndianStatesAndDistrictBO;

@FeignClient(url = "https://api.covidindiatracker.com/",name="INDIAN-DISTRICT-CLIENT")
public interface IndianDistrictRestClient {
	
	@GetMapping("state_data.json")
	public List<IndianStatesAndDistrictBO> getListOfIndianDistricts();

}
