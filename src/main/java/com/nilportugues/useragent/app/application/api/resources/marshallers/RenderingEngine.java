package com.nilportugues.useragent.app.application.api.resources.marshallers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

class RenderingEngine implements Serializable {

    @JsonProperty(value = "version")
    @ApiModelProperty(name = "version", position = 6)
    private String version = null;

    @JsonProperty(value = "fullVersion")
    @ApiModelProperty(name = "fullVersion", position = 7)
    private String fullVersion = null;

    @JsonProperty(value = "name")
    @ApiModelProperty(name = "name", position = 8)
    private String name = null;

    @JsonProperty(value = "website")
    @ApiModelProperty(name = "website", position = 9)
    private String website = null;

    @JsonProperty(value = "family")
    @ApiModelProperty(name = "family", position = 11)
    private String family = null;

    @JsonProperty(value = "isTridentBased")
    @ApiModelProperty(name = "isTridentBased", position = 12)
    private final boolean isTridentBased;

    @JsonProperty(value = "isWebKitBased")
    @ApiModelProperty(name = "isWebKitBased", position = 13)
    private final boolean isWebKitBased;

    public RenderingEngine(String version, String fullVersion, String name, String website, String family, boolean isTridentBased, boolean isWebKitBased) {

        if (!String.valueOf(version).equalsIgnoreCase("")) {
            this.version = version;
        }

        if (!String.valueOf(fullVersion).equalsIgnoreCase("")) {
            this.fullVersion = fullVersion;
        }

        if (!String.valueOf(family).equalsIgnoreCase("Unknown")) {
            this.family = family;
        }

        if (!String.valueOf(name).equalsIgnoreCase("Unknown")) {
            this.name = name;
        }

        if (!String.valueOf(website).equalsIgnoreCase("")) {
            this.website = website;
        }

        this.isTridentBased = isTridentBased;
        this.isWebKitBased = isWebKitBased;
    }

}
