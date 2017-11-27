package com.nilportugues.useragent.app.infrastructure.cqrs.query;

import java.util.concurrent.CompletableFuture;

public interface IQueryHandler<Q extends IQuery> {

    CompletableFuture handle(final Q query);
}
