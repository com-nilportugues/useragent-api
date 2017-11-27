package com.nilportugues.useragent.app.infrastructure.cqrs.middlewares;

import java.util.concurrent.CompletableFuture;

public interface IMiddleware {
    CompletableFuture execute(final Object object);
}
