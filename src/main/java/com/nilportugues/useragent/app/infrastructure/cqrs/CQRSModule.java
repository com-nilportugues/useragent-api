package com.nilportugues.useragent.app.infrastructure.cqrs;

import com.nilportugues.useragent.app.infrastructure.cqrs.di.IBeanProvider;
import com.nilportugues.useragent.app.infrastructure.cqrs.di.SpringBeanProvider;
import com.nilportugues.useragent.app.infrastructure.cqrs.query.IQueryBus;
import com.nilportugues.useragent.app.infrastructure.cqrs.query.QueryRegistry;
import com.nilportugues.useragent.app.infrastructure.cqrs.resolvers.ResolverStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CQRSModule {

    @Bean
    public IBeanProvider getSpringBeanProvider(final ApplicationContext context) {
        return new SpringBeanProvider(context);
    }

    @Bean
    public ResolverStrategy getResolverStrategy() {
        return new ResolverStrategy();
    }

    @Bean
    public QueryRegistry getQueryRegistry(final ResolverStrategy resolver, final IBeanProvider provider) {
        return new QueryRegistry(provider, resolver);
    }

    @Bean
    public IQueryBus getQueryBus(final QueryRegistry registry, final ResolverStrategy resolver) {
        return new QueryBus(registry, resolver);
    }
}
