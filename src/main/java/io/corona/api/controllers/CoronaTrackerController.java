package io.corona.api.controllers;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.corona.api.constants.StringConstant;
import io.corona.api.model.Covid19B0;
import io.corona.api.model.Statewise;
import io.corona.api.rest.clients.CoronaRestClient;
import io.corona.api.rest.clients.IndiaCovidRestClient;
import io.corona.api.services.CoronaService;

@RestController
@RequestMapping(StringConstant.CORONA_API)
public class CoronaTrackerController {

	private static final Logger logger = LoggerFactory.getLogger(CoronaTrackerController.class);

	@Autowired
	private CoronaService coronaService;

	@Autowired
	private CoronaRestClient client;

	@Autowired
	private IndiaCovidRestClient indiaRestClient;

	@GetMapping(StringConstant.COUNTRIES)
	public ModelAndView getAllCases() {
		ModelAndView modelAndVeiewForIndex = new ModelAndView(StringConstant.INDEX);
		List<Covid19B0> allCases = client.getWorldCases();
		logger.info("-----");
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_CASES, coronaService.getTotalNumberCases(allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_RECOVERY,
				coronaService.getTotalNumberOfRecoveredCases(allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_DEATHS, coronaService.getTotalNumberOfDeaths(allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_TODAYS,
				coronaService.getTotalNumberOfTodaysCase(allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_TODAYS_RECOVERY,
				coronaService.getTotalNumberOfTodaysRecovery(allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_TODAYS_DEATH,
				coronaService.getTotalNumberOfTodaysDeath(allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_POPULATION,
				coronaService.getTotalNumberOfPopulation(allCases));
		modelAndVeiewForIndex.addObject(StringConstant.ALL_CASES, allCases.stream()
				.sorted(Comparator.comparing(Covid19B0::getCases).reversed()).collect(Collectors.toList()));
		return modelAndVeiewForIndex;
	}

	@GetMapping(StringConstant.COUNTRIES_COUNTRY)
	public ModelAndView getCountryWise(@PathVariable String country) {
		ModelAndView modelAndVeiewForCountry = new ModelAndView(StringConstant.COUNTRY_WISE);
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_CASES, client.getCountryWiseCases(country).getCases());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_RECOVERY,
				client.getCountryWiseCases(country).getRecovered());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_DEATHS, client.getCountryWiseCases(country).getDeaths());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS,
				client.getCountryWiseCases(country).getTodayCases());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS_RECOVERY,
				client.getCountryWiseCases(country).getTodayRecovered());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS_DEATH,
				client.getCountryWiseCases(country).getTodayDeaths());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_POPULATION,
				client.getCountryWiseCases(country).getPopulation());
		modelAndVeiewForCountry.addObject(StringConstant.COUNTRY_DTO, client.getCountryWiseCases(country));
		modelAndVeiewForCountry.addObject(StringConstant.COUNTRY_NAME,
				client.getCountryWiseCases(country).getCountry());
		return modelAndVeiewForCountry;
	}

	@GetMapping(StringConstant.COUNTRIES_INDIA_STATEWISE)
	public ModelAndView getIndianCases() {
		ModelAndView modelAndVeiewForIndia = new ModelAndView(StringConstant.INDIAN_STATE_WISE);
		modelAndVeiewForIndia.addObject(StringConstant.INDIAN_STATES,
				indiaRestClient.getIndianCases().getData().getStatewise().stream().sorted(Comparator.comparing(Statewise::getConfirmed).reversed()).collect(Collectors.toList()));
		return modelAndVeiewForIndia;
	}
	
	@GetMapping("/graphs/india/displayBarGraph")
	public ModelAndView barGraph(Model model) {
		ModelAndView modelAndVeiewForCountryGraphs = new ModelAndView("barGraph");
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap=indiaRestClient.getIndianCases().getData().getStatewise().stream().collect(Collectors.toMap(Statewise::getState, Statewise::getActive));
		modelAndVeiewForCountryGraphs.addObject("surveyMap", surveyMap);
		return modelAndVeiewForCountryGraphs;
	}
	@GetMapping("/graphs/india/pieChart")
	public ModelAndView pieChart(Model model) {
		ModelAndView modelAndVeiewForCountryGraphs = new ModelAndView("pieChart");
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap=indiaRestClient.getIndianCases().getData().getStatewise().stream().collect(Collectors.toMap(Statewise::getState, Statewise::getActive));
		modelAndVeiewForCountryGraphs.addObject("surveyMap", surveyMap);
		return modelAndVeiewForCountryGraphs;
	}
}
