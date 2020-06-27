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
import io.swagger.annotations.ApiOperation;

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
	@ApiOperation(value = StringConstant.THIS_CONSOLIDATES_STATISTICS_FOR_ALL_COUNTRIES)
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
		modelAndVeiewForIndex.addObject(StringConstant.TOP_FIVE_COUNTRIES,
				CoronaService.getTopFiveCoronaAffectedCounties());
		return modelAndVeiewForIndex;
	}

	@GetMapping(StringConstant.COUNTRIES_COUNTRY)
	@ApiOperation(value = StringConstant.THIS_CONSOLIDATES_STATISTICS_FOR_A_SPECIFIC_COUNTRY)
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
	@ApiOperation(value = StringConstant.THIS_CONSOLIDATES_STATISTICS_FOR_INDIA_STATE_WISE)
	public ModelAndView getIndianCases() {
		ModelAndView modelAndVeiewForIndia = new ModelAndView(StringConstant.INDIAN_STATE_WISE);
		modelAndVeiewForIndia.addObject(StringConstant.INDIAN_STATES, CoronaService.allIndianCases);
		return modelAndVeiewForIndia;
	}

	@GetMapping(StringConstant.INDIA_DISTRICTS_STATE_NAME)
	public ModelAndView getIndianDistrictWiseCases(@PathVariable String stateName) {
		ModelAndView modelAndVeiewForIndianDistricts = new ModelAndView(StringConstant.INDIAN_DISTRICT_WISE);
		CoronaService.getIndianDistrictWiseWiseCases(stateName);
		modelAndVeiewForIndianDistricts.addObject(StringConstant.INDIAN_DISTRICTS, CoronaService.indianStateWise.getDistrictData());
		modelAndVeiewForIndianDistricts.addObject(StringConstant.STATE_NAME, stateName);
		return modelAndVeiewForIndianDistricts;

	}

	@GetMapping(StringConstant.GRAPHS_INDIA_DISPLAY_BAR_GRAPH)
	public ModelAndView barGraph(Model model) {
		ModelAndView modelAndVeiewForCountryGraphs = new ModelAndView(StringConstant.BAR_GRAPH);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		Map<String, Integer> surveyMap2 = new LinkedHashMap<>();
		Map<String, Integer> surveyMap3 = new LinkedHashMap<>();
		Map<String, Integer> surveyMap4 = new LinkedHashMap<>();

		surveyMap = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getActive));

		surveyMap2 = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getConfirmed));

		surveyMap3 = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getRecovered));

		surveyMap4 = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getDeaths));

		modelAndVeiewForCountryGraphs.addObject(StringConstant.SURVEY_MAP, surveyMap);
		modelAndVeiewForCountryGraphs.addObject(StringConstant.SURVEY_MAP2, surveyMap2);
		modelAndVeiewForCountryGraphs.addObject(StringConstant.RECOVERED_CASE_MAP, surveyMap3);
		modelAndVeiewForCountryGraphs.addObject(StringConstant.DEATH_CASE_MAP, surveyMap4);
		return modelAndVeiewForCountryGraphs;
	}

	@GetMapping(StringConstant.GRAPHS_INDIA_LINE_GRAPH)
	public ModelAndView lineChart(Model model) {
		ModelAndView modelAndViewForCountryGraphs = new ModelAndView(StringConstant.LINE_GRAPH);
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		Map<String, Integer> surveyMap2 = new LinkedHashMap<>();
		Map<String, Integer> surveyMap3 = new LinkedHashMap<>();
		Map<String, Integer> surveyMap4 = new LinkedHashMap<>();

		surveyMap = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getActive));

		surveyMap2 = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getConfirmed));

		surveyMap3 = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getRecovered));

		surveyMap4 = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getDeaths));

		modelAndViewForCountryGraphs.addObject(StringConstant.SURVEY_MAP, surveyMap);
		modelAndViewForCountryGraphs.addObject(StringConstant.SURVEY_MAP2, surveyMap2);
		modelAndViewForCountryGraphs.addObject(StringConstant.RECOVERED_CASE_MAP, surveyMap3);
		modelAndViewForCountryGraphs.addObject(StringConstant.DEATH_CASE_MAP, surveyMap4);
		return modelAndViewForCountryGraphs;
	}
}
