package com.nilportugues.useragent.app.modules.context.useragent;

import com.nilportugues.useragent.app.infrastructure.cqrs.query.QueryRegistry;
import com.nilportugues.useragent.app.modules.context.useragent.queries.UserAgent;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAgentModule {
    public UserAgentModule(final QueryRegistry queryRegistry) {
        registerQueryHandlers(queryRegistry);
    }

    private void registerQueryHandlers(final QueryRegistry registry) {
        registry.register(UserAgent.Query.class, "UserAgent.QueryHandler");
    }
}
