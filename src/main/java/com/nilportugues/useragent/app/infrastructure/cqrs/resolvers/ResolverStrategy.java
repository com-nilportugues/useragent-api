package com.nilportugues.useragent.app.infrastructure.cqrs.resolvers;

public class ResolverStrategy {

    public String get(final Object object) {
        if (object instanceof Class<?>) {
            return ((Class<?>) object).getCanonicalName();
        }

        return object.getClass().getCanonicalName();
    }
}
