package com.nilportugues.useragent.app.infrastructure.cqrs.di;

public interface IBeanProvider<T> {
    T get(final String beanName);
}
