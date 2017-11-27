package com.nilportugues.useragent.app.application.api.endpoints.useragent.marshaller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAgent implements Serializable{

    @JsonProperty(value = "os")
    private OS os;

    @JsonProperty(value = "device")
    private Device device;

    @JsonProperty(value = "userAgent")
    private UserAgentData userAgentData;

    @JsonProperty(value = "raw")
    private RawString rawString;

    private static class OS {

    }

    private static class Device {

    }

    private static class UserAgentData {

    }

    private static class RawString {

    }
}
