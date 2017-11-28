package com.nilportugues.useragent.app.application.api.resources.marshallers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSearchAgentRequest implements Serializable {

    @JsonProperty(value = "userAgent")
    @ApiModelProperty(name = "userAgent", required = true, position = 0)
    private String userAgent;

    @JsonProperty(value = "acceptLanguage")
    @ApiModelProperty(name = "acceptLanguage", required = false, position = 1)
    private String acceptLanguage;

    public UserSearchAgentRequest() {
    }

    @JsonIgnore
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @JsonIgnore
    public String getUserAgent() {
        return userAgent;
    }

    @JsonIgnore
    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    @JsonIgnore
    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }
}
