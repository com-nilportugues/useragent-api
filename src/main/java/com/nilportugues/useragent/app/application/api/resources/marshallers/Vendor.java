package com.nilportugues.useragent.app.application.api.resources.marshallers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Vendor {
    @JsonProperty(value = "name")
    @ApiModelProperty(name = "name", position = 2)
    private String name = null;

    @JsonProperty(value = "website")
    @ApiModelProperty(name = "website", position = 3)
    private String website = null;

    public Vendor(String name, String website) {

        if (!String.valueOf(name).equalsIgnoreCase("Unknown")) {
            this.name = name;
        }
        if (!String.valueOf(website).equalsIgnoreCase("")) {
            this.website = website;
        }
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getWebsite() {
        return website;
    }
}
