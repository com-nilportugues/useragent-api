package com.nilportugues.useragent.app.application.api.resources.presenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilportugues.useragent.app.application.api.presenters.vnderror.VndError;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("UserAgentPresenter")
public class JsonUserAgentPresenter implements UserAgentPresenter {

    private static final String INTERNAL_ERROR_MESSAGE = "We cannot resolve your request right now. Please try again later.";

    private final ObjectMapper objectMapper;

    @Inject
    public JsonUserAgentPresenter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String success() {
        return "";
    }

    public String internalServerError() {
        try {
            final VndError.VndErrors vndErrors = new VndError.VndErrors(new ArrayList<>());
            vndErrors.addError(new VndError.Error(INTERNAL_ERROR_MESSAGE));

            final VndError response = new VndError();
            response.setEmbedded(vndErrors);

            return format(response);
        } catch (Throwable t) {
            return "{ " +
                "\"total\" : 1, " +
                "\"_embedded\": { " +
                "\"errors\": [" +
                "{ \"message\": \"Internal Server Error\", \"path\": \"/\"}" +
                "]" +
                "}" +
                "}";
        }
    }

    private String format(final Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
