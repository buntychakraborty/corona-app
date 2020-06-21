
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
    "success",
    "data",
    "lastRefreshed",
    "lastOriginUpdate"
})
public class IndiaCoivid19 {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("data")
    private Data data;
    @JsonProperty("lastRefreshed")
    private String lastRefreshed;
    @JsonProperty("lastOriginUpdate")
    private String lastOriginUpdate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("lastRefreshed")
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    @JsonProperty("lastRefreshed")
    public void setLastRefreshed(String lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    @JsonProperty("lastOriginUpdate")
    public String getLastOriginUpdate() {
        return lastOriginUpdate;
    }

    @JsonProperty("lastOriginUpdate")
    public void setLastOriginUpdate(String lastOriginUpdate) {
        this.lastOriginUpdate = lastOriginUpdate;
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
