package com.nilportugues.useragent.app.application.api.resources.presenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilportugues.useragent.app.application.api.presenters.vnderror.VndError;
import com.nilportugues.useragent.app.application.api.resources.marshallers.UserSearchAgentResponse;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;

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

    public String success(UserAgentDetectionResult result) {
        try {
            return format(new UserSearchAgentResponse(result));
        } catch (Exception e) {
            return internalServerError(e);
        }
    }

    public String internalServerError(Throwable throwable) {
        try {
            final VndError.VndErrors vndErrors = new VndError.VndErrors(new ArrayList<>());
            vndErrors.addError(new VndError.Error(throwable.getMessage()));

            final VndError response = new VndError();
            response.setEmbedded(vndErrors);

            return format(response);
        } catch (Throwable t) {
            t.printStackTrace();
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

    public String internalServerError() {
        try {
            final VndError.VndErrors vndErrors = new VndError.VndErrors(new ArrayList<>());
            vndErrors.addError(new VndError.Error(INTERNAL_ERROR_MESSAGE));

            final VndError response = new VndError();
            response.setEmbedded(vndErrors);

            return format(response);
        } catch (Throwable t) {
            t.printStackTrace();
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
