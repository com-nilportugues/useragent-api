package com.nilportugues.useragent.app.parser;

import org.apache.commons.collections.map.LRUMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class CachingParser extends Parser {

    private static final int CACHE_SIZE = 1000;

    private Map<String, Client> cacheClient = null;
    private Map<String, UserAgent> cacheUserAgent = null;
    private Map<String, Device> cacheDevice = null;
    private Map<String, OS> cacheOS = null;

    public CachingParser() throws IOException {
        super();
    }

    public CachingParser(InputStream regexYaml) {
        super(regexYaml);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Client parse(String agentString) {
        if (agentString == null) {
            return null;
        }
        if (cacheClient == null) {
            cacheClient = new LRUMap(CACHE_SIZE);
        }
        Client client = cacheClient.get(agentString);
        if (client != null) {
            return client;
        }
        client = super.parse(agentString);
        cacheClient.put(agentString, client);
        return client;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserAgent parseUserAgent(String agentString) {
        if (agentString == null) {
            return null;
        }
        if (cacheUserAgent == null) {
            cacheUserAgent = new LRUMap(CACHE_SIZE);
        }
        UserAgent userAgent = cacheUserAgent.get(agentString);
        if (userAgent != null) {
            return userAgent;
        }
        userAgent = super.parseUserAgent(agentString);
        cacheUserAgent.put(agentString, userAgent);
        return userAgent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Device parseDevice(String agentString) {
        if (agentString == null) {
            return null;
        }
        if (cacheDevice == null) {
            cacheDevice = new LRUMap(CACHE_SIZE);
        }
        Device device = cacheDevice.get(agentString);
        if (device != null) {
            return device;
        }
        device = super.parseDevice(agentString);
        cacheDevice.put(agentString, device);
        return device;
    }

    @SuppressWarnings("unchecked")
    @Override
    public OS parseOS(String agentString) {
        if (agentString == null) {
            return null;
        }

        if (cacheOS == null) {
            cacheOS = new LRUMap(CACHE_SIZE);
        }
        OS os = cacheOS.get(agentString);
        if (os != null) {
            return os;
        }
        os = super.parseOS(agentString);
        cacheOS.put(agentString, os);
        return os;
    }
}
