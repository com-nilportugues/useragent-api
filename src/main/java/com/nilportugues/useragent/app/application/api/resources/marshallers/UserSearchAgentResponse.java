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

        @JsonProperty(value = "familyName")
        @ApiModelProperty(name = "familyName", position = 1)
        private String family;

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
                this.family = family;
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
    }

    private static class Browser implements Serializable {

        @JsonProperty(value = "version")
        @ApiModelProperty(name = "version", position = 0)
        private String version = null;

        @JsonProperty(value = "fullVersion")
        @ApiModelProperty(name = "fullVersion", position = 1)
        private String fullVersion = null;

        @JsonProperty(value = "vendorName")
        @ApiModelProperty(name = "vendorName", position = 2)
        private String vendorName = null;

        @JsonProperty(value = "vendorWebsite")
        @ApiModelProperty(name = "vendorWebsite", position = 3)
        private String vendorWebsite = null;

        @JsonProperty(value = "familyName")
        @ApiModelProperty(name = "familyName", position = 4)
        private String familyName = null;

        @JsonProperty(value = "description")
        @ApiModelProperty(name = "description", position = 5)
        private String description = null;

        @JsonProperty(value = "renderingEngineVersion")
        @ApiModelProperty(name = "renderingEngineVersion", position = 6)
        private String renderingEngineVersion = null;

        @JsonProperty(value = "renderingEngineFullVersion")
        @ApiModelProperty(name = "renderingEngineFullVersion", position = 7)
        private String renderingEngineFullVersion = null;

        @JsonProperty(value = "renderingEngineName")
        @ApiModelProperty(name = "renderingEngineName", position = 8)
        private String renderingEngineName = null;

        @JsonProperty(value = "renderingEngineWebsite")
        @ApiModelProperty(name = "renderingEngineWebsite", position = 9)
        private String renderingEngineWebsite = null;

        @JsonProperty(value = "renderingEngineVendor")
        @ApiModelProperty(name = "renderingEngineVendor", position = 10)
        private String renderingEngineVendor = null;

        @JsonProperty(value = "renderingEngineFamily")
        @ApiModelProperty(name = "renderingEngineFamily", position = 11)
        private String renderingEngineFamily = null;

        @JsonProperty(value = "isRenderingEngineTridentBased")
        @ApiModelProperty(name = "isRenderingEngineTridentBased", position = 12)
        private final boolean isRenderingEngineTridentBased;

        @JsonProperty(value = "isRenderingEngineWebKitBased")
        @ApiModelProperty(name = "isRenderingEngineWebKitBased", position = 13)
        private final boolean isRenderingEngineWebKitBased;

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


        public static class RenderingEngine implements Serializable {

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

            @JsonProperty(value = "vendor")
            @ApiModelProperty(name = "vendor", position = 10)
            private String vendor = null;

            @JsonProperty(value = "family")
            @ApiModelProperty(name = "family", position = 11)
            private String family = null;

            @JsonProperty(value = "isTridentBased")
            @ApiModelProperty(name = "isTridentBased", position = 12)
            private final boolean isTridentBased;

            @JsonProperty(value = "isWebKitBased")
            @ApiModelProperty(name = "isWebKitBased", position = 13)
            private final boolean isWebKitBased;

            public RenderingEngine(String version, String fullVersion, String name, String website, String vendor, String family, boolean isTridentBased, boolean isWebKitBased) {
                this.version = version;
                this.fullVersion = fullVersion;
                this.name = name;
                this.website = website;
                this.vendor = vendor;
                this.family = family;
                this.isTridentBased = isTridentBased;
                this.isWebKitBased = isWebKitBased;
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
            public String getWebsite() {
                return website;
            }
            @JsonIgnore
            public String getVendor() {
                return vendor;
            }
            @JsonIgnore
            public String getFamily() {
                return family;
            }
            @JsonIgnore
            public boolean isTridentBased() {
                return isTridentBased;
            }
            @JsonIgnore
            public boolean isWebKitBased() {
                return isWebKitBased;
            }
        }

        private static class Vendor implements Serializable {

            @JsonProperty(value = "name")
            @ApiModelProperty(name = "name", position = 2)
            private String name = null;

            @JsonProperty(value = "website")
            @ApiModelProperty(name = "website", position = 3)
            private String website = null;

            public Vendor(String name, String website) {
                this.name = name;
                this.website = website;
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

        public Browser(final String version,
            final String fullVersion,
            final String description,
            final String vendorName,
            final String vendorWebsite,
            final String familyName,
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

            if (!String.valueOf(vendorName).equalsIgnoreCase("Unknown")) {
                this.vendorName = vendorName;
            }

            if (!String.valueOf(familyName).equalsIgnoreCase("")) {
                this.familyName = familyName;
            }

            if (!String.valueOf(description).equalsIgnoreCase("")) {
                this.description = description;
            }

            if (!String.valueOf(renderingEngineVersion).equalsIgnoreCase("")) {
                this.renderingEngineVersion = renderingEngineVersion;
            }

            if (!String.valueOf(renderingEngineFullVersion).equalsIgnoreCase("")) {
                this.renderingEngineFullVersion = renderingEngineFullVersion;
            }

            if (!String.valueOf(renderingEngineFamily).equalsIgnoreCase("Unknown")) {
                this.renderingEngineFamily = renderingEngineFamily;
            }

            if (!String.valueOf(renderingEngineName).equalsIgnoreCase("Unknown")) {
                this.renderingEngineName = renderingEngineName;
            }

            if (!String.valueOf(renderingEngineWebsite).equalsIgnoreCase("")) {
                this.renderingEngineWebsite = renderingEngineWebsite;
            }

            if (!String.valueOf(vendorWebsite).equalsIgnoreCase("")) {
                this.vendorWebsite = vendorWebsite;
            }

            this.isRenderingEngineTridentBased = isRenderingEngineTridentBased;
            this.isRenderingEngineWebKitBased = isRenderingEngineWebKitBased;
            this.inWebView = inWebView;
            this.isGecko = isGecko;
            this.isRobot = isRobot;
            this.isTrident = isTrident;
            this.isWebkit = isWebkit;
        }

        @JsonIgnore
        public String getVendorWebsite() {
            return vendorWebsite;
        }

        @JsonIgnore
        public String getRenderingEngineName() {
            return renderingEngineName;
        }

        @JsonIgnore
        public String getRenderingEngineWebsite() {
            return renderingEngineWebsite;
        }

        @JsonIgnore
        public boolean isRenderingEngineTridentBased() {
            return isRenderingEngineTridentBased;
        }

        @JsonIgnore
        public boolean isRenderingEngineWebKitBased() {
            return isRenderingEngineWebKitBased;
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

        public void setVersion(String version) {
            this.version = version;
        }

        @JsonIgnore
        public String getFullVersion() {
            return fullVersion;
        }

        public void setFullVersion(String fullVersion) {
            this.fullVersion = fullVersion;
        }

        @JsonIgnore
        public String getVendorName() {
            return vendorName;
        }

        public void setVendorName(String vendorName) {
            this.vendorName = vendorName;
        }

        @JsonIgnore
        public String getFamilyName() {
            return familyName;
        }

        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }

        @JsonIgnore
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @JsonIgnore
        public String getRenderingEngineVersion() {
            return renderingEngineVersion;
        }

        public void setRenderingEngineVersion(String renderingEngineVersion) {
            this.renderingEngineVersion = renderingEngineVersion;
        }

        @JsonIgnore
        public String getRenderingEngineFullVersion() {
            return renderingEngineFullVersion;
        }

        public void setRenderingEngineFullVersion(String renderingEngineFullVersion) {
            this.renderingEngineFullVersion = renderingEngineFullVersion;
        }

        @JsonIgnore
        public String getRenderingEngineVendor() {
            return renderingEngineVendor;
        }

        public void setRenderingEngineVendor(String renderingEngineVendor) {
            this.renderingEngineVendor = renderingEngineVendor;
        }

        @JsonIgnore
        public String getRenderingEngineFamily() {
            return renderingEngineFamily;
        }

        public void setRenderingEngineFamily(String renderingEngineFamily) {
            this.renderingEngineFamily = renderingEngineFamily;
        }

        @JsonIgnore
        public boolean isInWebView() {
            return inWebView;
        }

        public void setInWebView(boolean inWebView) {
            this.inWebView = inWebView;
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

        @JsonProperty(value = "familyName")
        @ApiModelProperty(name = "familyName", position = 3)
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

        @JsonProperty(value = "brandName")
        @ApiModelProperty(name = "brandName", position = 1)
        private String brand = null;

        @JsonProperty(value = "brandWebsite")
        @ApiModelProperty(name = "brandWebsite", position = 2)
        private String brandWebsite = null;

        @JsonProperty(value = "manufacturerName")
        @ApiModelProperty(name = "manufacturerName", position = 3)
        private String manufacturer = null;

        @JsonProperty(value = "manufacturerWebsite")
        @ApiModelProperty(name = "manufacturerWebsite", position = 4)
        private String manufacturerWebsite = null;

        @JsonProperty(value = "architecture")
        @ApiModelProperty(name = "architecture", position = 5)
        private String architecture = null;

        @JsonProperty(value = "device")
        @ApiModelProperty(name = "device", position = 6)
        private String device = null;

        @JsonProperty(value = "isMobile")
        @ApiModelProperty(name = "isMobile", position = 7)
        private boolean mobile;

        @JsonProperty(value = "hasTouchScreen")
        @ApiModelProperty(name = "hasTouchScreen", position = 8)
        private boolean touch;


        private static class Brand implements Serializable {
            @JsonProperty(value = "name")
            @ApiModelProperty(name = "name", position = 1)
            private String name = null;

            @JsonProperty(value = "website")
            @ApiModelProperty(name = "website", position = 2)
            private String website = null;

            public Brand(String name, String website) {
                this.name = name;
                this.website = website;
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
                this.name = name;
                this.website = website;
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

            if (!String.valueOf(brand).equalsIgnoreCase("Unknown")) {
                this.brand = brand;
            }

            if (!String.valueOf(brandWebsite).equalsIgnoreCase("")) {
                this.brandWebsite = brandWebsite;
            }

            if (!String.valueOf(manufacturer).equalsIgnoreCase("Unknown")) {
                this.manufacturer = manufacturer;
            }

            if (!String.valueOf(manufacturerWebsite).equalsIgnoreCase("")) {
                this.manufacturerWebsite = manufacturerWebsite;
            }

            if (!String.valueOf(architecture).equalsIgnoreCase("")) {
                this.architecture = architecture;
            }

            if (!String.valueOf(device).equalsIgnoreCase("")) {
                this.device = device;
            }

            this.touch = touch;
            this.mobile = mobile;
        }

        @JsonIgnore
        public String getBrandWebsite() {
            return brandWebsite;
        }

        public void setBrandWebsite(String brandWebsite) {
            this.brandWebsite = brandWebsite;
        }

        @JsonIgnore
        public String getManufacturerWebsite() {
            return manufacturerWebsite;
        }

        public void setManufacturerWebsite(String manufacturerWebsite) {
            this.manufacturerWebsite = manufacturerWebsite;
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
        public String getBrand() {
            return brand;
        }

        public void setBrand(final String brand) {
            this.brand = brand;
        }

        @JsonIgnore
        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(final String manufacturer) {
            this.manufacturer = manufacturer;
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
