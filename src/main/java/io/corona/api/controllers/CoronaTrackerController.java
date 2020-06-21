package io.corona.api.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.corona.api.model.Covid19B0;
import io.corona.api.rest.clients.CoronaRestClient;
import io.corona.api.rest.clients.IndiaCovidRestClient;
import io.corona.api.services.CoronaService;

@RestController
@RequestMapping("/corona-api")
public class CoronaTrackerController {
	
	private static final Logger logger=LoggerFactory.getLogger(CoronaTrackerController.class);
	
	@Autowired
	private CoronaService coronaService;
	
	@Autowired
	private CoronaRestClient client;

	@Autowired
	private IndiaCovidRestClient indiaRestClient;

	@GetMapping("/countries")
	public ModelAndView getAllCases() {
		ModelAndView modelAndVeiewForIndex= new ModelAndView("index");
		List<Covid19B0> allCases = client.getWorldCases();
		logger.info("-----");
		modelAndVeiewForIndex.addObject("totalCases",coronaService.getTotalNumberCases(allCases));
		modelAndVeiewForIndex.addObject("totalRecovery",coronaService.getTotalNumberOfRecoveredCases(allCases));
		modelAndVeiewForIndex.addObject("totalDeaths",coronaService.getTotalNumberOfDeaths(allCases));
		modelAndVeiewForIndex.addObject("totalTodays",coronaService.getTotalNumberOfTodaysCase(allCases));
		modelAndVeiewForIndex.addObject("totalTodaysRecovery",coronaService.getTotalNumberOfTodaysRecovery(allCases));
		modelAndVeiewForIndex.addObject("totalTodaysDeath",coronaService.getTotalNumberOfTodaysDeath(allCases));
		modelAndVeiewForIndex.addObject("totalPopulation",coronaService.getTotalNumberOfPopulation(allCases));
		modelAndVeiewForIndex.addObject("allCases",allCases);
		
		return modelAndVeiewForIndex;
	}

	@GetMapping("/countries/{country}")
	public ModelAndView getCountryWise(@PathVariable String country) {
		ModelAndView modelAndVeiewForCountry= new ModelAndView("countryWise");
		modelAndVeiewForCountry.addObject("totalCases",client.getCountryWiseCases(country).getCases());
		modelAndVeiewForCountry.addObject("totalRecovery",client.getCountryWiseCases(country).getRecovered());
		modelAndVeiewForCountry.addObject("totalDeaths",client.getCountryWiseCases(country).getDeaths());
		modelAndVeiewForCountry.addObject("totalTodays",client.getCountryWiseCases(country).getTodayCases());
		modelAndVeiewForCountry.addObject("totalTodaysRecovery",client.getCountryWiseCases(country).getTodayRecovered());
		modelAndVeiewForCountry.addObject("totalTodaysDeath",client.getCountryWiseCases(country).getTodayDeaths());
		modelAndVeiewForCountry.addObject("totalPopulation",client.getCountryWiseCases(country).getPopulation());
		modelAndVeiewForCountry.addObject("countryDTO",client.getCountryWiseCases(country));
		modelAndVeiewForCountry.addObject("countryName",client.getCountryWiseCases(country).getCountry());
		return modelAndVeiewForCountry;
	}

	@GetMapping("/countries/india/statewise")
	public ModelAndView getIndianCases() {
		ModelAndView modelAndVeiewForIndia= new ModelAndView("indianStateWise");
		modelAndVeiewForIndia.addObject("indianStates",indiaRestClient.getIndianCases().getData().getStatewise());
		
		//indiaRestClient.getIndianCases().getData().getStatewise().forEach(s->System.out.println(s));
		
		return modelAndVeiewForIndia;
	}
	
	

}
