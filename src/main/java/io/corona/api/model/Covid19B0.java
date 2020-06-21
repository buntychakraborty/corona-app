
package io.corona.api.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "updated", "country", "countryInfo", "cases", "todayCases", "deaths", "todayDeaths", "recovered",
		"todayRecovered", "active", "critical", "casesPerOneMillion", "deathsPerOneMillion", "tests",
		"testsPerOneMillion", "population", "continent", "oneCasePerPeople", "oneDeathPerPeople", "oneTestPerPeople",
		"activePerOneMillion", "recoveredPerOneMillion", "criticalPerOneMillion" })
public class Covid19B0 {

	@JsonProperty("updated")
	private Long updated;
	@JsonProperty("country")
	private String country;
	@JsonProperty("countryInfo")
	private CountryInfo countryInfo;
	@JsonProperty("cases")
	private Integer cases;
	@JsonProperty("todayCases")
	private Integer todayCases;
	@JsonProperty("deaths")
	private Integer deaths;
	@JsonProperty("todayDeaths")
	private Integer todayDeaths;
	@JsonProperty("recovered")
	private Integer recovered;
	@JsonProperty("todayRecovered")
	private Integer todayRecovered;
	@JsonProperty("active")
	private Integer active;
	@JsonProperty("critical")
	private Integer critical;
	@JsonProperty("casesPerOneMillion")
	private Integer casesPerOneMillion;
	@JsonProperty("deathsPerOneMillion")
	private Integer deathsPerOneMillion;
	@JsonProperty("tests")
	private Integer tests;
	@JsonProperty("testsPerOneMillion")
	private Integer testsPerOneMillion;
	@JsonProperty("population")
	private Long population;
	@JsonProperty("continent")
	private String continent;
	@JsonProperty("oneCasePerPeople")
	private Integer oneCasePerPeople;
	@JsonProperty("oneDeathPerPeople")
	private Integer oneDeathPerPeople;
	@JsonProperty("oneTestPerPeople")
	private Integer oneTestPerPeople;
	@JsonProperty("activePerOneMillion")
	private Double activePerOneMillion;
	@JsonProperty("recoveredPerOneMillion")
	private Double recoveredPerOneMillion;
	@JsonProperty("criticalPerOneMillion")
	private Double criticalPerOneMillion;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("updated")
	public Long getUpdated() {
		return updated;
	}

	@JsonProperty("updated")
	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("countryInfo")
	public CountryInfo getCountryInfo() {
		return countryInfo;
	}

	@JsonProperty("countryInfo")
	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	@JsonProperty("cases")
	public Integer getCases() {
		return cases;
	}

	@JsonProperty("cases")
	public void setCases(Integer cases) {
		this.cases = cases;
	}

	@JsonProperty("todayCases")
	public Integer getTodayCases() {
		return todayCases;
	}

	@JsonProperty("todayCases")
	public void setTodayCases(Integer todayCases) {
		this.todayCases = todayCases;
	}

	@JsonProperty("deaths")
	public Integer getDeaths() {
		return deaths;
	}

	@JsonProperty("deaths")
	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	@JsonProperty("todayDeaths")
	public Integer getTodayDeaths() {
		return todayDeaths;
	}

	@JsonProperty("todayDeaths")
	public void setTodayDeaths(Integer todayDeaths) {
		this.todayDeaths = todayDeaths;
	}

	@JsonProperty("recovered")
	public Integer getRecovered() {
		return recovered;
	}

	@JsonProperty("recovered")
	public void setRecovered(Integer recovered) {
		this.recovered = recovered;
	}

	@JsonProperty("todayRecovered")
	public Integer getTodayRecovered() {
		return todayRecovered;
	}

	@JsonProperty("todayRecovered")
	public void setTodayRecovered(Integer todayRecovered) {
		this.todayRecovered = todayRecovered;
	}

	@JsonProperty("active")
	public Integer getActive() {
		return active;
	}

