package com.nilportugues.useragent.app.modules.context.useragent.queries;

import com.nilportugues.useragent.app.modules.context.useragent.parser.Client;
import com.nilportugues.useragent.app.modules.context.useragent.parser.Parser;
import com.nilportugues.useragent.app.modules.context.useragent.services.UserParserSingleton;

public class UserAgent {

    public static class Query {
        private String userAgent;

        public Query(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getUserAgent() {
            return userAgent;
        }
    }

    public static class QueryResponse {

    }

    public static class QueryHandler {

        private Parser parser = UserParserSingleton.getInstance();

        public QueryResponse handle(Query query) {
            Client client = parser.parse(query.getUserAgent());
            return new QueryResponse();
        }
    }
}
