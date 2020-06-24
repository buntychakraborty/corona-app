
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
@JsonPropertyOrder({
    "id",
    "state",
    "name",
    "confirmed",
    "recovered",
    "deaths",
    "oldConfirmed",
    "oldRecovered",
    "oldDeaths",
    "zone"
})
public class DistrictDatum {

    @JsonProperty("id")
    private String id;
    @JsonProperty("state")
    private Object state;
    @JsonProperty("name")
    private String name;
    @JsonProperty("confirmed")
    private Integer confirmed;
    @JsonProperty("recovered")
    private Object recovered;
    @JsonProperty("deaths")
    private Object deaths;
    @JsonProperty("oldConfirmed")
    private Integer oldConfirmed;
    @JsonProperty("oldRecovered")
    private Object oldRecovered;
    @JsonProperty("oldDeaths")
    private Object oldDeaths;
    @JsonProperty("zone")
    private String zone;
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
    public Object getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(Object state) {
        this.state = state;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
    public Object getRecovered() {
        return recovered;
    }

    @JsonProperty("recovered")
    public void setRecovered(Object recovered) {
        this.recovered = recovered;
    }

    @JsonProperty("deaths")
    public Object getDeaths() {
        return deaths;
    }

    @JsonProperty("deaths")
    public void setDeaths(Object deaths) {
        this.deaths = deaths;
    }

    @JsonProperty("oldConfirmed")
    public Integer getOldConfirmed() {
        return oldConfirmed;
    }

    @JsonProperty("oldConfirmed")
    public void setOldConfirmed(Integer oldConfirmed) {
        this.oldConfirmed = oldConfirmed;
    }

    @JsonProperty("oldRecovered")
    public Object getOldRecovered() {
        return oldRecovered;
    }

    @JsonProperty("oldRecovered")
    public void setOldRecovered(Object oldRecovered) {
        this.oldRecovered = oldRecovered;
    }

    @JsonProperty("oldDeaths")
    public Object getOldDeaths() {
        return oldDeaths;
    }

    @JsonProperty("oldDeaths")
    public void setOldDeaths(Object oldDeaths) {
        this.oldDeaths = oldDeaths;
    }

    @JsonProperty("zone")
    public String getZone() {
        return zone;
    }

    @JsonProperty("zone")
    public void setZone(String zone) {
        this.zone = zone;
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
