package io.corona.api.controllers;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
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
@EnableAsync
@RequestMapping(StringConstant.CORONA_API)
public class CoronaTrackerController {

	public static final String GRAPHS_INDIA_DISPLAY_BAR_GRAPH = "/graphs/india/displayBarGraph";

	private static final Logger logger = LoggerFactory.getLogger(CoronaTrackerController.class);

	@Autowired
	private CoronaService coronaService;

	@Autowired
	private CoronaRestClient client;

	@Autowired
	private IndiaCovidRestClient indiaRestClient;
	private static List<Covid19B0> allCases = null;
	private static List<Statewise> allIndianCases = null;
	private static Covid19B0 countryWise = null;
	private static String countryName = null;

	@PostConstruct
	@Async
	@Scheduled(cron = "1 * * * * *")
	public void statfetchWorldCases() {
		logger.info("----init---");
		allCases = client.getWorldCases();
		allIndianCases = indiaRestClient.getIndianCases().getData().getStatewise().stream()
				.sorted(Comparator.comparing(Statewise::getConfirmed).reversed()).collect(Collectors.toList());

	}

	@GetMapping(StringConstant.COUNTRIES)
	public ModelAndView getAllCases() {
		ModelAndView modelAndVeiewForIndex = new ModelAndView(StringConstant.INDEX);
		// List<Covid19B0> allCases = client.getWorldCases();
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
		getCountryWiseCases(country);
		logger.info(countryName);
		ModelAndView modelAndVeiewForCountry = new ModelAndView(StringConstant.COUNTRY_WISE);
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_CASES, countryWise.getCases());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_RECOVERY, countryWise.getRecovered());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_DEATHS, countryWise.getDeaths());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS, countryWise.getTodayCases());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS_RECOVERY, countryWise.getTodayRecovered());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_TODAYS_DEATH, countryWise.getTodayDeaths());
		modelAndVeiewForCountry.addObject(StringConstant.TOTAL_POPULATION, countryWise.getPopulation());
		modelAndVeiewForCountry.addObject(StringConstant.COUNTRY_DTO, countryWise);
		modelAndVeiewForCountry.addObject(StringConstant.COUNTRY_NAME, countryWise.getCountry());
		return modelAndVeiewForCountry;
	}

	@GetMapping(StringConstant.COUNTRIES_INDIA_STATEWISE)
	public ModelAndView getIndianCases() {
		ModelAndView modelAndVeiewForIndia = new ModelAndView(StringConstant.INDIAN_STATE_WISE);
		modelAndVeiewForIndia.addObject(StringConstant.INDIAN_STATES, allIndianCases);
		return modelAndVeiewForIndia;
	}

	@GetMapping(GRAPHS_INDIA_DISPLAY_BAR_GRAPH)
	public ModelAndView barGraph(Model model) {
		ModelAndView modelAndVeiewForCountryGraphs = new ModelAndView("barGraph");
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap = indiaRestClient.getIndianCases().getData().getStatewise().stream()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getActive));
		modelAndVeiewForCountryGraphs.addObject("surveyMap", surveyMap);
		return modelAndVeiewForCountryGraphs;
	}

	@GetMapping("/graphs/india/pieChart")
	public ModelAndView pieChart(Model model) {
		ModelAndView modelAndVeiewForCountryGraphs = new ModelAndView("pieChart");
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap = indiaRestClient.getIndianCases().getData().getStatewise().stream()
				.collect(Collectors.toMap(Statewise::getState, Statewise::getActive));
		modelAndVeiewForCountryGraphs.addObject("surveyMap", surveyMap);
		return modelAndVeiewForCountryGraphs;
	}

	public void getCountryWiseCases(String name) {
		allCases.stream().parallel().filter(s -> s.getCountry().equalsIgnoreCase(name)).forEach(cases -> {
			countryWise = cases;
			System.out.println(countryWise);
		});
	}

}
