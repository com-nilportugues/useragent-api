package com.nilportugues.useragent.app.parser;

public class Client {
    private final OS os;
    private final Device device;
    private final UserAgent userAgent;

    public Client(UserAgent userAgent, OS os, Device device) {
        this.userAgent = userAgent;
        this.os = os;
        this.device = device;
    }

    public OS getOs() {
        return os;
    }

    public Device getDevice() {
        return device;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Client))
            return false;

        Client o = (Client) other;
        return ((this.userAgent != null && this.userAgent.equals(o.userAgent)) || this.userAgent == o.userAgent) &&
            ((this.os != null && this.os.equals(o.os)) || this.os == o.os) &&
            ((this.device != null && this.device.equals(o.device)) || this.device == o.device);
    }

    @Override
    public int hashCode() {
        int h = userAgent == null ? 0 : userAgent.hashCode();
        h += os == null ? 0 : os.hashCode();
        h += device == null ? 0 : device.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return String.format("{\"user_agent\": %s, \"os\": %s, \"device\": %s}",
            userAgent, os, device);
    }
}
