
package io.corona.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "state",
    "active",
    "confirmed",
    "recovered",
    "deaths",
    "aChanges",
    "cChanges",
    "rChanges",
    "dChanges",
    "districtData",
    "cchanges",
    "dchanges",
    "rchanges",
    "achanges"
})
public class IndianStatesAndDistrictBO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("state")
    private String state;
    @JsonProperty("active")
    private Integer active;
    @JsonProperty("confirmed")
    private Integer confirmed;
    @JsonProperty("recovered")
    private Integer recovered;
    @JsonProperty("deaths")
    private Integer deaths;
    @JsonProperty("aChanges")
    private Integer aChanges;
    @JsonProperty("cChanges")
    private Integer cChanges;
    @JsonProperty("rChanges")
    private Integer rChanges;
    @JsonProperty("dChanges")
    private Integer dChanges;
    @JsonProperty("districtData")
    private List<DistrictDatum> districtData = null;
    @JsonProperty("cchanges")
    private Integer cchanges;
    @JsonProperty("dchanges")
    private Integer dchanges;
    @JsonProperty("rchanges")
    private Integer rchanges;
    @JsonProperty("achanges")
    private Integer achanges;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("active")
    public Integer getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Integer active) {
        this.active = active;
    }

    @JsonProperty("confirmed")
    public Integer getConfirmed() {
        return confirmed;
    }

    @JsonProperty("confirmed")
    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    @JsonProperty("recovered")
    public Integer getRecovered() {
        return recovered;
    }

    @JsonProperty("recovered")
    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    @JsonProperty("deaths")
    public Integer getDeaths() {
        return deaths;
    }

    @JsonProperty("deaths")
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    @JsonProperty("aChanges")
    public Integer getAChanges() {
        return aChanges;
    }

    @JsonProperty("aChanges")
    public void setAChanges(Integer aChanges) {
        this.aChanges = aChanges;
    }

    @JsonProperty("cChanges")
    public Integer getCChanges() {
        return cChanges;
    }

    @JsonProperty("cChanges")
    public void setCChanges(Integer cChanges) {
        this.cChanges = cChanges;
    }

    @JsonProperty("rChanges")
    public Integer getRChanges() {
        return rChanges;
    }

    @JsonProperty("rChanges")
    public void setRChanges(Integer rChanges) {
        this.rChanges = rChanges;
    }

    @JsonProperty("dChanges")
    public Integer getDChanges() {
        return dChanges;
    }

    @JsonProperty("dChanges")
    public void setDChanges(Integer dChanges) {
        this.dChanges = dChanges;
    }

    @JsonProperty("districtData")
    public List<DistrictDatum> getDistrictData() {
        return districtData;
    }

    @JsonProperty("districtData")
    public void setDistrictData(List<DistrictDatum> districtData) {
        this.districtData = districtData;
    }

    @JsonProperty("cchanges")
    public Integer getCchanges() {
        return cchanges;
    }

    @JsonProperty("cchanges")
    public void setCchanges(Integer cchanges) {
        this.cchanges = cchanges;
    }

    @JsonProperty("dchanges")
    public Integer getDchanges() {
        return dchanges;
    }

    @JsonProperty("dchanges")
    public void setDchanges(Integer dchanges) {
        this.dchanges = dchanges;
    }

    @JsonProperty("rchanges")
    public Integer getRchanges() {
        return rchanges;
    }

    @JsonProperty("rchanges")
    public void setRchanges(Integer rchanges) {
        this.rchanges = rchanges;
    }

    @JsonProperty("achanges")
    public Integer getAchanges() {
        return achanges;
    }

    @JsonProperty("achanges")
    public void setAchanges(Integer achanges) {
        this.achanges = achanges;
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
