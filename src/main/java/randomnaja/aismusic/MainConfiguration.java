package randomnaja.aismusic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

import javax.validation.constraints.NotNull;

public class MainConfiguration extends Configuration {

    @NotNull
    @JsonProperty
    private String deviceNumber;

    @NotNull
    @JsonProperty
    private String msisdn;

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public String getMsisdn() {
        return msisdn;
    }
}
