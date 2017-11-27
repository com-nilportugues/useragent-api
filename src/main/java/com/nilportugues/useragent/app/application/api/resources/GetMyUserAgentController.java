package com.nilportugues.useragent.app.application.api.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilportugues.useragent.app.application.api.resources.presenter.UserAgentPresenter;
import com.nilportugues.useragent.app.infrastructure.cqrs.EventBus;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetector;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;

@RestController
public class GetMyUserAgentController {

    private EventBus eventBus;
    private UserAgentPresenter presenter;

    @Inject
    public GetMyUserAgentController(EventBus eventBus, @Named("UserAgentPresenter") UserAgentPresenter presenter) {
        this.eventBus = eventBus;
        this.presenter = presenter;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> getAction(@RequestHeader(value = "User-Agent") final String userAgentHeader) throws JsonProcessingException {

        final UserAgentDetectionResult userAgent = new UserAgentDetector().parseUserAgent(userAgentHeader);
        final ObjectMapper objectMapper = new ObjectMapper();

        return ResponseEntity.ok(objectMapper.writeValueAsString(userAgent));
    }

}
