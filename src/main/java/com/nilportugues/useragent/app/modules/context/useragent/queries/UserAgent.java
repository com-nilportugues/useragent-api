package com.nilportugues.useragent.app.modules.context.useragent.queries;

import com.nilportugues.useragent.app.infrastructure.cqrs.query.IQuery;
import com.nilportugues.useragent.app.infrastructure.cqrs.query.IQueryHandler;
import com.nilportugues.useragent.app.modules.context.useragent.model.UserAgentDetectionResult;

import javax.inject.Named;
import java.util.concurrent.CompletableFuture;

public class UserAgent {

    public static class Query implements IQuery {
        private String userAgent;

        public Query(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getUserAgent() {
            return userAgent;
        }
    }

    @Named("UserAgent.Query")
    public static class QueryHandler implements IQueryHandler<Query> {

        @Override
        public CompletableFuture<UserAgentDetectionResult> handle(final Query query) {
            return null;
        }
    }
}
