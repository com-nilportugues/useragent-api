package com.nilportugues.useragent.app.infrastructure.cqrs.query;

import com.nilportugues.useragent.app.infrastructure.cqrs.middlewares.IMiddleware;

import java.util.concurrent.CompletableFuture;

public interface IQueryBus {

    CompletableFuture<?> dispatch(final IQuery query);

    void addMiddleware(final IMiddleware middleware);
}
