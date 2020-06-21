
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
    "source",
    "lastRefreshed",
    "total",
    "statewise"
})
public class Data {

    @JsonProperty("source")
    private String source;
    @JsonProperty("lastRefreshed")
    private String lastRefreshed;
    @JsonProperty("total")
    private Total total;
    @JsonProperty("statewise")
    private List<Statewise> statewise = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("lastRefreshed")
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    @JsonProperty("lastRefreshed")
    public void setLastRefreshed(String lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    @JsonProperty("total")
    public Total getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Total total) {
        this.total = total;
    }

    @JsonProperty("statewise")
    public List<Statewise> getStatewise() {
        return statewise;
    }

    @JsonProperty("statewise")
    public void setStatewise(List<Statewise> statewise) {
        this.statewise = statewise;
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
