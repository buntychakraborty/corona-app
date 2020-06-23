package io.corona.api.controllers;

import java.util.Comparator;
import java.util.LinkedHashMap;
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
	public CoronaRestClient client;
	@Autowired
	public IndiaCovidRestClient indiaRestClient;

	@GetMapping(StringConstant.COUNTRIES)
	public ModelAndView getAllCases() {
		ModelAndView modelAndVeiewForIndex = new ModelAndView(StringConstant.INDEX);
		logger.info("-----");
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_CASES,
				coronaService.getTotalNumberCases(CoronaService.allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_RECOVERY,
				coronaService.getTotalNumberOfRecoveredCases(CoronaService.allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_DEATHS,
				coronaService.getTotalNumberOfDeaths(CoronaService.allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_TODAYS,
				coronaService.getTotalNumberOfTodaysCase(CoronaService.allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_TODAYS_RECOVERY,
				coronaService.getTotalNumberOfTodaysRecovery(CoronaService.allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_TODAYS_DEATH,
				coronaService.getTotalNumberOfTodaysDeath(CoronaService.allCases));
		modelAndVeiewForIndex.addObject(StringConstant.TOTAL_POPULATION,
				coronaService.getTotalNumberOfPopulation(CoronaService.allCases));
		modelAndVeiewForIndex.addObject(StringConstant.ALL_CASES, CoronaService.allCases.stream()
				.sorted(Comparator.comparing(Covid19B0::getCases).reversed()).collect(Collectors.toList()));
		modelAndVeiewForIndex.addObject("topFiveCountries",CoronaService.getTopFiveCoronaAffectedCounties());
		return modelAndVeiewForIndex;
	}

	@GetMapping(StringConstant.COUNTRIES_COUNTRY)
	public ModelAndView getCountryWise(@PathVariable String country) {
		CoronaService.getCountryWiseCases(country);
		ModelAndView modelAndVeiewForCountry = new ModelAndView(StringConstant.COUNTRY_WISE);
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_CASES, CoronaService.countryWise.getCases());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_RECOVERY, CoronaService.countryWise.getRecovered());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_DEATHS, CoronaService.countryWise.getDeaths());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS, CoronaService.countryWise.getTodayCases());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS_RECOVERY,
				CoronaService.countryWise.getTodayRecovered());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS_DEATH,
				CoronaService.countryWise.getTodayDeaths());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_POPULATION, CoronaService.countryWise.getPopulation());
		modelAndVeiewForCountry.addObject(StringConstant.COUNTRY_DTO, CoronaService.countryWise);
		modelAndVeiewForCountry.addObject(StringConstant.COUNTRY_NAME, CoronaService.countryWise.getCountry());
		return modelAndVeiewForCountry;
	}

	@GetMapping(StringConstant.COUNTRIES_INDIA_STATEWISE)
	public ModelAndView getIndianCases() {
		ModelAndView modelAndVeiewForIndia = new ModelAndView(StringConstant.INDIAN_STATE_WISE);
		modelAndVeiewForIndia.addObject(StringConstant.INDIAN_STATES, CoronaService.allIndianCases);
		return modelAndVeiewForIndia;
	}

	@GetMapping(StringConstant.GRAPHS_INDIA_DISPLAY_BAR_GRAPH)
	public ModelAndView barGraph(Model model) {
		ModelAndView modelAndVeiewForCountryGraphs = new ModelAndView(StringConstant.BAR_GRAPH);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap = indiaRestClient.getIndianCases().getData().getStatewise().stream()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getActive));
		modelAndVeiewForCountryGraphs.addObject(StringConstant.SURVEY_MAP, surveyMap);
		return modelAndVeiewForCountryGraphs;
	}

	@GetMapping(StringConstant.GRAPHS_INDIA_PIE_CHART)
	public ModelAndView pieChart(Model model) {
		ModelAndView modelAndVeiewForCountryGraphs = new ModelAndView(StringConstant.PIE_CHART);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap = indiaRestClient.getIndianCases().getData().getStatewise().stream()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getActive));
		modelAndVeiewForCountryGraphs.addObject(StringConstant.SURVEY_MAP, surveyMap);
		return modelAndVeiewForCountryGraphs;
	}
}
