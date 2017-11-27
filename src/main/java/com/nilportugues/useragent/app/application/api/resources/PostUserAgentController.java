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
import java.io.Serializable;

@RestController
public class PostUserAgentController {

    private EventBus eventBus;
    private UserAgentPresenter presenter;

    @Inject
    public PostUserAgentController(EventBus eventBus, @Named("UserAgentPresenter") UserAgentPresenter presenter) {
        this.eventBus = eventBus;
        this.presenter = presenter;
    }

    @RequestMapping(value = "/user-agent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> postAction(@RequestBody final UserSearchAgent search) throws JsonProcessingException {
        final UserAgentDetectionResult userAgent = new UserAgentDetector().parseUserAgent(search.getUserAgent());
        final ObjectMapper objectMapper = new ObjectMapper();

        return ResponseEntity.ok(objectMapper.writeValueAsString(userAgent));
    }

    private static class UserSearchAgent implements Serializable {
        private String userAgent;

        public UserSearchAgent() {
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getUserAgent() {
            return userAgent;
        }
    }
}
