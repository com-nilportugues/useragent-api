package com.nilportugues.useragent.app.infrastructure.cqrs.query;

import com.nilportugues.useragent.app.infrastructure.cqrs.di.IBeanProvider;
import com.nilportugues.useragent.app.infrastructure.cqrs.resolvers.ResolverStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class QueryRegistry {

    private static final Logger log = LoggerFactory.getLogger(QueryRegistry.class);
    private static final String LOG = "[Query Bus][Registered] {}";

    private final Map<String, String> querys;
    private final ResolverStrategy resolver;
    private final IBeanProvider provider;

    public QueryRegistry(final IBeanProvider provider, final ResolverStrategy resolver) {
        this.querys = new HashMap<>();
        this.provider = provider;
        this.resolver = resolver;
    }

    public void register(final Class<? extends IQuery> query, final IQueryHandler handler) {
        CompletableFuture.runAsync(() -> {
            final String queryName = resolver.get(query);
            final String handlerBeanName = resolver.get(handler);

            registerHandler(handlerBeanName, queryName);
            logRegistration(queryName);
        });
    }

    public void register(final Class<? extends IQuery> query, final String handlerBeanName) {
        CompletableFuture.runAsync(() -> {
            final String queryName = resolver.get(query);

            registerHandler(handlerBeanName, queryName);
            logRegistration(queryName);
        });
    }

    private void registerHandler(final String handlerBeanName, final String queryName) {
        querys.put(queryName, handlerBeanName);
    }

    private void logRegistration(final String queryName) {
        log.debug(LOG, queryName);
    }

    public IQueryHandler get(final IQuery query) {
        final String queryClassName = resolver.get(query);
        final String queryHandlerClassName = querys.get(queryClassName);

        return (IQueryHandler) provider.get(queryHandlerClassName);
    }
}
