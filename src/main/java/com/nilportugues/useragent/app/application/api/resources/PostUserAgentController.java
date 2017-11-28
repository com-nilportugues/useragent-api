package com.nilportugues.useragent.app.application.api.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilportugues.useragent.app.application.api.resources.marshallers.UserSearchAgentRequest;
import com.nilportugues.useragent.app.application.api.resources.presenter.UserAgentPresenter;
import com.nilportugues.useragent.app.infrastructure.cqrs.query.IQueryBus;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetector;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;

@RestController
public class PostUserAgentController {
    private static final String SWAGGER_DOC_TAG = "User Agent";
    private static final String SWAGGER_DOC = "Returns analyzed User Agent data.";

    private IQueryBus queryBus;
    private UserAgentPresenter presenter;

    @Inject
    public PostUserAgentController(IQueryBus queryBus, @Named("UserAgentPresenter") UserAgentPresenter presenter) {
        this.queryBus = queryBus;
        this.presenter = presenter;
    }

    @ApiOperation(value = SWAGGER_DOC, tags = {SWAGGER_DOC_TAG})
    @RequestMapping(value = "/user-agent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<String> postAction(@RequestBody final UserSearchAgentRequest search) throws JsonProcessingException {
        final UserAgentDetectionResult userAgent = new UserAgentDetector().parseUserAgent(search.getUserAgent());
        final ObjectMapper objectMapper = new ObjectMapper();

        return ResponseEntity.ok(objectMapper.writeValueAsString(userAgent));
    }
}
