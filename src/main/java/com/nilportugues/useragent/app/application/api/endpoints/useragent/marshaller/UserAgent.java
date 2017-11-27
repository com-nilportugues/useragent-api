package com.nilportugues.useragent.app.application.api.endpoints.useragent.marshaller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude
public class UserAgent implements Serializable {

    @JsonProperty(value = "os")
    private OS os;

    @JsonProperty(value = "device")
    private Device device;

    @JsonProperty(value = "userAgent")
    private UserAgentData userAgentData;

    @JsonProperty(value = "raw")
    private String rawString;

    private static class OS implements Serializable {
        private String family;
        private String major;
        private String minor;
        private String patch;
        private String patchMinor;

        public OS(String family, String major, String minor, String patch, String patchMinor) {
            this.family = family;
            this.major = major;
            this.minor = minor;
            this.patch = patch;
            this.patchMinor = patchMinor;
        }

        @JsonIgnore
        public String getFamily() {
            return family;
        }

        @JsonIgnore
        public String getMajor() {
            return major;
        }

        @JsonIgnore
        public String getMinor() {
            return minor;
        }

        @JsonIgnore
        public String getPatch() {
            return patch;
        }

        @JsonIgnore
        public String getPatchMinor() {
            return patchMinor;
        }
    }

    private static class Device implements Serializable {
        private String family;

        public Device(String family) {
            this.family = family;
        }

        @JsonIgnore
        public String getFamily() {
            return family;
        }
    }

    private static class UserAgentData implements Serializable {
        private String family;
        private String major;
        private String minor;
        private String patch;

        public UserAgentData(String family, String major, String minor, String patch) {
            this.family = family;
            this.major = major;
            this.minor = minor;
            this.patch = patch;
        }

        @JsonIgnore
        public String getFamily() {
            return family;
        }

        @JsonIgnore
        public String getMajor() {
            return major;
        }

        @JsonIgnore
        public String getMinor() {
            return minor;
        }

        @JsonIgnore
        public String getPatch() {
            return patch;
        }
    }
}
