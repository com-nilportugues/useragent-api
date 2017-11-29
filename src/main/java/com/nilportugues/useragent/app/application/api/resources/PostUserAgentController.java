package com.nilportugues.useragent.app.application.api.resources;

import com.nilportugues.useragent.app.application.api.resources.marshallers.InternalServerError;
import com.nilportugues.useragent.app.application.api.resources.marshallers.UserSearchAgentRequest;
import com.nilportugues.useragent.app.application.api.resources.marshallers.UserSearchAgentResponse;
import com.nilportugues.useragent.app.application.api.resources.presenter.UserAgentPresenter;
import com.nilportugues.useragent.app.infrastructure.cqrs.query.IQueryBus;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;
import com.nilportugues.useragent.app.modules.context.useragent.queries.UserAgent;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.Future;

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
        @ApiResponse(code = 200, message = "OK", response = UserSearchAgentResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class)
    })
    public Future<ResponseEntity<String>> postAction(@RequestBody final UserSearchAgentRequest search) throws Exception {

        final UserAgent.Query query = new UserAgent.Query(search.getUserAgent(), search.getAcceptLanguage());

        return Mono.fromFuture(queryBus.dispatch(query))
            .map(result -> okView((UserAgentDetectionResult) result))
            .doOnError(this::errorView)
            .toFuture();
    }

    private ResponseEntity<String> okView(final UserAgentDetectionResult result) {
        return new ResponseEntity<>(presenter.success(result), HttpStatus.OK);
    }

    private Mono<ResponseEntity<String>> errorView(final Throwable throwable) {
        throwable.printStackTrace();
        return Mono.just(new ResponseEntity<>(presenter.internalServerError(throwable), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
