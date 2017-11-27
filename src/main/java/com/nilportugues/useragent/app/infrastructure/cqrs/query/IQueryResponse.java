package com.nilportugues.useragent.app.infrastructure.cqrs.query;

public interface IQueryResponse<Data> {
    Data getResponse();
}
