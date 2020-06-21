package io.corona.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.corona.api.model.Covid19B0;

@Service
public class CoronaService {

	public Integer getTotalNumberCases(List<Covid19B0> listOfCallCountries) {
		List<Integer> listOfTotalCases = new ArrayList<>();
		listOfCallCountries.stream().forEach(cases -> listOfTotalCases.add(cases.getCases()));
		return listOfTotalCases.stream().reduce(Integer::sum).get();
	}

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
}
