package com.nilportugues.useragent.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nilportugues.useragent.app.parser.Client;
import com.nilportugues.useragent.app.parser.Parser;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {

        final Router router = Router.router(vertx);

        router.get("/me").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                final String userAgent = routingContext.request().getHeader("User-Agent");

                try {
                    // Query
                    Parser uaParser = new Parser();
                    Client client = uaParser.parse(userAgent);

                    // presenter
                    ObjectMapper objectMapper = new ObjectMapper();
                    String response = objectMapper.writeValueAsString(client);

                    routingContext.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(200)
                        .end(response);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(config().getInteger("http.port", 8080),
                result -> {
                    if (result.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(result.cause());
                    }
                });
    }
}
