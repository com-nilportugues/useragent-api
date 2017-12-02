package com.nilportugues.useragent.app.application.api.resources.marshallers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSearchAgentResponse implements Serializable {

    @JsonProperty(value = "userAgent")
    @ApiModelProperty(name = "userAgent", position = 0)
    private String userAgent;

    @JsonProperty(value = "locale")
    @ApiModelProperty(name = "locale", position = 1)
    private Locale locale;

    @JsonProperty(value = "device")
    @ApiModelProperty(name = "device", position = 2)
    private Device device;

    @JsonProperty(value = "bot")
    @ApiModelProperty(name = "bot", position = 3)
    private Bot bot;

    @JsonProperty(value = "os")
    @ApiModelProperty(name = "os", position = 4)
    private OS os;

    @JsonProperty(value = "browser")
    @ApiModelProperty(name = "browser", position = 5)
    private Browser browser;

    public UserSearchAgentResponse(UserAgentDetectionResult result) {
        userAgent = result.getRaw();

        locale = new Locale(
            result.getLocale().getCountry().getLabel(),
            result.getLocale().getLanguage().getLabel());

        device = new Device(
            result.getDevice().getDeviceType().getLabel(),
            result.getDevice().getDeviceType().isMobile(),
            result.getDevice().getBrand().getLabel(),
            result.getDevice().getBrand().getWebsite(),
            result.getDevice().getManufacturer().getLabel(),
            result.getDevice().getManufacturer().getWebsite(),
            result.getDevice().getArchitecture(),
            result.getDevice().getDevice(),
            result.getDevice().isTouch());

        bot = new Bot(
            result.getBot().getVersion(),
            result.getBot().getFamily().isNefarious(),
            result.getBot().getFamily().getLabel(),
            result.getBot().getDescription(),
            result.getBot().getVersion(),
            result.getBot().getUrl());

        os = new OS(
            result.getOperatingSystem().getVendor().getLabel(),
            result.getOperatingSystem().getVendor().getWebsite(),
            result.getOperatingSystem().getFamily().getLabel(),
            result.getOperatingSystem().getFamily().isLinuxKernel(),
            result.getOperatingSystem().getDescription(),
            result.getOperatingSystem().getVersion());

        browser = new Browser(
            result.getBrowser().getVersion(),
            result.getBrowser().getFullVersion(),
            result.getBrowser().getDescription(),
            result.getBrowser().getVendor().getLabel(),
            result.getBrowser().getVendor().getWebsite(),
            result.getBrowser().getFamily().getLabel(),
            result.getBrowser().getFamily().isGecko(),
            result.getBrowser().getFamily().isRobot(),
            result.getBrowser().getFamily().isTrident(),
            result.getBrowser().getFamily().isWebKit(),
            result.getBrowser().getRenderingEngine().getVendor().getLabel(),
            result.getBrowser().getRenderingEngine().getVendor().getWebsite(),
            result.getBrowser().getRenderingEngine().getVersion(),
            result.getBrowser().getRenderingEngine().getFullVersion(),
            result.getBrowser().getRenderingEngine().getFamily().getLabel(),
            result.getBrowser().getRenderingEngine().getFamily().isTridentBased(),
            result.getBrowser().getRenderingEngine().getFamily().isWebkitBased(),
            result.getBrowser().isInWebView());
    }

    @JsonIgnore
    public OS getOs() {
        return os;
    }

    @JsonIgnore
    public String getUserAgent() {
        return userAgent;
    }

    @JsonIgnore
    public Locale getLocale() {
        return locale;
    }

    @JsonIgnore
    public Device getDevice() {
        return device;
    }

    @JsonIgnore
    public Bot getBot() {
        return bot;
    }

    @JsonIgnore
    public Browser getBrowser() {
        return browser;
    }

    private static class OS implements Serializable {
        @JsonProperty(value = "vendor")
        @ApiModelProperty(name = "vendor", position = 0)
        private String vendor;

        @JsonProperty(value = "website")
        @ApiModelProperty(name = "website", position = 1)
        private String website;

        @JsonProperty(value = "name")
        @ApiModelProperty(name = "name", position = 1)
        private String name;

        @JsonProperty(value = "description")
        @ApiModelProperty(name = "description", position = 2)
        private String description;

        @JsonProperty(value = "version")
        @ApiModelProperty(name = "version", position = 3)
        private String version;

        @JsonProperty(value = "isLinux")
        @ApiModelProperty(name = "isLinux", position = 5)
        private boolean isLinux;

        public OS(final String vendor,
            final String website,
            final String family,
            final boolean isLinux,
            final String description,
            final String version) {

            if (!String.valueOf(vendor).equalsIgnoreCase("Unknown")) {
                this.vendor = vendor;
            }

            if (!String.valueOf(family).equalsIgnoreCase("")) {
                this.name = family;
            }

            if (!String.valueOf(description).equalsIgnoreCase("")) {
                this.description = description;
            }

            if (!String.valueOf(version).equalsIgnoreCase("")) {
                this.version = version;
            }

            if (!String.valueOf(website).equalsIgnoreCase("")) {
                this.website = website;
            }

            if (!String.valueOf(version).equalsIgnoreCase("")) {
                this.version = version;
            }

            this.isLinux = isLinux;
        }

        @JsonIgnore
        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        @JsonIgnore
        public boolean isLinux() {
            return isLinux;
        }

        public void setLinux(boolean linux) {
            isLinux = linux;
        }

        @JsonIgnore
        public String getVendor() {
            return vendor;
        }

        public void setVendor(final String vendor) {
            this.vendor = vendor;
        }

        @JsonIgnore
        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        @JsonIgnore
        public String getDescription() {
            return description;
        }

        public void setDescription(final String description) {
            this.description = description;
        }

        @JsonIgnore
        public String getVersion() {
            return version;
        }

        public void setVersion(final String version) {
            this.version = version;
        }
    }

    private static class Browser implements Serializable {

        @JsonProperty(value = "version")
        @ApiModelProperty(name = "version", position = 0)
        private String version = null;

        @JsonProperty(value = "fullVersion")
        @ApiModelProperty(name = "fullVersion", position = 1)
        private String fullVersion = null;

        @JsonProperty(value = "name")
        @ApiModelProperty(name = "name", position = 4)
        private String name = null;

        @JsonProperty(value = "description")
        @ApiModelProperty(name = "description", position = 5)
        private String description = null;

        @JsonProperty(value = "isWebView")
        @ApiModelProperty(name = "isWebView", position = 14)
        private boolean inWebView;

        @JsonProperty(value = "isRobot")
        @ApiModelProperty(name = "isRobot", position = 15)
        private final boolean isRobot;

        @JsonProperty(value = "isGecko")
        @ApiModelProperty(name = "isGecko", position = 16)
        private final boolean isGecko;

        @JsonProperty(value = "isTrident")
        @ApiModelProperty(name = "isTrident", position = 17)
        private final boolean isTrident;

        @JsonProperty(value = "isWebkit")
        @ApiModelProperty(name = "isWebkit", position = 18)
        private final boolean isWebkit;

        @JsonProperty(value = "vendor")
        @ApiModelProperty(name = "vendor", position = 19)
        private final Vendor vendor;

        @JsonProperty(value = "renderingEngine")
        @ApiModelProperty(name = "renderingEngine", position = 20)
        private final RenderingEngine renderingEngine;

        public Browser(final String version,
            final String fullVersion,
            final String description,
            final String vendorName,
            final String vendorWebsite,
            final String name,
            final boolean isGecko,
            final boolean isRobot,
            final boolean isTrident,
            final boolean isWebkit,
            final String renderingEngineName,
            final String renderingEngineWebsite,
            final String renderingEngineVersion,
            final String renderingEngineFullVersion,
            final String renderingEngineFamily,
            final boolean isRenderingEngineTridentBased,
            final boolean isRenderingEngineWebKitBased,
            final boolean inWebView) {

            if (!String.valueOf(version).equalsIgnoreCase("")) {
                this.version = version;
            }

            if (!String.valueOf(fullVersion).equalsIgnoreCase("")) {
                this.fullVersion = fullVersion;
            }

            if (!String.valueOf(name).equalsIgnoreCase("")) {
                this.name = name;
            }

            if (!String.valueOf(description).equalsIgnoreCase("")) {
                this.description = description;
            }

            this.renderingEngine = new RenderingEngine(
                renderingEngineVersion,
                renderingEngineFullVersion,
                renderingEngineName,
                renderingEngineWebsite,
                renderingEngineFamily,
                isRenderingEngineTridentBased,
                isRenderingEngineWebKitBased);

            this.vendor = new Vendor(vendorName, vendorWebsite);

            this.inWebView = inWebView;
            this.isGecko = isGecko;
            this.isRobot = isRobot;
            this.isTrident = isTrident;
            this.isWebkit = isWebkit;
        }

        @JsonIgnore
        public Vendor getVendor() {
            return vendor;
        }

        @JsonIgnore
        public RenderingEngine getRenderingEngine() {
            return renderingEngine;
        }

        @JsonIgnore
        public boolean isGecko() {
            return isGecko;
        }

        @JsonIgnore
        public boolean isRobot() {
            return isRobot;
        }

        @JsonIgnore
        public boolean isTrident() {
            return isTrident;
        }

        @JsonIgnore
        public boolean isWebkit() {
            return isWebkit;
        }

        @JsonIgnore
        public String getVersion() {
            return version;
        }

        @JsonIgnore
        public String getFullVersion() {
            return fullVersion;
        }

        @JsonIgnore
        public String getName() {
            return name;
        }

        @JsonIgnore
        public String getDescription() {
            return description;
        }

        @JsonIgnore
        public boolean isInWebView() {
            return inWebView;
        }

    }

    private static class Bot implements Serializable {

        @JsonProperty(value = "isBot")
        @ApiModelProperty(name = "isBot", position = 0)
        private boolean isBot;

        @JsonProperty(value = "vendor")
        @ApiModelProperty(name = "vendor", position = 2)
        private String vendor = null;

        @JsonProperty(value = "isEvil")
        @ApiModelProperty(name = "isEvil", position = 1)
        private boolean isNefarius;

        @JsonProperty(value = "name")
        @ApiModelProperty(name = "name", position = 3)
        private String family = null;

        @JsonProperty(value = "description")
        @ApiModelProperty(name = "description", position = 4)
        private String description = null;

        @JsonProperty(value = "version")
        @ApiModelProperty(name = "version", position = 5)
        private String version = null;

        @JsonProperty(value = "url")
        @ApiModelProperty(name = "url", position = 6)
        private String url = null;

        public Bot(final String vendor, final boolean isNefarius, final String family, final String description, final String version, final String url) {

            this.isBot = !String.valueOf(family).equalsIgnoreCase("Not a bot");
            this.isNefarius = false;

            if (this.isBot) {
                this.family = family;
                this.isNefarius = isNefarius;
                this.vendor = vendor;
                this.description = description;
                this.version = version;
                this.url = url;
            }
        }

        @JsonIgnore
        public boolean isBot() {
            return isBot;
        }

        @JsonIgnore
        public boolean isNefarius() {
            return isNefarius;
        }

        @JsonIgnore
        public String getVendor() {
            return vendor;
        }

        public void setVendor(final String vendor) {
            this.vendor = vendor;
        }

        @JsonIgnore
        public String getFamily() {
            return family;
        }

        public void setFamily(final String family) {
            this.family = family;
        }

        @JsonIgnore
        public String getDescription() {
            return description;
        }

        public void setDescription(final String description) {
            this.description = description;
        }

        @JsonIgnore
        public String getVersion() {
            return version;
        }

        public void setVersion(final String version) {
            this.version = version;
        }

        @JsonIgnore
        public String getUrl() {
            return url;
        }

        public void setUrl(final String url) {
            this.url = url;
        }
    }

    private static class Device implements Serializable {

        @JsonProperty(value = "vendor")
        @ApiModelProperty(name = "vendor", position = 0)
        private String deviceType = null;

        @JsonProperty(value = "architecture")
        @ApiModelProperty(name = "architecture", position = 1)
        private String architecture = null;

        @JsonProperty(value = "device")
        @ApiModelProperty(name = "device", position = 2)
        private String device = null;

        @JsonProperty(value = "isMobile")
        @ApiModelProperty(name = "isMobile", position = 3)
        private boolean mobile;

        @JsonProperty(value = "hasTouchScreen")
        @ApiModelProperty(name = "hasTouchScreen", position = 4)
        private boolean touch;

        @JsonProperty(value = "brand")
        @ApiModelProperty(name = "brand", position = 5)
        private Brand brand = null;

        @JsonProperty(value = "manufacturer")
        @ApiModelProperty(name = "manufacturer", position = 6)
        private Manufacturer manufacturer = null;

        private static class Brand implements Serializable {
            @JsonProperty(value = "name")
            @ApiModelProperty(name = "name", position = 1)
            private String name = null;

            @JsonProperty(value = "website")
            @ApiModelProperty(name = "website", position = 2)
            private String website = null;

            public Brand(String name, String website) {

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

        private static class Manufacturer implements Serializable {
            @JsonProperty(value = "name")
            @ApiModelProperty(name = "name", position = 1)
            private String name = null;

            @JsonProperty(value = "website")
            @ApiModelProperty(name = "website", position = 2)
            private String website = null;

            public Manufacturer(String name, String website) {

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

        public Device(final String deviceType,
            final boolean mobile,
            final String brand,
            final String brandWebsite,
            final String manufacturer,
            final String manufacturerWebsite,
            final String architecture,
            final String device,
            final boolean touch) {

            if (!String.valueOf(deviceType).equalsIgnoreCase("Unknown")) {
                this.deviceType = deviceType;
            }

            if (!String.valueOf(architecture).equalsIgnoreCase("")) {
                this.architecture = architecture;
            }

            if (!String.valueOf(device).equalsIgnoreCase("")) {
                this.device = device;
            }

            this.brand = new Brand(brand, brandWebsite);
            this.manufacturer = new Manufacturer(manufacturer, manufacturerWebsite);

            this.touch = touch;
            this.mobile = mobile;
        }

        @JsonIgnore
        public boolean isMobile() {
            return mobile;
        }

        public void setMobile(boolean mobile) {
            this.mobile = mobile;
        }

        @JsonIgnore
        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(final String deviceType) {
            this.deviceType = deviceType;
        }

        @JsonIgnore
        public String getArchitecture() {
            return architecture;
        }

        public void setArchitecture(final String architecture) {
            this.architecture = architecture;
        }

        @JsonIgnore
        public String getDevice() {
            return device;
        }

        public void setDevice(final String device) {
            this.device = device;
        }

        @JsonIgnore
        public boolean isTouch() {
            return touch;
        }

        public void setTouch(boolean touch) {
            this.touch = touch;
        }
    }

    private static class Locale implements Serializable {
        @JsonProperty(value = "country")
        @ApiModelProperty(name = "country", position = 0)
        private String country = null;

        @JsonProperty(value = "language")
        @ApiModelProperty(name = "language", position = 1)
        private String language = null;

        public Locale(final String country, final String language) {
            if (!String.valueOf(country).equalsIgnoreCase("Unknown")) {
                this.country = country;
            }

            if (!String.valueOf(language).equalsIgnoreCase("Unknown")) {
                this.language = language;
            }
        }

        @JsonIgnore
        public String getCountry() {
            return country;
        }

        public void setCountry(final String country) {
            this.country = country;
        }

        @JsonIgnore
        public String getLanguage() {
            return language;
        }

        public void setLanguage(final String language) {
            this.language = language;
        }
    }
}
