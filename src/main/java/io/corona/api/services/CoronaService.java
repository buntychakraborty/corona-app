package io.corona.api.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.corona.api.constants.StringConstant;
import io.corona.api.model.Covid19B0;
import io.corona.api.model.IndianStatesAndDistrictBO;
import io.corona.api.model.Statewise;
import io.corona.api.rest.clients.CoronaRestClient;
import io.corona.api.rest.clients.IndiaCovidRestClient;
import io.corona.api.rest.clients.IndianDistrictRestClient;

@Service
@EnableAsync
public class CoronaService {

	@Autowired
	public CoronaRestClient client;
	@Autowired
	public IndiaCovidRestClient indiaRestClient;
	@Autowired
	public IndianDistrictRestClient indianDistrictRestClient;
	public static List<Covid19B0> allCases = null;
	public static List<Statewise> allIndianCases = null;
	public static Covid19B0 countryWise = null;
	public static String countryName = null;
	public static List<IndianStatesAndDistrictBO> listOfIndianDistricts =null;
	public static IndianStatesAndDistrictBO indianStateWise = null;
	@PostConstruct
	@Async
	@Scheduled(cron = StringConstant._1)
	public void statfetchWorldCases() {
		allCases = client.getWorldCases();
		allIndianCases = indiaRestClient.getIndianCases().getData().getStatewise().stream().parallel()
				.sorted(Comparator.comparing(Statewise::getConfirmed).reversed()).collect(Collectors.toList());
		
		listOfIndianDistricts=indianDistrictRestClient.getListOfIndianDistricts();
	}

	/**
	 * 
	 * @param listOfCallCountries
	 * @return Total Number of cases
	 */
	public Integer getTotalNumberCases(List<Covid19B0> listOfCallCountries) {
		List<Integer> listOfTotalCases = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfTotalCases.add(cases.getCases()));
		return listOfTotalCases.stream().reduce(Integer::sum).get();
	}

	/**
	 * 
	 * @param listOfCallCountries
	 * @return Total number of recovered Cases
	 */
	public Integer getTotalNumberOfRecoveredCases(List<Covid19B0> listOfCallCountries) {
		List<Integer> listOfTotalCasesRecovered = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfTotalCasesRecovered.add(cases.getRecovered()));
		return listOfTotalCasesRecovered.stream().reduce(Integer::sum).get();
	}

	public Integer getTotalNumberOfDeaths(List<Covid19B0> listOfCallCountries) {
		List<Integer> listOfTotalCasesDeaths = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfTotalCasesDeaths.add(cases.getDeaths()));
		return listOfTotalCasesDeaths.stream().reduce(Integer::sum).get();
	}

	public Integer getTotalNumberOfTodaysCase(List<Covid19B0> listOfCallCountries) {
		List<Integer> listOfTodaysCases = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfTodaysCases.add(cases.getTodayCases()));
		return listOfTodaysCases.stream().reduce(Integer::sum).get();
	}

	public Integer getTotalNumberOfTodaysRecovery(List<Covid19B0> listOfCallCountries) {
		List<Integer> listOfTodaysRecovery = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfTodaysRecovery.add(cases.getTodayRecovered()));
		return listOfTodaysRecovery.stream().reduce(Integer::sum).get();
	}

	public Integer getTotalNumberOfTodaysDeath(List<Covid19B0> listOfCallCountries) {
		List<Integer> listOfTodaysDeath = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfTodaysDeath.add(cases.getTodayDeaths()));
		return listOfTodaysDeath.stream().reduce(Integer::sum).get();
	}

	public Long getTotalNumberOfPopulation(List<Covid19B0> listOfCallCountries) {
		List<Long> listOfPopulation = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfPopulation.add(cases.getPopulation()));
		return listOfPopulation.stream().reduce(Long::sum).get();
	}

	public static void getCountryWiseCases(String name) {
		allCases.stream().parallel().filter(s -> s.getCountry().equalsIgnoreCase(name)).forEach(cases -> {
			countryWise = cases;
			System.out.println(countryWise);
		});
	}

	public static List<Covid19B0> getTopFiveCoronaAffectedCounties() {
		return allCases.parallelStream().sorted(Comparator.comparing(Covid19B0::getCases).reversed()).limit(5)
				.collect(Collectors.toList());
	}
	
	public static void getIndianDistrictWiseWiseCases(String stateName) {
		listOfIndianDistricts.stream().parallel().takeWhile(s -> s.getState().equalsIgnoreCase(stateName)).forEach(cases -> {
			indianStateWise = cases;
			System.out.println(indianStateWise);
		});
	}
}