	@JsonProperty("active")
	public void setActive(Integer active) {
		this.active = active;
	}

	@JsonProperty("critical")
	public Integer getCritical() {
		return critical;
	}

	@JsonProperty("critical")
	public void setCritical(Integer critical) {
		this.critical = critical;
	}

	@JsonProperty("casesPerOneMillion")
	public Integer getCasesPerOneMillion() {
		return casesPerOneMillion;
	}

	@JsonProperty("casesPerOneMillion")
	public void setCasesPerOneMillion(Integer casesPerOneMillion) {
		this.casesPerOneMillion = casesPerOneMillion;
	}

	@JsonProperty("deathsPerOneMillion")
	public Integer getDeathsPerOneMillion() {
		return deathsPerOneMillion;
	}

	@JsonProperty("deathsPerOneMillion")
	public void setDeathsPerOneMillion(Integer deathsPerOneMillion) {
		this.deathsPerOneMillion = deathsPerOneMillion;
	}

	@JsonProperty("tests")
	public Integer getTests() {
		return tests;
	}

	@JsonProperty("tests")
	public void setTests(Integer tests) {
		this.tests = tests;
	}

	@JsonProperty("testsPerOneMillion")
	public Integer getTestsPerOneMillion() {
		return testsPerOneMillion;
	}

	@JsonProperty("testsPerOneMillion")
	public void setTestsPerOneMillion(Integer testsPerOneMillion) {
		this.testsPerOneMillion = testsPerOneMillion;
	}

	@JsonProperty("population")
	public Long getPopulation() {
		return population;
	}

	@JsonProperty("population")
	public void setPopulation(Long population) {
		this.population = population;
	}

	@JsonProperty("continent")
	public String getContinent() {
		return continent;
	}

	@JsonProperty("continent")
	public void setContinent(String continent) {
		this.continent = continent;
	}

	@JsonProperty("oneCasePerPeople")
	public Integer getOneCasePerPeople() {
		return oneCasePerPeople;
	}

	@JsonProperty("oneCasePerPeople")
	public void setOneCasePerPeople(Integer oneCasePerPeople) {
		this.oneCasePerPeople = oneCasePerPeople;
	}

	@JsonProperty("oneDeathPerPeople")
	public Integer getOneDeathPerPeople() {
		return oneDeathPerPeople;
	}

	@JsonProperty("oneDeathPerPeople")
	public void setOneDeathPerPeople(Integer oneDeathPerPeople) {
		this.oneDeathPerPeople = oneDeathPerPeople;
	}

	@JsonProperty("oneTestPerPeople")
	public Integer getOneTestPerPeople() {
		return oneTestPerPeople;
	}

	@JsonProperty("oneTestPerPeople")
	public void setOneTestPerPeople(Integer oneTestPerPeople) {
		this.oneTestPerPeople = oneTestPerPeople;
	}

	@JsonProperty("activePerOneMillion")
	public Double getActivePerOneMillion() {
		return activePerOneMillion;
	}

	@JsonProperty("activePerOneMillion")
	public void setActivePerOneMillion(Double activePerOneMillion) {
		this.activePerOneMillion = activePerOneMillion;
	}

	@JsonProperty("recoveredPerOneMillion")
	public Double getRecoveredPerOneMillion() {
		return recoveredPerOneMillion;
	}

	@JsonProperty("recoveredPerOneMillion")
	public void setRecoveredPerOneMillion(Double recoveredPerOneMillion) {
		this.recoveredPerOneMillion = recoveredPerOneMillion;
	}

	@JsonProperty("criticalPerOneMillion")
	public Double getCriticalPerOneMillion() {
		return criticalPerOneMillion;
	}

	@JsonProperty("criticalPerOneMillion")
	public void setCriticalPerOneMillion(Double criticalPerOneMillion) {
		this.criticalPerOneMillion = criticalPerOneMillion;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
