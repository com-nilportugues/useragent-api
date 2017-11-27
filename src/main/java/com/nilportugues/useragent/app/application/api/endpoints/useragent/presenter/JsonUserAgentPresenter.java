package com.nilportugues.useragent.app.application.api.endpoints.useragent.presenter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Named;

@Named("JsonUserAgentPresenter")
public class JsonUserAgentPresenter implements UserAgentPresenter {

    @Inject
    private final ObjectMapper objectMapper;

    public JsonUserAgentPresenter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
